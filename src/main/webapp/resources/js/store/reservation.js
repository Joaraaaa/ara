/**
 * 예약
 */


let bno = $("#bno").val();
let first = Number($("#first").val());
let last = Number($("#last").val());
let cycle = Number($("#cycle").val());
let table_no = Number($("#table_no").val());
//let date = $("#date").val();

//console.log(first);
//console.log(last);
//console.log(cycle);
//console.log(table_no);

$("#date").on("change",function(){
	let date=$("#date").val();
	console.log(date);
	time(first,last,cycle,table_no,date);
})
timelist(bno);
function timelist(bno){
	$.getJSON("/reslist",{bno},function(data){
		console.log(data);
	})
}

//time(first,last,cycle,table_no,date);
function time(first,last,cycle,table_no,date){
	console.log(first);
	console.log(last);
	console.log(cycle);
	let str="";
	for(let i=first;i<=last;i+=cycle){
		console.log(i+"시 예약");
		for(let j=1;j<=table_no;j++){
//			let data = {"bno":bno,"date":date,"tno":j,"r_time":i};
//			let rno=date+bno+i+j;
//			console.log(rno);
//			str+=`${bno}/${date}/${i}시 ${j}번 테이블<a href="/reservationmodify?bno=${bno}&date=${date}&r_time=${i}&tno=${j}">예약 변경</a><br>`
			$.getJSON("/reservationsetting",{"rno":date+bno+i+j,bno,date,"tno":j,"r_time":i}, function(res) {
				console.log("목록="+res);
			})	
			
		}
	}
//	console.log(str);
//	$("#r_list").html(str);
}