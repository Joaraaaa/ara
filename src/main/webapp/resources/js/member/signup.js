/**
 * 
 */

// let id = false;
// //let idd = false;
// $("#idchk").on("keyup",function(){
// const idid=/^[a-z0-9-_]{5,20}$/g;
// if($("#idchk").val()==""){
// $("#idmsg").text("필수입력정보입니다.");
// $("#idmsg").css("color","red");
// // idd = false;
// }else if(idid.test($("#idchk").val())){
// $("#idmsg").text("");
// // idd = true;
// let idc=$("#idchk").val();
// idcheck(idc);
// }else{
// $("#idmsg").text("5~20의 영문 소문자, 숫자와 특수기호 (-)(_)만 사용가능합니다.");
// $("#idmsg").css("color","red");
// // idd = false;
// }
// })
//
// function idcheck(idc){
// console.log(idc);
// $.ajax({
// type: "get",
// url: "/member/signup/"+idc,
// data: idc,
// contentType: "application/json; charset=utf-8"
// })
// .done(function(r){
// // alert("중복된 id 입니다.");
// $("#idmsg").text("이미 사용중인 id 입니다.");
// $("#idmsg").css("color","red");
// id = false;
// })
// .fail(function(){
// // alert("사용가능한 id 입니다.");
// $("#idmsg").text("사용가능한 id 입니다.");
// $("#idmsg").css("color","green");
// id = true;
// })
// }

var pw = false;
$("#pwchk")
		.on(
				"keyup",
				function() {
					const pwval = $("#pwchk").val();

					const pwpw = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[`$@$!%*#?&])[A-Za-z\d`$@$!%*#?&]{8,16}$/g;

					if (pwval == "") {
						$("#pwmsg").text("필수입력정보입니다.").css("color", "red");
						pw = false;
					} else if (pwpw.test(pwval)) {
						$("#pwmsg").text("");
						pw = true;
					} else {
						$("#pwmsg").text("8~16자 영문 숫자 특수문자를 하나 이상 사용하세요.").css(
								"color", "red");
						pw = false;
					}
				})

var pw2 = false;
$("#pwchk2").on("keyup", function() {
	if (pw) {
		if ($("#pwchk2").val() === $("#pwchk").val()) {
			$("#pwmsg2").text("정상입니다.").css("color", "green");
			pw2 = true;
		} else {
			$("#pwmsg2").text("비밀번호가 일치하지 않습니다.").css("color", "red");
			pw2 = false
		}
	} else {
		$("#pwmsg2").text("비밀번호를 다시 확인해주세요.").css("color", "red");
		pw_chk2 = false
	}
})

//const name = /^[가-힣a-zA-Z0-9-_]{1,20}$/g;
const name = /^[가-힣a-zA-Z0-9- _]{1,20}$/;
$("#make_name").on("click", function(e) {
	e.preventDefault();
	$.getJSON("/makename", function(res) {
		console.log(res.words[0]);
		$("#nachk").val(res.words[0]);
		if (name.test($("#nachk").val())) {
			$("#namsg").text("");
			let nac = $("#nachk").val();
			nacheck(nac);
		} else {
			$("#make_name").click();
		}

	})
})

var na = false;
$("#nachk").on("keyup", function() {

	if ($("#nachk").val() == "") {
		$("#namsg").text("필수입력정보입니다.").css("color", "red");
		// na = false;
	} else if (name.test($("#nachk").val())) {
		$("#namsg").text("");
		let nac = $("#nachk").val();
		nacheck(nac);
		// na = true;
	} else {
		$("#namsg").text("한글 또는 영문 대소문자 사용하세요.").css("color", "red");
		// na = false;
	}
})
function nacheck(nac) {
	console.log(nac);
	$.ajax({
		type : "get",
		url : "/member/signup/" + nac,
		data : nac,
		contentType : "application/json; charset=utf-8"
	}).done(function(r) {
		// alert("중복된 id 입니다.");
		$("#namsg").text("이미 사용중인 닉네임 입니다.").css("color", "red");
		na = false;
	}).fail(function() {
		// alert("사용가능한 id 입니다.");
		$("#namsg").text("사용가능한 닉네임 입니다.").css("color", "green");
		na = true;
	})
}

$("#email_address").on("change", function() {
	$("#direct").val(this.value.slice(1));
	if ($("#email_address").val() == "@user") {
		$("#direct").val("");
		$("#direct").attr("readonly", false);
		$("#direct").css("background-color", "white");
	} else {
		$("#direct").val(this.value.slice(1));
		$("#direct").attr("readonly", true);
		$("#direct").css("background-color", "#e7e7e7");
	}
})

var em = false;
let emm = false;
$("#email").on("keyup", function() {

	const Email = /^([a-z0-9_\.-]+)$/g;
	if ($("#email").val() == "") {
		$("#email_msg").text("필수입력정보입니다.").css("color", "red");
		emm = false;
	} else if (Email.test($("#email").val())) {
		$("#email_msg").text("");
		if ($("#direct").val() != "") {
			$("#email_msg").text("이메일 인증이 필요합니다.").css("color", "red");
		}
		emm = true;
	} else {
		$("#email_msg").text("이메일 주소를 다시 확인해 주세요.").css("color", "red");
		emm = false;
	}
})

$("#email_btn")
		.on(
				"click",
				function(e) {
					const Email_check = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
					e.preventDefault();

					if ($("#direct").val() == "") {
						$("#email_msg").text("이메일을 입력해주세요.")
								.css("color", "red");
						emm = false;
					}
					if (emm) {
						$("input[name='email']").val(
								$("#email").val() + "@" + $("#direct").val());
						var emc = $("input[name='email']").val();
						if (Email_check.test($("#direct").val())) {

							$("#email_msg").text("이메일 전송중...").css("color",
									"green");
							emcheck(emc);
						} else {
							$("#email_msg").text("이메일 주소를 다시 확인해 주세요.").css(
									"color", "red");
						}
					} else {
						$("#email_msg").text("이메일 주소를 다시 확인해 주세요.").css(
								"color", "red");
						// alert("부적절한 이메일 입니다.")
					}

				})

const checkInput = $('.mail_check_input') // 인증번호 입력하는곳
function emcheck(email) {
	console.log(email);
	$.ajax({
		type : 'get',
		url : "/member/emailchk/" + email + "/",
		data : email,
		contentType : "application/json; charset=utf-8",
		success : function(data) {
			$("#email_msg").text("");
			console.log("data : " + data);
			checkInput.attr('disabled', false);
			code = data;
			alert('인증번호가 전송되었습니다.')
		}
	})
	// .done(function(r){
	// alert("중복된 이메일 입니다.");
	// $("#email_msg").text("중복된 이메일 입니다.");
	// $("#email_msg").css("color","red");
	// em = false;
	// })
	.fail(function() {
		alert("실패.");
		$("#email_msg").text("실패");
		$("#email_msg").css("color", "green");
		em = false;
	})
}

$("#email_address")
// 인증번호 비교
// blur -> focus가 벗어나는 경우 발생
$('.mail_check_input').blur(function() {
	const inputCode = $(this).val();
	const $resultMsg = $('#email_msg');

	if (inputCode === code) {
		$resultMsg.html('인증번호가 일치합니다.');
		$resultMsg.css('color', 'green');
		em = true;
	} else {
		$resultMsg.html('인증번호가 불일치 합니다. 다시 확인해주세요!');
		$resultMsg.css('color', 'red');
		em = false;
	}
});

$("#signsub").on("click", function(e) {
	e.preventDefault();

	if (!(pw && pw2 && na && em)) {

		alert('입력해');
	} else {
		$("input[name='email']").val();
		alert('가입됨');
		$("form[action='/member/signup']").submit();
	}
})
