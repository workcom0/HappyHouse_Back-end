<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">

var map, marker;

$(function() {
	var s = <%=request.getAttribute("jsonData")%>
	
	if(s!=null){
		geocode(s);
	}
	
	function geocode(jsonData) {
		let idx = 0;
		let tmpLat;
		let tmpLng;
		
		var mapContainer = document.getElementById('map'), // ì§ëë¥¼ íìí  div  	   
		mapOption = { 
	        center: new kakao.maps.LatLng(jsonData[0].lat, jsonData[0].lng), // ì§ëì ì¤ì¬ì¢í
	        level: 3 // ì§ëì íë ë ë²¨
	    };

		map = new kakao.maps.Map(mapContainer, mapOption);

		var positions = [];
		
		for(var i=0; i<jsonData.length; i++){
			positions[i] = {
				title:jsonData[i].aptName,
				latlng: new kakao.maps.LatLng(jsonData[i].lat, jsonData[i].lng)
			};
		}
		
		var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
		
		for (var i = 0; i < positions.length; i ++) {
		    
		    // ë§ì»¤ ì´ë¯¸ì§ì ì´ë¯¸ì§ í¬ê¸° ìëë¤
		    var imageSize = new kakao.maps.Size(24, 35); 
		    
		    // ë§ì»¤ ì´ë¯¸ì§ë¥¼ ìì±í©ëë¤    
		    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
		    
		    // ë§ì»¤ë¥¼ ìì±í©ëë¤
		    var marker = new kakao.maps.Marker({
		        map: map, // ë§ì»¤ë¥¼ íìí  ì§ë
		        position: positions[i].latlng, // ë§ì»¤ë¥¼ íìí  ìì¹
		        title : positions[i].title, // ë§ì»¤ì íì´í, ë§ì»¤ì ë§ì°ì¤ë¥¼ ì¬ë¦¬ë©´ íì´íì´ íìë©ëë¤
		        image : markerImage // ë§ì»¤ ì´ë¯¸ì§ 
		    });
		    marker.setMap(map);
		}
		console.log(jsonData[0].lat);
	}
});

function panTo() {

	var lat = $(this).attr('lat');
	var lng = $(this).attr('lng');
    // ì´ëí  ìë ê²½ë ìì¹ë¥¼ ìì±í©ëë¤ 
    var moveLatLon = new kakao.maps.LatLng(lat, lng);
    
    // ì§ë ì¤ì¬ì ë¶ëë½ê² ì´ëìíµëë¤
    // ë§ì½ ì´ëí  ê±°ë¦¬ê° ì§ë íë©´ë³´ë¤ í¬ë©´ ë¶ëë¬ì´ í¨ê³¼ ìì´ ì´ëí©ëë¤
    map.setCenter(moveLatLon);            
}

</script>