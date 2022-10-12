/**
 * 
 */

$("#buisness_num").on("click",function(e){
	e.preventDefault();
//	alert($("#b_num").val());
	let num=$("#b_num").val();
	$.getJSON("/buisnesscheck",{num},function(res){
		console.log(res);
	})
})