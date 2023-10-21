<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path1" value="${pageContext.servletContext.contextPath }" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>채팅 테스트</title>
    <jsp:include page="../include/head.jsp"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            var ws = new WebSocket("ws://localhost:8080/pro04_1_war/socket");
            ws.onopen = function(e){ // 연결 시 실행
                console.log("info : connection opened.");
            }
            ws.onmessage = function(e){ // 서버로부터 메세지를 받았을 때 실행
                console.log(e.data); //전달 받은 메세지 = e
                $("#msg").append("<p>"+e.data+"</p>");
            }
            ws.onclose = function(e){ // 연결 종료 시 실행
                console.log("info : connection closed");
            };
            ws.onerror = function(e){
                console.log("error")
            };
            $("#btn").on("click",function(e){
                e.preventDefault();
                ws.send($("#testInput").val());
            });
        });
    </script>
    <jsp:include page="../include/head.jsp"></jsp:include>
</head>
<body>
<div class="container is-fullhd">
    <!-- 헤더 부분 인클루드 -->
    <jsp:include page="../include/header.jsp"></jsp:include>
    <div class="contents">
        <nav aria-label="You are here:" role="navigation">
            <ul class="breadcrumbs">
                <li><a href="#">Home</a></li>
                <li><a href="#">Chat</a></li>
                <li>
                    <span class="show-for-sr">Current: </span> Chat
                </li>
            </ul>
        </nav>
        <h2 class="title">Chatting Page</h2>
        <div class="container" id="chat-area" style="width:900px;margin:20px auto;">
            <input type="text" id="testInput">
            <button type="button" id="btn" class="button">전송</button>
            <div id="msg">

            </div>
        </div>
    </div>
    <!-- 푸터 부분 인클루드 -->
    <jsp:include page="../include/footer.jsp"></jsp:include>
</div>
</body>
</html>