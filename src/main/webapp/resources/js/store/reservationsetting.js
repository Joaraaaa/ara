/**
 * 예약
 */


let s_no = Number($("#s_no").val());
let first = Number($("#first").val());
let last = Number($("#last").val());
let cycle = Number($("#cycle").val());
let table_no = Number($("#table_no").val());

$("#r_btn").on("click",function(){
	let r_date = $("#r_date").val();
	alert(s_no+r_date);
	r_list(s_no,r_date);
	
})

function r_list(s_no,r_date){

	$.getJSON("/reslist",{"s_no":s_no,"r_date":r_date}, function(res) {
//		console.log("목록="+res);
		let list='';
		res.forEach(function(r){
			list+=`<tr>`
			if(r.r_status==1){
				list+=`<td>예약가능</td>`
			}else{
				list+=`<td>예약불가</td>`
			}
			list+=`
					<td>${r.dt_no}</td>
					<td>${r.r_date}</td>
					<td>${r.r_time}</td>
					<td><input type="text" value="${r.people}" id="${r.dt_no}_people" disabled></td>
					<td><a href="/store/list?s_no=${s_no}&dt_no=${r.dt_no}">예약자 목록</a></td>
					<td id="${r.dt_no}">
						<button class="modi_btn">수정</button>
					</td>
					</tr>`
				//					<td><input type="text" value="${r.r_name}" id="${r.rno}_name" disabled></td>
				//					<td><input type="text" value="${r.r_phone}" id="${r.rno}_phone" disabled></td>
				//						<button class="c_btn">취소</button>
				//						<button class="d_btn">삭제</button>
		})
		$("#r_list").html(list);
		$(".modi_btn").on("click",function(){
			
			console.log("dt_no="+$(this).parent().attr("id"));
			$("#"+$(this).parent().attr("id")+"_people").attr("disabled",false);
//			$("#"+$(this).parent().attr("id")+"_name").attr("disabled",false);
//			$("#"+$(this).parent().attr("id")+"_phone").attr("disabled",false);
			
			if($(this).html() == '수정' ) {
				$(this).html('확인');
//			    $("#"+$(this).parent().attr("id")).attr("class","mody_check");
			}else {
				if(confirm("인원을 수정하시겠습니까?")){
				const values={
						"s_no": s_no,
						"dt_no":$(this).parent().attr("id"),
						"people":$("#"+$(this).parent().attr("id")+"_people").val()
						// r_name의 id = rno_name
						// 선택자 $("#rno_name").val()
						// $("#"+$(this).attr("id")+"_name").val()
//						"r_name":$("#"+$(this).parent().attr("id")+"_name").val(),
//						"r_phone":$("#"+$(this).parent().attr("id")+"_phone").val()
				}
				update(values);
				console.log(values);
				$(this).html('수정');
				$("#"+$(this).parent().attr("id")+"_people").attr("disabled",true);
//			    $("#"+$(this).parent().attr("id")).attr("class","modi_btn");
//				$("#"+$(this).parent().attr("id")+"_name").attr("disabled",true);
//				$("#"+$(this).parent().attr("id")+"_phone").attr("disabled",true);

			}
			}
		})
//		$(".c_btn").on("click",function(){
//			if(confirm("취소?")){
//			const values={
//					"rno":$(this).parent().attr("id")
//					
//			}
//			update(values);
//			}
//		})
//		$(".d_btn").on("click",function(){
//			if(confirm("삭제?")){
//			const values={
//					"rno":$(this).parent().attr("id")
//					
//			}
//			remove(values);
//			}
//		})
	})
}
function update(a){
	console.log("a="+a)
	$.ajax({
		type:"put",
		url:"/reservation/peopleupdate",
		data:JSON.stringify(a),
		contentType:"application/json; charset=utf-8",
		success:function(){
			alert("수정성공");
//			r_list(s_no,r_date);
		}
	})
}
//function remove(a){
//	console.log("a="+a)
//	$.ajax({
//		type:"delete",
//		url:"/reservation/delete",
//		data:JSON.stringify(a),
//		contentType:"application/json; charset=utf-8",
//		success:function(){
//			alert("삭제성공");
//			r_list(bno);
//		}
//	})
//}

