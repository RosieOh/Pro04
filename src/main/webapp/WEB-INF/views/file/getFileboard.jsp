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
        <h2 class="h1">파일 자료 상세보기</h2>
        <hr>
        <div class="container">
            <table class="table table-active">
                <tbody>
                <tr>
                    <th>글 번호</th>
                    <td>${fileboard.fileBoard.postNo}</td>
                </tr>
                <tr>
                    <th>글 제목</th>
                    <td>${fileboard.fileBoard.title}</td>
                </tr>
                <tr>
                    <th>글 내용</th>
                    <td>${fileboard.fileBoard.content}</td>
                </tr>
                <tr>
                    <th>첨부 파일</th>
                    <td>
                        <c:forEach var="file" items="${fileboard.fileList}">
                            <a href="${path1}/resources/upload/${file.originFile}" title="${file.fileSize}" download>${file.originFile}</a>
                        </c:forEach>
                        <c:if test="${empty fileboard.fileList}">
                            첨부된 파일이 없습니다.
                        </c:if>
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
                        <a href="${path1}/file/filelist1.do" class="button is-primary">파일 자료 목록</a>
                        <c:if test="${sid.equals('admin')}">
                            <a href="${path1}/file/removeFileboard.do?postNo=${fileboard.fileBoard.postNo}" class="button is-danger">자료 삭제</a>
                            <a href="${path1}/file/modifyFileboard.do?postNo=${fileboard.fileBoard.postNo}" class="button is-info">자료 수정</a>
                        </c:if>
                    </td>
                </tr>
                </tbody>
            </table>
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
