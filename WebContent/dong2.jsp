<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>template2</title>


<c:import url="/include/header.jsp"></c:import>

<!-- #main -->
<main id="main"> <!-- ======= Breadcrumbs ======= -->
<section class="breadcrumbs">
	<div class="container">
		<div class="section-title pt-3 pb-0 d-flex justify-content-between align-items-center">
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
	<div class="card col-sm-12 mt-1" style="min-height: 850px;">
				<div class="card-body">

<script>
let colorArr = ['table-primary','table-success','table-danger'];
var locations;
</script>

<table id="idx"class="table mt-2">
	<thead>
		<tr>
			<th>번호</th>
			<th>법정동</th>
			<th>아파트이름</th>
			<th>지번</th>
			<th>지역코드</th>
			<th>위도</th>
			<th>경도</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
<!-- here end -->
<!-- map start -->
<div id="map" style="width: 100%; height: 500px; margin: auto;"></div>
<script src="https://unpkg.com/@google/markerclustererplus@4.0.1/dist/markerclustererplus.min.js"></script>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCm_hAb-P76X7Qkeh9y2-FOujkq_Z6JDDs&callback=initMap"></script>
<script>
	var seoul = {lat: 37.551256, lng: 126.988262};
	var map;
	var markers = [];
	function initMap() {
		console.dir()
		map = new google.maps.Map(document.getElementById('map'), {
			center: seoul, zoom: 14
		});
	}
	function addMarker(tmpLat, tmpLng, aptName) {
		var marker = new google.maps.Marker({
			position: new google.maps.LatLng(parseFloat(tmpLat),parseFloat(tmpLng)),
			label: aptName,
			title: aptName
		});
		marker.addListener('click', function() {
			map.setZoom(20);
			map.setCenter(marker.getPosition());
			callHouseDealInfo(aptName);
		});
		marker.setMap(map);
		markers.push(marker);
	}
	function callHouseDealInfo(AptName) {
		console.dir(AptName);
		console.dir("확인");
		$.ajax({
   			url:"selectByName",
   			dataType:"json",
   			data:{AptName:AptName},
   			success:function(deal){
   				let str = "<thead><tr><th>번호</th><th>법정동</th><th>아파트이름</th><th>코드</th><th>거래가격</th><th>준공년도</th><th>면적</th><th>층</th></tr></thead>"
						+ "<tbody>";
   				deal.forEach(function(vo){
   						str +=
   						"<tr class='table-primary'>"
						+ "<td>"+vo.no+"</td>"
						+ "<td>"+vo.dong+"</td>"
						+ "<td>"+vo.aptName+"</td>"
		   				+ "<td>"+vo.code+"</td>"
		   				+ "<td>"+vo.dealAmount+"</td>"
		   				+ "<td>"+vo.buildYear+"</td>"
		   				+ "<td>"+vo.area+"</td>"
		   				+ "<td>"+vo.floor+"</td>"
		   				+ "</tr>";
   					});
   				str += "</tbody>";
		   		console.dir(str);
   				$("#housedeal").html(str);
			}
   		});
	}
	function clearMarkers(){
		for (var i = 0; i < markers.length; i++) {
			markers[i].setMap(null);
		}
		markers = [];
	}
</script>
<!-- map end -->
<div>
	<h3>관심 아파트 상세 정보</h3>
</div>
<table class="table mt-2" id="housedeal">
	<thead>
		<tr>
			<th>번호</th><th>법정동</th><th>아파트이름</th><th>코드</th><th>거래가격</th><th>준공년도</th><th>면적</th><th>층</th>
		</tr>
	</thead>
</table>

				</div>
			</div>
</section>
<!-- End Portfolio Details Section --> </main>
<!-- End #main -->

<c:import url="/include/footer.jsp"></c:import>