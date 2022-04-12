var map, marker;
$(document).ready(function() {
					// 카카오 지도 설정.
		var container = document.getElementById("map"); // 지도를 담을 dom 객체
		var options = {
			// 지도를 생성할 때 필요한 기본 옵션
			center : new kakao.maps.LatLng(37.5012743, 127.039585), // 지도의 중심좌표																
			level : 3, // 지도의 레벨(확대, 축소 정도)
		};
		
		map = new kakao.maps.Map(container, options);

		// 마커가 표시될 위치입니다
		var markerPosition = new kakao.maps.LatLng(37.5012743, 127.039585);
		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
			position : markerPosition,
			title : "키키키키",
		});
		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);

		// HTML5의 geolocation으로 사용할 수 있는지 확인합니다
		if (navigator.geolocation) {
			// GeoLocation을 이용해서 접속 위치를 얻어옵니다
			navigator.geolocation.getCurrentPosition(function(position) {
				var lat = position.coords.latitude, // 위도
				lon = position.coords.longitude; // 경도

				var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가
																	// 표시될
																	// 위치를
																	// geolocation으로
																	// 얻어온
																	// 좌표로
																	// 생성합니다
				message = '<div style="padding:5px;">우리집!</div>'; // 인포윈도우에
																	// 표시될
																	// 내용입니다

				// 마커와 인포윈도우를 표시합니다
				displayMarker(locPosition, message);
			});
		} else {
			// HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을
			// 설정합니다

			var locPosition = new kakao.maps.LatLng(33.450701, 126.570667), message = "geolocation을 사용할수 없어요..";
			displayMarker(locPosition, message);
		}

		// 지도에 마커와 인포윈도우를 표시하는 함수입니다
		function displayMarker(locPosition, message) {
			// 마커를 생성합니다
			var marker = new kakao.maps.Marker({
				map : map,
				position : locPosition,
			});

			var iwContent = message, // 인포윈도우에 표시할 내용
			iwRemoveable = true;

			// 인포윈도우를 생성합니다
			var infowindow = new kakao.maps.InfoWindow({
				content : iwContent,
				removable : iwRemoveable,
			});

			// 지도 중심좌표를 접속위치로 변경합니다
			map.setCenter(locPosition);
		}
});



