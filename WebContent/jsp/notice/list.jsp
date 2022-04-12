<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>공지사항</title>
<c:import url="/include/header.jsp"></c:import>

<style>
/* 게시판 리스트 목록 */

/* //게시판 리스트 목록 */
</style>

<!-- #main -->
<main id="main"> <!-- ======= Breadcrumbs ======= -->
<section class="breadcrumbs">
	<div class="container">
		<div
			class="section-title pt-3 pb-0 d-flex justify-content-between align-items-center">
			<h2>공지사항</h2>
			<ol>
				<li><a href="/main/index.jsp">Home</a></li>
				<li>공지사항</li>
			</ol>
		</div>
	</div>
</section>
<!-- End Breadcrumbs --> 


<!-- ======= Portfolio Details Section ======= -->
<section id="portfolio-details" class="portfolio-details">
	<div class="container">

		<div class="col-lg-10" style="margin: auto">
			<div class="mt-2 mb-2">
				<div class="card-body">
					
					<div align="right">
						<form method="post" action="">
							<select class="form-control mx-1" name="searchType" style="width:100px; display:inline;">
								<option value="all">전체</option>
								<option value="subject">제목</option>
								<option value="content">내용</option>
							</select> 
							<input type="text" name="searchWord" class="mx-1"/>
							<button class="btn btn-secondary btn-xs">검색</button>
						</form>
					</div>


					<div class=row>
						<c:if test="${not empty loginMember}">
							<c:if test='${loginMember.name eq "관리자"}'>
								<button class="btn btn-secondary btn-xs" onclick="location.href=">글쓰기</button>
							</c:if>
						</c:if>
					</div>
					<br>
					
					<table class="table">
						<tr>
							<th style="width:10%">No</th>
							<th style="width:50%">제목</th>
							<th style="width:20%">작성자</th>
							<th style="width:40%">작성일자</th>
						</tr>
						<c:forEach var="notice" items="${noticeList}">
							<tr>
								<td>${notice.noticeno}</td>
								<td>${notice.subject}</td>
								<td><a href="<c:url value="noticedetail?no=${notice.noticeno}"/>">조회</a></td>
							</tr>
						</c:forEach>
					</table>
					
				</div>
			</div>

		</div>
	</div>


</section>


</main>
<!-- End #main -->

<c:import url="/include/footer.jsp"></c:import>