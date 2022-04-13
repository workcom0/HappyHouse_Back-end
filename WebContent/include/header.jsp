<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/include/style.jsp"></c:import>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

  <!-- ======= Header ======= -->
  <header id="header" class="fixed-top header-inner-pages">
    <div class="container d-flex align-items-center justify-content-lg-between">

      <h1 class="logo me-auto me-lg-0"><a href="/main/index.jsp">Happy House<span>.</span></a></h1>
      <!-- Uncomment below if you prefer to use an image logo -->
      <!-- <a href="index.html" class="logo me-auto me-lg-0"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->

      <nav id="navbar" class="navbar order-last order-lg-0">
        <ul>
          <li><a class="nav-link scrollto" href="/main/index.jsp">Home</a></li>
          <li><a class="nav-link scrollto" href="/main/about.jsp">About</a></li>
          <li><a class="nav-link scrollto" href="/main/notice?act=list&pg=1&key=&word=&">공지사항</a></li>
          <li><a class="nav-link scrollto " href="/main/dong.jsp">거래가검색</a></li>
<!-- *************************** 회원 화면 *********************************** -->
<c:if test="${!empty userInfo}">
		<li class="dropdown"><a href="/main/user?act=info"><span>마이페이지</span> <i class="bi bi-chevron-down"></i></a>
            <ul>
              <li><a href="/main/user?act=info">나의정보관리</a></li>
              <li><a href="#">관심지역설정</a></li>
              <li><a href="#">회원탈퇴</a></li>
            </ul>
          </li>
</c:if>          
<!-- ********************************************************************* -->
        </ul>
        <i class="bi bi-list mobile-nav-toggle"></i>
      </nav>
<!-- .navbar -->


<!-- *************************** 비회원 화면 *********************************** -->
<c:if test="${empty userInfo}">
      <a href="/main/jsp/user/login.jsp" class="get-started-btn scrollto">로그인</a>
</c:if>
<!-- *********************************************************************** -->

<!-- *************************** 회원 화면 *********************************** -->
<c:if test="${!empty userInfo}">
      <a href="/main/user?act=logout" class="get-started-btn scrollto">로그아웃</a>
</c:if>
<!-- ********************************************************************* -->
    </div>
  </header>
  
<!-- End Header -->