<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path1" value="${pageContext.servletContext.contextPath }" />
<!DOCTYPE html>
<html lang="en" style="background: linear-gradient(to right, #71AEE8, #77E9CE);">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>메인페이지</title>

    <%-- 필요 디자인 요소 취합 항목 --%>
    <jsp:include page="./include/head.jsp" />
    <script src="${path1}/resources/aos/aos.js"></script>
    <link rel="stylesheet" href="${path1}/resources/aos/aos.css">
    <link rel="stylesheet" href="${path1}/resources/css/main.css">
    <%-- 필요 디자인 요소 취합 항목 끝--%>
</head>
<body>

<%--메인 페이지 이미지 애니메이션--%>
<div class="img aos-init aos-animate" data-aos="fade-up" data-aos-duration="1000" style="padding-top: 300px;">
    <img src="${path1}/resources/images/img_mc03_01.png" alt="icon" class="i1">
    <img src="${path1}/resources/images/img_mc03_02.png" alt="icon" class="ani ani1 i2">
    <img src="${path1}/resources/images/img_mc03_03.png" alt="icon" class="ani ani2 i3">
    <img src="${path1}/resources/images/img_mc03_04.png" alt="icon" class="ani ani2 i4">
    <img src="${path1}/resources/images/img_mc03_05.png" alt="icon" class="ani ani2 i5">
</div>
<%--메인 페이지 이미지 애니메이션 끝--%>

</body>
</html>