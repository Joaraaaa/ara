/**
 * 예약
 */


let bno = Number($("#bno").val());
let first = Number($("#first").val());
let last = Number($("#last").val());
let cycle = Number($("#cycle").val());
let table_no = Number($("#table_no").val());

$("#r_btn").on("click",function(){
	let date = $("#date").val();
	alert(bno+date);
	r_list(bno,date);
	
})

function r_list(bno,date){

	$.getJSON("/reslist",{"bno":bno,"date":date}, function(res) {
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
					<td>${r.rno}</td>
					<td>${r.date}</td>
					<td>${r.r_time}</td>
					<td>${r.people}</td>
					<td><a href="/store/list?rno=${r.rno}">예약자 목록</a></td>
					<td><input type="text" value="${r.r_name}" id="${r.rno}_name" disabled></td>
					<td><input type="text" value="${r.r_phone}" id="${r.rno}_phone" disabled></td>
					<td id="${r.rno}">
						<button class="modi_btn">수정</button>
						<button class="c_btn">취소</button>
						<button class="d_btn">삭제</button>
					</td>
					</tr>`
		})
		$("#r_list").html(list);
		$(".modi_btn").on("click",function(){
			
			console.log("rno="+$(this).parent().attr("id"));
			$("#"+$(this).parent().attr("id")+"_name").attr("disabled",false);
			$("#"+$(this).parent().attr("id")+"_phone").attr("disabled",false);
			
			if($(this).html() == '수정' ) {
				$(this).html('확인');
//			    $("#"+$(this).parent().attr("id")).attr("class","mody_check");
			}else {
				if(confirm("수정?")){
				const values={
						"rno":$(this).parent().attr("id"),
						// r_name의 id = rno_name
						// 선택자 $("#rno_name").val()
						// $("#"+$(this).attr("id")+"_name").val()
						"r_name":$("#"+$(this).parent().attr("id")+"_name").val(),
						"r_phone":$("#"+$(this).parent().attr("id")+"_phone").val()
				}
				update(values);
				$(this).html('수정');
//			    $("#"+$(this).parent().attr("id")).attr("class","modi_btn");
				$("#"+$(this).parent().attr("id")+"_name").attr("disabled",true);
				$("#"+$(this).parent().attr("id")+"_phone").attr("disabled",true);

			}
			}
		})
		$(".c_btn").on("click",function(){
			if(confirm("취소?")){
			const values={
					"rno":$(this).parent().attr("id")
					
			}
			update(values);
			}
		})
		$(".d_btn").on("click",function(){
			if(confirm("삭제?")){
			const values={
					"rno":$(this).parent().attr("id")
					
			}
			remove(values);
			}
		})
	})
}
function update(a){
	console.log("a="+a)
	$.ajax({
		type:"put",
		url:"/reservation/update",
		data:JSON.stringify(a),
		contentType:"application/json; charset=utf-8",
		success:function(){
			alert("수정성공");
			r_list(bno);
		}
	})
}
function remove(a){
	console.log("a="+a)
	$.ajax({
		type:"delete",
		url:"/reservation/delete",
		data:JSON.stringify(a),
		contentType:"application/json; charset=utf-8",
		success:function(){
			alert("삭제성공");
			r_list(bno);
		}
	})
}

