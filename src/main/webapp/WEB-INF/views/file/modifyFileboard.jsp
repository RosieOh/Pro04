<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path1" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>자료실</title>
    <jsp:include page="../include/head.jsp" />
    <!-- Add Bootstrap CSS link here -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<!-- Header Start -->
<jsp:include page="../include/header.jsp" />
<!-- Header Close -->

<!-- Content Start -->
<div class="content" id="contents">
    <div class="row column text-center">
        <h2 class="h1">파일 자료 수정하기</h2>
        <hr>
        <div class="container">
            <form method="post" enctype="multipart/form-data" action="${path1}/file/modifyFileboard.do">
                <table class="table table-active">
                    <tbody>
                    <tr>
                        <th>글 번호</th>
                        <td>${fileboard.fileBoard.postNo}</td>
                    </tr>
                    <tr>
                        <th><label for="title">제목</label></th>
                        <td><input type="text" name="title" id="title" class="input" value="${fileboard.fileBoard.title}" required></td>
                    </tr>
                    <tr>
                        <th>글 내용</th>
                        <td><textarea name="content" id="content" class="textarea" required>${fileboard.fileBoard.content}</textarea></td>
                    </tr>
                    <tr>
                        <th>첨부 파일</th>
                        <td>
                            현재 파일 : <br>
                            <c:forEach var="file" items="${fileboard.fileList}">
                                <a href="${path1}/resources/upload/${file.originFile}" title="${file.fileSize}" download>${file.originFile}</a>
                                <button type="button" class="remove_btn" style="display:inline-block;padding:0;background-color: black;color:#fff;width:18px;height:18px;font-size:12px;line-height:18px;text-align:center;border-radius:10px;" data1="${file.no}" data2="${file.postNo}">x</button><br>
                            </c:forEach>
                            <c:if test="${empty fileboard.fileList}">
                                첨부된 파일이 없습니다.
                            </c:if>
                            <hr>새로 첨부할 파일 : <br>
                            <div class="control">
                                <label for="file1" class="file-label">
                                    <span style="padding:5px;margin:5px">파일1 : </span>
                                    <input type="file" name="file1" id="file1" class="file-input">
                                    <span class="file-cta">
                                          <span class="file-icon">
                                            <i class="fas fa-upload"></i>
                                          </span>
                                          <span class="file-label">
                                            Choose a file…
                                          </span>
                                        </span>
                                </label>
                            </div>
                            <div class="control">
                                <label for="file2" class="file-label">
                                    <span style="padding:5px;margin:5px">파일2 : </span>
                                    <input type="file" name="file2" id="file2" class="file-input">
                                    <span class="file-cta">
                                          <span class="file-icon">
                                            <i class="fas fa-upload"></i>
                                          </span>
                                          <span class="file-label">
                                            Choose a file…
                                          </span>
                                        </span>
                                </label>
                            </div>
                            <div class="control">
                                <label for="file3" class="file-label">
                                    <span style="padding:5px;margin:5px">파일3 : </span>
                                    <input type="file" name="file3" id="file3" class="file-input">
                                    <span class="file-cta">
                                          <span class="file-icon">
                                            <i class="fas fa-upload"></i>
                                          </span>
                                          <span class="file-label">
                                            Choose a file…
                                          </span>
                                        </span>
                                </label>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th>작성일</th>
                        <td>${fileboard.fileBoard.regdate}</td>
                    </tr>
                    <tr>
                        <th>조회수</th>
                        <td>${fileboard.fileBoard.visited}</td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" class="button is-danger" value="자료 수정" />
                            <a href="${path1}/file/filelist1.do" class="button is-primary">파일 자료 목록</a>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
            <script>
                $(document).ready(function(){
                    $(".remove_btn").click(function(){
                        var tar = {
                            no : parseInt($(this).attr("data1")),
                            postNo:parseInt($(this).attr("data2"))
                        };
                        $.ajax({
                            url:"${path1}/file/fileRemove.do", //요청URL
                            type:"post",    //요청 전송방식
                            dataType:"json",
                            data:tar,
                            sucess:function(){
                                console.log("파일 삭제 성공");
                            },
                            error:function(){
                                console.log("파일 삭제 실패");
                            }
                        });
                    });
                });
            </script>
        </div>
    </div>
</div>
<!-- Content Close -->

<!-- Add Bootstrap JS and jQuery script links here -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>

<!-- Footer Start -->
<jsp:include page="../include/footer.jsp" />
<!-- Footer Close -->
</body>
</html>
