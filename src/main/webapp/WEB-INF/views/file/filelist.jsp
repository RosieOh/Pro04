<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path1" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항</title>
    <!-- 헤드 부분 인클루드 -->
    <jsp:include page="../include/head.jsp"></jsp:include>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding&display=swap');

        * {
            font-family: 'Nanum Gothic Coding', monospace;
        }

        .title {
            font-family: 'Nanum Gothic Coding', monospace;
            margin-top: 55px;
            text-align: center;
            font-weight: 700;
        }

        .contents {
            font-family: 'Nanum Gothic Coding', monospace;
            text-align: center;
            font-weight: 200;
        }

        .container {
            max-width: 1280px;
            border-radius: 5px;
            text-align: center; /* 컨테이너 내부의 요소들을 가운데 정렬 */
        }

        .box_wrap {
            margin-top: 15px;
        }

        .review-list th:nth-child(1) {
            width: 12%;
        }

        .box_wrap {
            border-top: #000 2px solid;
        }

        *, *:before, *:after {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        .box_wrap thead th {
            padding: 15px 5px;
            border-bottom: 1px solid #d0cfd5;
            text-align: center;
        }

        .box_wrap tbody td {
            padding: 15px 5px;
            border-bottom: 1px solid #d0cfd5;
            text-align: center;
        }

        .item2 .al {
            text-decoration: none;
            color: black;
            display: block;
            text-overflow: ellipsis;
            max-height: 2.8em;
            line-height: 3.4em;
            word-wrap: break-word;
            overflow: hidden;
            display: -webkit-box;
            -webkit-line-clamp: 2;
            -webkit-box-orient: vertical;
            word-break: keep-all;
        }

        .paginate .page-item {
            margin: 0 5px;
        }

        .paginate .page-link {
            display: inline-block;
            width: 34px;
            height: 34px;
            border: 1px solid #ddd;
            background-color: #f8f9fa;
            color: #333;
            font-size: 14px;
            line-height: 32px;
            text-align: center;
            text-decoration: none;
            transition: background-color 0.3s, color 0.3s;
        }

        .paginate .page-link:hover {
            background-color: #333;
            color: #fff;
            cursor: pointer;
        }

        .item1, .item2, .item3, .item4 {
            font-size: 18px;
        }

        .button2 {
            text-decoration: none;
            border-radius: 20px;
            margin: 15px;
            padding: 10px;
            float: right;
            background-color: #008CD6;
            border-color: #008CD6;
            color: #ffffff;
        }
        table {
            table-layout: fixed;
            width: 100%;
            border-collapse: collapse;
            border-spacing: 0;
        }

        tbody {
            display: table-row-group;
            vertical-align: middle;
            border-color: inherit;
        }

        thead {
            display: table-header-group;
            vertical-align: middle;
            border-color: inherit;
        }
        .search_wrap {
            align-items: center;
            background: #f8f8f8;
            padding-top: 56px;
            padding-left: 60px;
            text-align: center;
            margin: 20px 0 20px;
            display: flex;
            gap: 10px;
            justify-content: center;
        }
    </style>
</head>
<body>
<!-- Header Start -->
<jsp:include page="../include/header.jsp"></jsp:include>
<!-- Header Close -->

<!-- Content Start -->
<div class="container">
    <h2 class="title">자료실 목록</h2>
    <h3 class="contents">강의 및 교재에 대한 자료를 등록하는 곳입니다.</h3>
    <br>
    <div class="search_wrap">
        <form action="${path1 }/file/list.do" method="get" class="field has-addons has-addons-right" style="margin-right: 315px;">
            <p class="control">
                <span class="select">
                    <select id="type" name="type" style="width: 240px;">
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                    </select>
                </span>
            </p>
            <p class="control">
                <input class="input" type="text" id="keyword" name="keyword" placeholder="검색어를 입력하세요" value="${keyword }" style="    position: relative;
    width: 600px;
    padding-right: 60px;
    background: #fff;
    border: #ddd 1px solid;">
            </p>
            <p class="control">
                <input type="submit" class="button is-mainColor" value="검색" />
            </p>
        </form>
    </div>
    <br>
    <div class="box_wrap">
        <table class="notice-list" id="tb1">
            <thead>
            <tr>
                <th class="item1">번호</th>
                <th class="item2">제목</th>
                <th class="item3">등록일</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="board" items="${fileboardList}" varStatus="status">
                <tr>
                    <td class="item1">${board.fileBoard.postNo}</td>
                    <td class="item2">
                        <a href="${path1}/file/getFileboard.do?postNo=${board.fileBoard.postNo}" class="al">${board.fileBoard.title}</a>
                    </td>
                    <td class="item3">${board.fileBoard.regdate}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <c:if test="${sid.equals('admin')}">
            <div class="button-group">
                <a class="button2" href="${path1}/file/fileupload1.do">파일 자료 등록</a>
            </div>
        </c:if>
    </div>
</div>
<br>
<br>
<nav class="pagination is-rounded is-centered mb-6" role="navigation" aria-label="pagination">
    <c:if test="${curPage > page.pageCount }">
        <a href="${path1 }/file/list.do?page=${page.blockStartNum - 1 }<c:if test="${!empty keyword }">&type=${type }&keyword=${keyword }</c:if>" class="pagination-previous">Previous</a>
    </c:if>
    <c:if test="${page.blockLastNum < page.totalPageCount }">
        <a href="${path1 }/file/list.do?page=${page.blockLastNum + 1 }<c:if test="${!empty keyword }">&type=${type }&keyword=${keyword }</c:if>" class="pagination-next">Next page</a>
    </c:if>

    <ul class="pagination-list">
        <c:forEach var="i" begin="${page.blockStartNum }" end="${page.blockLastNum }">
            <c:choose>
                <c:when test="${i == curPage }">
                    <li>
                        <a href="${path1 }/file/list.do?page=${i }<c:if test="${!empty keyword }">&type=${type }&keyword=${keyword }</c:if>" class="pagination-link is-current" aria-label="Page ${i }" aria-current="page" >${i }</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="${path1 }/file/list.do?page=${i }<c:if test="${!empty keyword }">&type=${type }&keyword=${keyword }</c:if>" class="pagination-link" aria-label="Page ${i }" aria-current="page">${i }</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </ul>
</nav>
</div>
<script>
    function checkLogin() {
        var confirmLogin = confirm("글 작성은 로그인이 필요해요! 로그인하시겠어요?");
        if (confirmLogin) {
            location.href = '${path1 }/user/loginForm';
        }
    }
</script>
<!-- Content Close -->

<!-- Footer Start -->
<jsp:include page="../include/footer.jsp"></jsp:include>
<!-- Footer Close -->
</body>
</html>
