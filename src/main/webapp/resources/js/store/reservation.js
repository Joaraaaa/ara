/**
 * 예약
 */

let first = $("#first").val();
let last = $("#last").val();
let cycle = $("#cycle").val();
let table_no = $("#table_no").val();
//let date = $("#date").val();

console.log(first);
console.log(last);
console.log(cycle);
console.log(table_no);

$("#date").on("change",function(){
	let date=$("#date").val();
	console.log(date);

})
time(first,last,cycle,table_no);
function time(first,last,cycle,table_no){

}