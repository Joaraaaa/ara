/**
 * 예약 상세페이지
 */

//$(".rbtn").on("click",function(){
//	var rno = $(this).attr("id");
//	// 예약 화면으로 넘기기
//	$.getJSON("reservation",{"rno":rno}, function() {
//		
//	})
//})





$("#r_btn").on("click",function(){
	let r_date=$("#r_date").val();
	let s_no=$("#s_no").val();
	let str = '';
	$.getJSON("/reservationlist",{"s_no":s_no,"r_date":r_date}, function(res) {
		res.forEach(function(r,i){
			console.log(r.r_time)
			str+=`
				<tr>
					<td>${r.r_time}시</td>
					<td>${r.r_date}</td>`
			if(r.r_status==false){
			str+=`	<td>예약불가</td></tr>`
			}else{
			str+=`	<td><a href="/normal/reservation?s_no=${r.s_no}&dt_no=${r.dt_no}">예약하기</a></td></tr>`	
			}
		})
	$("#r_tbody").html(str);
	})
})
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

var add= $("#address").val();
console.log(add);
var store= $("#store").val();
// 주소로 좌표를 검색합니다
geocoder.addressSearch(add, function(result, status) {

    // 정상적으로 검색이 완료됐으면 
     if (status === kakao.maps.services.Status.OK) {

        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
        console.log(coords);
        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });

        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+store+'</div>'
        });
        infowindow.open(map, marker);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
    } 
});    

