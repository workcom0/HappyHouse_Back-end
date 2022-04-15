<%@page import="com.mysql.cj.xdevapi.JsonArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% 
String s = (String) request.getAttribute("jsonData"); 
%>

<script type="text/javascript">
var map, marker;

$(document).ready(function() {
	var s = <%=s%>;
	
	if(s!=null){
		geocode(s);
	}
	
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


});
</script>
