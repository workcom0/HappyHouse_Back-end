<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>회원가입</title>
<c:import url="/include/header.jsp"></c:import>


<!-- #main -->
<main id="main"> 

<!-- ======= Breadcrumbs ======= -->
<section class="breadcrumbs">
<div class="container">
	<div class="section-title pt-3 pb-0">
          <h2>Portfolio</h2>
          <p>회원가입</p>
	</div>
</div>
</section>
<!-- End Breadcrumbs --> 

<!-- ======= Portfolio Details Section ======= -->
<section id="portfolio-details" class="portfolio-details">
	<div class="container">
		<div class="row gy-4 justify-content-between">
			<div class="col-lg-7 portfolio-info p-3">
				<form method="Post" action="/main/user" class="bg-white rounded shadow-5-strong p-5">
				<input type="hidden" name="act" value="register">
					<div class="row">
						<div class="mb-4">
							<label for="id">아이디<span style="color:red;"> *</span></label> 
							<input type="text" class="form-control" id="id" name="id" required>
							<!-- <p6 style="color: red;">이미 등록된 아이디입니다.</p6> -->
						</div>
						<div class="mb-4">
							<label for="pw">비밀번호<span style="color:red;"> *</span></label>
							<input type="password" class="form-control" id="pw" name="pw" required>
						</div>
						<div class="mb-4">
							<label for="name">이름<span style="color:red;"> *</span></label> 
							<input type="text" class="form-control" id="name" name="name" required>
						</div>
						<div class="mb-4">
							<label for="email">e-mail<span style="color:red;"> *</span></label>
							<input type="email" class="form-control" id="email" name="email" placeholder="you@example.com" required>
						</div>
						<div class="mb-4">
							<label for="phone">전화번호<span style="color:red;"> *</span></label> 
							<input type="text" class="form-control" id="phone" name="phone" placeholder="010-xxxx-xxxx" required>
						</div>
					</div>
					<div class="custom-control custom-checkbox">
						<input type="checkbox" class="custom-control-input" id="agreement" required> 
						<label class="custom-control-label" for="aggrement">개인정보 수집 및 이용에 동의합니다.</label>
					</div>
					<div class="d-grid gap-2 d-md-flex justify-content-md-end mt-3">
						<button type="submit" class="btn btn-primary btn-lg btn-block">가입하기</button>
					</div>
				</form>
			</div>

			<div class="col-lg-4">
				<div class="portfolio-info p-5 mb-5">
					<h3>■ 관심지역 설정</h3>
					<ul>
						<li><strong>Category</strong>: Web design</li>
						<li><strong>Client</strong>: ASU Company</li>
						<li><strong>Project date</strong>: 01 March, 2020</li>
						<li><strong>Project URL</strong>: <a href="#">www.example.com</a></li>
					</ul>
				</div>
				<div class="portfolio-info p-5 mt-10">
					<h3>■ 개인정보 수집 동의</h3>
					<p>
					<strong>1. 수집 항목</strong><br>
						아이디, 비밀번호, 이름, 이메일, 전화번호, 기타 서비스 이용 과정에서 생성되는 정보<br><br>
					<strong>2. 수집 목적</strong><br>
						회원관리(본인확인, 개인 식별, 가입 의사 확인, 가입 및 가입횟수 제한)<br><br>
					<strong>3. 보유 및 이용 기간</strong><br>
						회원 탈퇴시까지. 다만, 장기(1년) 미접속의 경우 사전 안내 후 탈회 및 삭제 처리<br>
					</p>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- End Portfolio Details Section --> 

</main>
<!-- End #main -->

<c:import url="/include/footer.jsp"></c:import>