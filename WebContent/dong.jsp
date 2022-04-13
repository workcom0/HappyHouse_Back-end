<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>template2</title>

<c:import url="/include/header.jsp"></c:import>

<!-- map -->
<script type="text/javascript" src="./assets/js/map.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2c05fbececc1c861482cc4c9053dd42d&libraries=services"></script>
<!-- map -->

<!-- #main -->
<main id="main"> <!-- ======= Breadcrumbs ======= -->
<section class="breadcrumbs">
	<div class="container">
		<div
			class="section-title pt-3 pb-0 d-flex justify-content-between align-items-center">
			<h2>거래가 검색</h2>
			<ol>
				<li><a href="index.jsp">Home</a></li>
				<li>거래가 검색</li>
			</ol>
		</div>

	</div>
</section>
<!-- End Breadcrumbs --> <!-- ======= Portfolio Details Section ======= -->
<section id="portfolio-details" class="portfolio-details">
	<div class="container">

		<!-- ##### sigungu start #####-->
		<c:import url="sigungu.jsp" />
		<!-- ##### sigungu end #####-->


		<!-- ##### trade-main start #####-->
		<div class="row ">
			<div class="col-lg-5">
				<h3 id="tradeInfo">거래 정보</h3>
				<table id="idx" class="table mt-2">
					<thead>
						<tr>
							<th>번호</th>							
							<th>아파트이름</th>
							<th>법정동</th>
							<th>지번</th>
							<th>위도</th>
							<th>경도</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>

			<div class="col-lg-7">
				<h3 id="tradeInfo">Map</h3>
				<br>
				<div id="map" class="mt-2" style="width: 100%; height: 500px"></div>
			</div>
		</div>
		<!-- ##### trade-main end #####-->

	</div>
</section>
<!-- End Portfolio Details Section --> </main>
<!-- End #main -->

<c:import url="/include/footer.jsp"></c:import>