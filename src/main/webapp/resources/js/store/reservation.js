/**
 * 예약
 */


let bno = Number($("#bno").val());
let first = Number($("#first").val());
let last = Number($("#last").val());
let cycle = Number($("#cycle").val());
let table_no = Number($("#table_no").val());
r_list(bno);
function r_list(bno){

	$.getJSON("/reslist",{"bno":bno}, function(res) {
//		console.log("목록="+res);
		let list='';
		res.forEach(function(r){
			if(r.r_status==0){
				list+=`<tr><td>예약가능</td>`
			}else{
				list+=`<tr><td>예약불가</td>`
			}
			list+=`${r.r_status}</td>
					<td>${r.rno}</td>
					<td>${r.date}</td>
					<td>${r.r_time}</td>
					<td>${r.tno}</td>
					<td id="${r.rno}_email">${r.email}</td>
					<td>${r.r_name}</td>
					<td>${r.r_phone}</td>
					<td><button class="modi_btn" id="${r.rno}">수정</button></td>
					<td><a href="delete/${r.rno}">삭제</a></td>
				</tr>`
		})
		$("#r_list").html(list);
		$(".modi_btn").on("click",function(){
			let rno=$(this).attr("id");
			
			console.log("rno="+rno);
			$getJSON("/store/rmodify/${rno}",function(data){
				console.log(data);
			})
		})
	})
}

