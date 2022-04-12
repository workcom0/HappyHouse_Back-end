<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>공지사항</title>
<c:import url="/include/header.jsp"></c:import>


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

<style>
	h3{
		padding-bottom: 10px;
		border-bottom: 1px solid gray;
	}
</style>
<!-- ======= Portfolio Details Section ======= -->
<section id="portfolio-details" class="portfolio-details">
	<div class="container" >
		<div class="col-lg-6" align="center" style="margin:auto">
			
			<h3 class="card-header">공지사항 상세</h3>
						<div class="card-body">
							<div>
								<form method="post" action="insertNotice">
									<table class="table">
										<tr>
											<th>제목</th>
											<td>${notice.subject}</td>
										</tr>
										<tr>
											<th>작성자</th>
											<td>${notice.adminid}</td>
										</tr>
										<tr>
											<th>내용</th>
											<td>${notice.content}</td>
										</tr>
									</table>

									<div>
										<c:if test="${not empty loginMember}">
											<c:if test='${loginMember.name eq "관리자"}'>
												<div>
													<a href="modifyNotice?no=${notice.noticeno}">수정</a> <a
														href="deleteNotice?no=${notice.noticeno}">삭제</a>
												</div>
											</c:if>
										</c:if>
										<div class="text-right">
											<a href="noticelist">목록</a>
<!-- 											<button class="btn btn-secondary btn-xs" -->
<!-- 												onclick="#">확인</button> -->
										</div>
									</div>
								</form>
							</div>
			
			
			
		</div>
	</div>
</section>
<!-- End Portfolio Details --> 

</main>
<!-- End #main -->

<c:import url="/include/footer.jsp"></c:import>