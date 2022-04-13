<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
let colorArr = ['table-primary','table-success','table-danger'];
var locations;

	$(document).ready(function(){	
		$.ajax({
			url: "/main/apt",
			type:"post",
			dataType:'json',
			data:{action:"sido"},
			contentType:'application/x-www-form-urlencoded; charset=UTF-8',
			success:function(data){
				$.each(data, function(index, vo) {
					$("#sido").append("<option value='"+vo.sido_code+"'>"+vo.sido_name+"</option>");
				});//each			
			}, error:function(){
				alert("에러입니다.");
			}
		});
	});//ready
	
	$(document).ready(function(){
		$("#sido").change(function() {
			$.get("/main/apt"
					,{action:"gugun", sido:$("#sido").val()}
					,function(data, status){
						$("#gugun").empty();
						$("#gugun").append('<option value="0">선택</option>');
						$.each(data, function(index, vo) {
							$("#gugun").append("<option value='"+vo.gugun_code+"'>"+vo.gugun_name+"</option>");
						});//each
					}//function
					, "json"
			);//get
		});//change
		$("#gugun").change(function() {
			$.get("/main/apt"
					,{action:"dong", gugun:$("#gugun").val()}
					,function(data, status){
						$("#dong").empty();
						$("#dong").append('<option value="0">선택</option>');
						$.each(data, function(index, vo) {
							$("#dong").append("<option value='"+vo.dong_code+"'>"+vo.dong_name+"</option>");
						});//each
					}//function
					, "json"
			);//get
		});//change
		$("#dong").change(function() {
			$.get("/main/apt"
					,{action:"apt", dong:$("#dong").val()}
					,function(data, status){
						$("#searchResult").empty();
						$("#idx>tbody").empty();

						$.each(data, function(index, vo) {
							let str = "<tr class="+colorArr[index%3]+">"
							+ "<td>" + vo.aptCode + "</td>"
							+ "<td>" + vo.aptName + "</td>"
							+ "<td>" + vo.dongName + "</td>"
							+ "<td>" + vo.jibun + "</td>"
							+ "<td id='lat_"+index+"'>" + vo.lat + "+</td><td id='lng_"+index+"'>" + vo.lng + "</td>"
							+ "</tr>";
							$("#idx>tbody").append(str);
							$("#searchResult").append(vo.dong+" "+vo.AptName+" "+vo.jibun+"<br>");
						});//each
						geocode(data);
					}//function
					, "json"
			);//get
		});//change
	});//ready	

	function geocode(jsonData) {
		let idx = 0;
		let tmpLat;
		let tmpLng;
		
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div  	   
		mapOption = { 
	        center: new kakao.maps.LatLng(jsonData[0].lat, jsonData[0].lng), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };
		
		var map = new kakao.maps.Map(mapContainer, mapOption);
		
		var positions = [];
		
		for(var i=0; i<jsonData.length; i++){
			positions[i] = {
				title:jsonData[i].aptName,
				latlng: new kakao.maps.LatLng(jsonData[i].lat, jsonData[i].lng)
			};
		}
		
		var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
		
		for (var i = 0; i < positions.length; i ++) {
		    
		    // 마커 이미지의 이미지 크기 입니다
		    var imageSize = new kakao.maps.Size(24, 35); 
		    
		    // 마커 이미지를 생성합니다    
		    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
		    
		    // 마커를 생성합니다
		    var marker = new kakao.maps.Marker({
		        map: map, // 마커를 표시할 지도
		        position: positions[i].latlng, // 마커를 표시할 위치
		        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
		        image : markerImage // 마커 이미지 
		    });
		    marker.setMap(map);
		}
		console.log(jsonData[0].lat);
	}
	
</script>

<div>
	<div class="alert alert-primary ">
		<strong id="tradeInfo">아파트 실거래가 조회</strong> 지역별 매매정보
	</div>
	<div align="left">
		<form id="" class="text-left mb-3" method="post" action="">
		<input type="hidden" name="action" value="search" /> 
			<select class="" id="sido" name="sido">
				<option value="">시도선택</option>
			</select> 
			<select class="" id="gugun" name="gugun">
				<option value="">구군선택</option>
			</select> 
			<select class="" id="dong" name="dong">
				<option value="">동선택</option>
			</select>
	</form>
	</div>
</div>

