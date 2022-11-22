/**
 * 
 */
$("#update_btn").on("click",function(){
//	alert("update")
	$.ajax({
		type:"put",
		url:"/updatesetting",
		contentType:"application/json; charset=utf-8",
		success:function(){
			alert("수정성공");
			location.reload();
//			r_list(bno);
		}
	})
})