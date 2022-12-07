/**
 *  사업자 회원가입
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


	
	

// 대표자명
	
	// 대표자명 정규식 확인
	let na = false;
	$("#n_input").on("keyup",function(){
		const N_chk = /^[가-힣a-zA-Z0-9- _]{1,20}$/;
		if($("#n_input").val()==""){
			$("#n_msg").text("필수입력정보입니다.").css("color","red");
			na = false;
		}else if(N_chk.test($("#n_input").val())){
			$("#n_msg").text("");
			na = true;
		}else{
			$("#n_msg").text("한글 또는 영문 대소문자를 사용하세요.").css("color","red");
			na = false;
		}
	})


	
	

// 전화번호
	
	// 전화번호 정규식 확인
	let ph = false;
	$("#p_input").on("keyup",function(){
		const P_chk = /^[0-9]{8,13}$/;
		if($("#p_input").val()==""){
			$("#p_msg").text("필수입력정보입니다.").css("color","red");
			ph = false;
		}else if(P_chk.test($("#p_input").val())){
			$("#p_msg").text("");
			ph = true;
		}else{
			$("#p_msg").text("숫자만 입력해주세요.").css("color","red");
			ph = false;
		}
	})	
	
	
	
	
	
// 이메일
	
	// 이메일 인증버튼을 누르면 먼저 사업자 등록번호 조회 하기
	let bn = false;
	$("#e_num_btn").on("click",function(e){
		e.preventDefault();
		$("#e_msg").text("잠시만 기다려주세요...").css("color","green");
		let c_no=$("#c_input").val();
		let c_name=$("#cn_input").val();
		$.ajax({
			type : 'get',
			url: "/buisnesscheck",
			data:{"num":c_no},
			contentType: "application/json; charset=utf8",
			success : function (data) {
				console.log("해당 사업자 번호에 등록된 상호명 : "+data);
				
				if(data!=""){
					if(data==c_name){
						bn = true;
						// 이메일 인증으로 보내기
						email_check();
					}else{
						$("#e_msg").text("입력한 상호명과 등록된 상호명이 다릅니다.").css("color","red");
					}
				}else{
					$("#e_msg").text("사업자 등록번호를 다시 확인해 주세요.").css("color","red");
				}
			}
		})
		.fail(function(){
			$("#e_msg").text("사업자 등록번호를 다시 확인해 주세요.").css("color","red");
			
		})
	})

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

	// 이메일 전송 전 이메일 주소 확인
	function email_check(){	
		const Email_chk= /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		
		if (em2) {
			$("input[name='email']").val($("#e_input1").val() + "@" + $("#e_input2").val());
			let email = $("input[name='email']").val();
			if (Email_chk.test($("#e_input2").val())) {
				$("#e_msg").text("이메일 전송중...").css("color","green");
				email_num_check(email);
			} else {
				$("#e_msg").text("이메일 주소를 다시 확인해 주세요.").css("color", "red");
			}
		} else {
			$("#e_msg").text("이메일 주소를 다시 확인해 주세요.").css("color", "red");
		}
	}

	// 인증 번호 전송
	function email_num_check(email) {
		console.log(email);
		$.ajax({
			type : 'get',
			url : "/member/bemailchk/",
			data : {email},
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
		
		if (!(bn && pw && pw2 && na && em)) {
			alert('입력을 완료하세요.');
				
		} else {
			$("form[action='/member/bsignup']").submit();
		}
	})



