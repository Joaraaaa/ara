var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

function elemArr(arr) {
	var arrObj = [];
	for (var i = 0; i < arr.length; i++) {
	arrObj.push(arr[i].innerHTML);
	}
	return arrObj;
	}

var bno= $(".bno").toArray();
var bArr=elemArr(bno);
var add= $(".address").toArray();
var aArr=elemArr(add);
console.log(aArr);
//console.log(aArr.length);
//console.log(aArr[0]);
//console.log(aArr[1]);
var store= $(".store").toArray();
var sArr=elemArr(store);
console.log(sArr);

for (let j = 0; j < aArr.length; j ++) {
	
// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();	
	geocoder.addressSearch(aArr[j], function(result, status) {

	    // 정상적으로 검색이 완료됐으면 
	     if (status === kakao.maps.services.Status.OK) {
	        var li = {
	        		content: '<div>'+sArr[j]+'</div>', 
	        		latlng: new kakao.maps.LatLng(result[0].y, result[0].x)
		           };
	        console.log(li);
	        var marker = new kakao.maps.Marker({
	            map: map, // 마커를 표시할 지도
	            position: li.latlng, // 마커의 위치
	            clickable: true
	        });
	        console.log(marker);
	        // 마커에 표시할 인포윈도우를 생성합니다 
	        var infowindow = new kakao.maps.InfoWindow({
	            content: li.content // 인포윈도우에 표시할 내용
	        });

	        // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
	        // 이벤트 리스너로는 클로저를 만들어 등록합니다 
	        // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
	        kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
	        kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
	        
	        //마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
	        var iwContent = '<div style="padding:5px; width:200px; height:50px;"><a href="/normal/storedetail?bno='+bArr[j]+'"><div class="store">'+sArr[j]+'바로가기</div></a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
	            iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

	        // 인포윈도우를 생성합니다
	        var infowindow2 = new kakao.maps.InfoWindow({
	            content : iwContent,
	            removable : iwRemoveable
	        });
	        
	        
	        // 마커에 클릭이벤트를 등록합니다
	        kakao.maps.event.addListener(marker, 'click',makeClickListener(map, marker, infowindow2));
	        // 가게로 지도 중심 이동
	        map.setCenter(li.latlng);
	    }
	});    
}




// 마커에 클릭이벤트를 등록합니다
function makeClickListener(map, marker, infowindow) {
      // 마커 위에 인포윈도우를 표시합니다
	return function() {
      infowindow.open(map, marker);  
	};
};

// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
function makeOverListener(map, marker, infowindow) {
    return function() {
        infowindow.open(map, marker);
    };
}

// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
function makeOutListener(infowindow) {
    return function() {
        infowindow.close();
    };
}
