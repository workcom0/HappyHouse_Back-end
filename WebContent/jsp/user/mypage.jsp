<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>마이페이지</title>
<c:import url="/include/header.jsp"></c:import>

<c:if test="${empty userInfo}">
	alert("로그인 후 이용하세요");
	location.href='/main/index.jsp';
</c:if>

<!-- #main -->
<main id="main"> 

<!-- ======= Breadcrumbs ======= -->
<section class="breadcrumbs">
<div class="container">
	<div class="section-title pt-3 pb-0 d-flex justify-content-between align-items-center">
          <h2>나의정보관리</h2>
          <ol>
				<li><a href="/main/index.jsp">Home</a></li>
				<li>나의정보관리</li>
			</ol>
	</div>
</div>
</section>
<!-- End Breadcrumbs --> 

<!-- ======= Portfolio Details Section ======= -->
<section id="portfolio-details" class="portfolio-details">
	<div class="container">
		<div class="row gy-4 justify-content-center">
			<div class="col-lg-8 portfolio-info p-3">
				<form action="/main/user" method="post" class="bg-white rounded shadow-5-strong p-5">
					<div class="row">
						<div class="mb-3">
							<label for="id">아이디</label> 
							<input type="text" class="form-control" id="id" name="id" placeholder="" value="${dto.id}" readonly="readonly">
						</div>
						<div class="mb-3">
							<label for="pw">비밀번호<span style="color: red;"> *</span></label>
							<input type="password" class="form-control" id="pw" name="pw" value="${dto.pw}" required>
						
						</div>
						<div class="mb-3">
							<label for="name">이름<span style="color: red;"> *</span></label> 
							<input type="text" class="form-control" id="name" name="name" value="${dto.name}" required>
							
						</div>
						<div class="mb-3">
							<label for="email">e-mail<span style="color: red;"> *</span></label>
							<input type="email" class="form-control" id="email" name="email" value="${dto.email}" required>
							
						</div>
						<div class="mb-3">
							<label for="phone">전화번호<span style="color: red;"> *</span></label> 
							<input type="text" class="form-control" id="phone" name="phone" value="${dto.phone}" required>
							
						</div>
					</div>
					<div class="d-grid gap-2 d-md-flex mt-3 justify-content-between">
						<button id="delete" data-bs-toggle="modal" data-bs-target="#withdrawal-Modal" class="btn btn-secondary btn-lg btn-block" name="act" value="delete">탈퇴하기</button>
						<button id="modify" type="submit" class="btn btn-primary btn-lg btn-block" name="act" value="modify">수정하기</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>
<!-- End Portfolio Details Section --> 



</main>
<!-- End #main -->

<c:import url="/include/footer.jsp"></c:import>