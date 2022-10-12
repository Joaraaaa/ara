/**
 * 
 */




$(".member").on("click",function(){
	let admin=$('input[name="admin"]:checked').val();
	console.log(admin);
	if(admin=="true"){
		console.log("사업자 로그인");
		$("#buisness_num").show();
	}else{
		console.log("일반 로그인");
		$("#buisness_num").hide();
	}
})