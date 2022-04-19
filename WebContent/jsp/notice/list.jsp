<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>공지사항</title>
<c:import url="/include/header.jsp"></c:import>

<script type="text/javascript">

        $(document).ready(function () {
        	$("#mvRegisterBtn").click(function () {
                location.href = "/main/notice?act=mvregister";
            });
        	
        	$(".page-item").click(function () {
               	$("#pg").val($(this).attr("data-pg"));
               	$("#pageForm").attr("action", "/main/notice").submit();
            });
        });
        
</script>
<form name="pageForm" id="pageForm">
    <input type="hidden" name="act" id="act" value="list" />
    <input type="hidden" name="pg" id="pg" value="" />
    <input type="hidden" name="key" id="key" value="${key}" />
    <input type="hidden" name="word" id="word" value="${word}" />
</form>

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
						<form method="post" action="/main/notice">
							<input type="hidden" name="act" value="list">
		            		<input type="hidden" name="pg" value="1">
							<select class="form-control mx-1" name="key" style="width:100px; display:inline;">
								<option value="all">전체</option>
								<option value="subject">제목</option>
								<option value="content">내용</option>
							</select> 
							<input name="word" type="text" name="searchWord" class="mx-1"/>
							<button class="btn btn-secondary btn-xs">검색</button>
						</form>
					</div>


					<div class="row" style="width: 100px;">
						<c:if test="${not empty userInfo}">
							<c:if test='${userInfo.name eq "관리자"}'>
								<button id="mvRegisterBtn" class="btn btn-secondary btn-xs">글쓰기</button>
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
								<td>${notice.articleNo}</td>
								<td>${notice.subject}</td>
								<td>${notice.userId}</td>
								<td>${notice.regTime}</td>
							</tr>
						</c:forEach>
					</table>					
				</div>
			</div>
			<div style="width: 50%; margin:auto">${navi.navigator}</div>		
		</div>
	</div>


</section>


</main>
<!-- End #main -->

<c:import url="/include/footer.jsp"></c:import>