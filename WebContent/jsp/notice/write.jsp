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
			<h3>공지사항 등록</h3>
			<form id="" method="post" action="">
				<input type="hidden" name="act" id="act" value="write">
				<div class="form-group mb-3 mt-3" align="left">
					<label for="subject"></label> 
					<input type="text" class="form-control" id="subject" name="subject" placeholder="제목을 입력해 주세요." required="required">
				</div>
				<div class="form-group mb-3" align="left">
					<label for="content"></label>
					<textarea class="form-control" rows="15" id="content" name="content" placeholder="내용을 입력해 주세요." required="required"></textarea>
				</div>
				<div>
					<button type="reset" class="btn btn-secondary  m-3">취소</button>
					<button type="button" class="btn btn-warning" onclick="javascript:writeNotice();">글작성</button>
				</div>		
			</form>
		</div>
	</div>
</section>
<!-- End Portfolio Details --> 

</main>
<!-- End #main -->

<c:import url="/include/footer.jsp"></c:import>