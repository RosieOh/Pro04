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
<div class="container mt-5">
    <h1 class="text-center">자료실 목록</h1>
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">글번호</th>
            <th scope="col">제목</th>
            <th scope="col">작성일</th>
            <th scope="col">읽은횟수</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="board" items="${fileboardList}">
            <tr class="table-info">
                <td>${board.fileBoard.postNo}</td>
                <td><a href="${path1}/file/getFileboard.do?postNo=${board.fileBoard.postNo}">${board.fileBoard.title}</a></td>
                <td>${board.fileBoard.regdate}</td>
                <td>${board.fileBoard.visited}</td>
                <td>
                    <c:if test="${not empty board.fileList}">
                        <img src="${path1}/resources/img/disk.png" alt="디스크이미지" style="width:24px;height:24px;"/>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${sid.equals('admin')}">
        <a href="${path1}/file/fileupload1.do" class="btn btn-primary">파일 자료 등록</a>
    </c:if>
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
