/**
 * 
 */

$("#find_r").on("click",function(){
	var r_name=$("#r_name").val();
	var r_phone=$("#r_phone").val();
	console.log(r_name);
	console.log(r_phone);
	$.getJSON("/findreservation",{"r_name":r_name,"r_phone":r_phone}, function(res){
		console.log(res);
		let list='';
		res.forEach(function(r){
			list+=`<div>
				<a href="/normal/storedetail?bno=${r.svo.bno}">${r.svo.store} 바로가기</a>
				<p>예약일 : ${r.date}</p>
				<p>${r.email}</p>
				<p>${r.r_name}</p>
				<p>${r.r_phone}</p>
				</div>`
		})
		$("#r_list").html(list);
	})
})