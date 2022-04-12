<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>로그인</title>
<c:import url="/include/header.jsp"></c:import>


<!-- #main -->
<main id="main"> 

<!-- ======= Breadcrumbs ======= -->
<section class="breadcrumbs">
<div class="container">
	<div class="section-title pt-3 pb-0 d-flex justify-content-between align-items-center">
          <h2>로그인</h2>
          <ol>
				<li><a href="/main/index.jsp">Home</a></li>
				<li>로그인</li>
			</ol>
	</div>
</div>
</section>
<!-- End Breadcrumbs --> 

<!-- ======= 로그인  Section ======= -->
<section id="portfolio-details" class="portfolio-details">
	<div class="container">
		<div class="row gy-4 d-flex align-items-center justify-content-center">
			<div class="col-lg-8 col-xl-5 col-md-8">
					<div class="justify-content-center portfolio-info">
					
						<form method="post" action="/main/user" class="bg-white rounded shadow-5-strong p-5">
						<input type="hidden" name="act" value="login"> 
							<!-- id input -->
							<div class="form-outline mb-4">
								<input type="text" class="form-control" id="id" name="id" required></input>
								<label class="form-label" for="id">아이디</label>
							</div>
							
							<!-- pw input -->
							<div class="form-outline mb-4">
								<input type="password" id="pw" class="form-control" name="pw" required="required"/>
								<label class="form-label" for="pw">비밀번호</label>
							</div>
							
							<c:if test="${msg eq 'f'}">
								<div>
									<p style="color: red;">아이디 또는 비밀번호가 틀렸습니다.</p>
								</div>
							</c:if>
							
							
							<!-- 2 column grid layout for inline styling -->
							<div class="row mb-4">
								<div class="col d-flex justify-content-center">
									<!-- Checkbox -->
									<div class="form-check">
										<input class="form-check-input" type="checkbox" value="" id="" checked /> 
										<label class="form-check-label" for="">아이디 저장</label>
									</div>
								</div>
								<div class="col text-center">
									<!-- Simple link -->
									<a href="#!">비밀번호 찾기</a>
								</div>
							</div>
							
							<!-- Submit button -->
							<div class="d-grid gap-2">
								<button type="submit" id="btn-login" class="btn btn-secondary btn-block">로그인</button>
								<input type="button" value="회원가입" class="btn btn-secondary btn-block" onclick="location='./join.jsp'">
							</div>
						</form>
						
				   </div>
			 </div>
		</div>
	</div>
</section>
<!-- End Portfolio Details Section --> 

</main>
<!-- End #main -->

<c:import url="/include/footer.jsp"></c:import>