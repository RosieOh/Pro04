<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*, java.lang.*" %>
<%@ page import="java.text.*, java.net.InetAddress" %>
<c:set var="path1" value="${pageContext.servletContext.contextPath }" />

<header class="navigation">
  <div class="header-top">
    <div class="container" style="padding: 9px 15px">
      <div class="columns is-gapless is-justify-content-space-between is-align-items-center">
        <div class="column is-6-desktop is-4-tablet has-text-left-desktop has-text-centered-mobile">
        </div>
        <div class="column is-6-desktop is-8-tablet">
          <div class="header-top-right has-text-right-tablet has-text-centered-mobile">
            <%--<a href="https://www.facebook.com/themefisher" target="_blank"><i class="icofont-facebook"></i></a>
            <a href="https://github.com/themefisher/" target="_blank"><i class="icofont-github"></i></a>
            <a href="#" target="_blank"><i class="icofont-linkedin"></i></a>--%>
            <a href="#">
              <i class="icofont-login has-text-white"> 로그인</i>
            </a>
            <a href="#">
              <i class="icofont-user has-text-white"> 회원가입</i>
            </a>
            <a href="#">
              <i class="icofont-google-map has-text-white"> 오시는길</i>
            </a>
            <%--<a href="donation.html" class="top-btn">Donate Now</a>--%>
          </div>
        </div>
      </div>
    </div>
  </div>
  <nav id="navbar" class="navbar main-nav">
    <div class="container">
      <div class="navbar-brand">
        <a class="navbar-item" href="index.html">
          <img src="${path1}/resources/images/haebeop.png" alt="logo">
        </a>
        <button role="button" class="navbar-burger burger" data-hidden="true" data-target="navigation">
          <span aria-hidden="true"></span>
          <span aria-hidden="true"></span>
          <span aria-hidden="true"></span>
        </button>
      </div>

      <div class="navbar-menu mr-0" id="navigation">
        <ul class="navbar-start" style="flex-grow: 1; justify-content: center;">
          <li class="navbar-item has-dropdown is-hoverable">
            <a class="navbar-link">수강신청</a>
            <div class="navbar-dropdown">
              <a class="navbar-item" href="#">수강신청</a>
              <a class="navbar-item" href="#">개강일정</a>
            </div>
          </li>

          <li class="navbar-item">
            <a class="navbar-link" href="#">무료강의</a>
          </li>

          <li class="navbar-item has-dropdown is-hoverable">
            <a class="navbar-link">커뮤니티</a>
            <div class="navbar-dropdown">
              <a class="navbar-item" href="#">공지사항</a>
              <a class="navbar-item" href="#">자주 묻는 질문</a>
              <a class="navbar-item" href="#">후기</a>
            </div>
          </li>

          <li class="navbar-item">
            <a class="navbar-link" href="#">자료실</a>
          </li>
          <%--<li class="navbar-item">
              <a class="navbar-link" href="contact.html">Contact</a>
          </li>--%>
        </ul>
      </div>
    </div>
  </nav>
</header>