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


var add= $(".address").toArray();
var aArr=elemArr(add);
console.log(aArr);
console.log(aArr.length);
console.log(aArr[0]);
console.log(aArr[1]);
var store= $(".store").toArray();
var sArr=elemArr(store);
console.log(sArr);
var li=[];
// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();	
for (let i = 0; i < aArr.length; i ++) {
	console.log(i);
	console.log("1번 - "+aArr[i]);
	geocoder.addressSearch(aArr[i], function(result, status) {
		console.log("2번 " +aArr[i])
	    // 정상적으로 검색이 완료됐으면 
	     if (status === kakao.maps.services.Status.OK) {
	        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	        console.log("3번 - "+coords);
	     // 마커를 생성합니다
	        var marker = new kakao.maps.Marker({
	            map: map, // 마커를 표시할 지도
	            position: coords // 마커의 위치
	        });
	
	        // 마커에 표시할 인포윈도우를 생성합니다 
	        var infowindow = new kakao.maps.InfoWindow({
	            content: '<div>'+sArr[i]+'</div>' // 인포윈도우에 표시할 내용
	        });
	
	        // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
	        // 이벤트 리스너로는 클로저를 만들어 등록합니다 
	        // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
	        kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
	        kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
	        
	    }
	});    
}

//// 마커를 표시할 위치와 내용을 가지고 있는 객체 배열입니다 
//var positions = [
//    {
//        content: '<div>우리가게</div>', 
//        latlng: new kakao.maps.LatLng(33.450705, 126.570677)
//    }
//];
//
//
//for (var i = 0; i < positions.length; i ++) {
//    // 마커를 생성합니다
//    var marker = new kakao.maps.Marker({
//        map: map, // 마커를 표시할 지도
//        position: positions[i].latlng // 마커의 위치
//    });
//
//    // 마커에 표시할 인포윈도우를 생성합니다 
//    var infowindow = new kakao.maps.InfoWindow({
//        content: positions[i].content // 인포윈도우에 표시할 내용
//    });
//
//    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
//    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
//    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
//    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
//    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
//}

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

/* 아래와 같이도 할 수 있습니다 */
/*
for (var i = 0; i < positions.length; i ++) {
    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: positions[i].latlng // 마커의 위치
    });

    // 마커에 표시할 인포윈도우를 생성합니다 
    var infowindow = new kakao.maps.InfoWindow({
        content: positions[i].content // 인포윈도우에 표시할 내용
    });

    // 마커에 이벤트를 등록하는 함수 만들고 즉시 호출하여 클로저를 만듭니다
    // 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
    (function(marker, infowindow) {
        // 마커에 mouseover 이벤트를 등록하고 마우스 오버 시 인포윈도우를 표시합니다 
        kakao.maps.event.addListener(marker, 'mouseover', function() {
            infowindow.open(map, marker);
        });

        // 마커에 mouseout 이벤트를 등록하고 마우스 아웃 시 인포윈도우를 닫습니다
        kakao.maps.event.addListener(marker, 'mouseout', function() {
            infowindow.close();
        });
    })(marker, infowindow);
}
*/