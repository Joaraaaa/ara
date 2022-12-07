/**
 * 일반 회원가입
 */



//비밀번호

	// 비밀번호 정규식 확인
	let pw = false;
	$("#pw_input").on("keyup",function() {
		
		let pw_val = $(this).val();
	
		const Pw_chk = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[`$@$!%*#?&])[A-Za-z\d`$@$!%*#?&]{8,16}$/g;
	
		if (pw_val == "") {
			$("#pw_msg").text("필수 입력 정보입니다.").css("color", "red");
			pw = false;
		} else if (Pw_chk.test(pw_val)) {
			$("#pw_msg").text("");
			pw = true;
		} else {
			$("#pw_msg").text("8~16자의 영문, 숫자, 특수문자를 하나 이상 사용하세요.").css("color", "red");
			pw = false;
		}
	})
	
	// 비밀번호 일치 확인
	let pw2 = false;
	$("#pw_input2").on("keyup", function() {
		if (pw) {
			if ($(this).val() === $("#pw_input").val()) {
				$("#pw_msg2").text("정상입니다.").css("color", "green");
				pw2 = true;
			} else {
				$("#pw_msg2").text("비밀번호가 일치하지 않습니다.").css("color", "red");
				pw2 = false
			}
		} else {
			$("#pw_msg2").text("비밀번호를 다시 확인해주세요.").css("color", "red");
			pw2 = false
		}
	})


	
	
	
// 닉네임
	
	// 닉네임 정규식 확인
	const N_chk = /^[가-힣a-zA-Z0-9- _]{1,20}$/;
	
	$("#n_input").on("keyup", function() {
	
		let n_val = $(this).val();
		
		if ($("#n_input").val() == "") {
			$("#n_msg").text("필수입력정보입니다.").css("color", "red");
		} else if (N_chk.test(n_val)) {
			$("#n_msg").text("");
			nacheck(n_val);
		} else {
			$("#n_msg").text("한글 또는 영문 대소문자 사용하세요.").css("color", "red");
		}
	})
	
	// 닉네임 추천
	$("#n_make").on("click", function(e) {
		e.preventDefault();
		$.getJSON("/makename", function(res) {
			$("#n_input").val(res.words[0]);
			if (N_chk.test($("#n_input").val())) {
				$("#n_msg").text("");
				name_check($("#n_input").val());
			} else {
				$("#n_make").click();
			}
	
		})
	})
	
	// 닉네임 중복 확인
	let na = false;
	function name_check(n_name) {
		console.log(n_name);
		$.ajax({
			type : "get",
			url : "/check",
			data : {"n_name":n_name},
			contentType : "application/json; charset=utf-8"
		}).done(function(r) {
			$("#n_msg").text("이미 사용중인 닉네임 입니다.").css("color", "red");
			na = false;
		}).fail(function() {
			$("#n_msg").text("사용가능한 닉네임 입니다.").css("color", "green");
			na = true;
		})
	}


	
	
	
// 이메일

	// 이메일 주소 @ 앞 부분
	let em = false;
	let em2 = false;
	$("#e_input1").on("keyup", function() {
	
		const E_chk = /^([a-z0-9_\.-]+)$/g;
		if ($(this).val() == "") {
			$("#e_msg").text("필수입력정보입니다.").css("color", "red");
			em2 = false;
		} else if (E_chk.test($(this).val())) {
			$("#e_msg").text("이메일 인증이 필요합니다.").css("color", "red");
			em2 = true;
		} else {
			$("#e_msg").text("이메일 주소를 다시 확인해 주세요.").css("color", "red");
			em2 = false;
		}
	})
	
	// 이메일 주소 @ 뒷 부분
	$("#e_select").on("change", function() {
		if ($("#e_select").val() == "@user") {
			$("#e_input2").val("");
			$("#e_input2").attr("readonly", false);
			$("#e_input2").css("background-color", "white");
		} else {
			$("#e_input2").val(this.value.slice(1));
			$("#e_input2").attr("readonly", true);
			$("#e_input2").css("background-color", "#e7e7e7");
		}
	})
	
	// 이메일로 인증 번호 받기 버튼
	$("#e_num_btn").on("click",function(e) {
		const Email_chk = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		e.preventDefault();
						
		if (em2) {
			$("input[name='email']").val($("#e_input1").val() + "@" + $("#e_input2").val());
			let email = $("input[name='email']").val();
			if (Email_chk.test($("#e_input2").val())) {
				$("#e_msg").text("이메일 전송중...").css("color","green");
				email_check(email);
			} else {
				$("#e_msg").text("이메일 주소를 다시 확인해 주세요.").css("color", "red");
			}
		} else {
			$("#e_msg").text("이메일 주소를 다시 확인해 주세요.").css("color", "red");
		}
	})
	
	// 이메일 중복 확인				
	function email_check(email) {
		$.ajax({
			type : "get",
			url : "/check",
			data : {"email":email},
			contentType : "application/json; charset=utf-8"
		}).done(function(r) {
			$("#e_msg").text("이미 사용중인 이메일 입니다.").css("color", "red");
		}).fail(function() {
			email_num_check(email);
		})
	}
	
	// 인증 번호 전송
	function email_num_check(email) {
		console.log(email);
		$.ajax({
			type : 'get',
			url : "/member/emailchk/" + email + "/",
			data : email,
			contentType : "application/json; charset=utf-8",
			success : function(data) {
				$("#e_msg").text("인증번호가 전송되었습니다").css("color", "green");
				console.log("인증번호 : " + data);
				$('#e_num_input').attr('disabled', false);
				code = data;
			}
		})
		.fail(function() {
			$("#e_msg").text("인증번호 전송에 실패했습니다.");
			$("#e_msg").css("color", "red");
			em = false;
		})
	}

	// 인증번호 비교
	$('.e_num_input').blur(function() {
	
		if ($(this).val() === code) {
			$('#e_msg').html('인증번호가 일치합니다.').css('color', 'green');
			em = true;
		} else {
			$('#e_msg').html('인증번호가 불일치 합니다. 다시 확인해주세요!').css('color', 'red');
			em = false;
		}
	});



	
	
// 회원가입
	$("#signsub").on("click", function(e) {
		e.preventDefault();
	
		if (!(pw && pw2 && na && em)) {
			alert('입력을 완료하세요.');
			
		} else {
			$("form[action='/member/signup']").submit();
		}
	})
