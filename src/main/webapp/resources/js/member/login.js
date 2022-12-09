/**
 * 
 */

$(".admin_radio").on("click",function(){
let admin=$('input[name="admin"]:checked').val();	
	console.log(admin);
	$("#login_msg").text("");
	if(admin=="true"){
		console.log("사업자 로그인");
		$("#buisness_num").show();
		$("#social_login_box").hide();
		$("#admin_t_box").css({"background-color":"white","border-bottom-style": "none"});
		$("#admin_f_box").css({"background-color":"#f9f9f9","border-bottom":"2px solid #f9f9f9"});
		$("#label_f").css({"color":"#808080","font-weight":"100"});
		$("#label_t").css({"color":"black","font-weight":"bold"});
	}else{
		console.log("일반 로그인");
		$("#buisness_num").hide();
		$("#social_login_box").show();
		$("#admin_f_box").css({"background-color":"white","border-bottom-style": "none"});
		$("#admin_t_box").css({"background-color":"#f9f9f9","border-bottom":"2px solid #f9f9f9"});
		$("#label_t").css({"color":"#808080","font-weight":"100"});
		$("#label_f").css({"color":"black","font-weight":"bold"});
	}
})


let lchk=false;
$("#loginsub").on("click",function(e){
	e.preventDefault();
	let admin=$('input[name="admin"]:checked').val();
		if($("#email").val()==""){
			$("#login_msg").text("이메일을 입력해 주세요").css("color","red");
		}else{
			if($("#password").val()==""){
				$("#login_msg").text("비밀번호를 입력해 주세요").css("color","red");
			}else{
				if(admin=="true"){
					if($("#buisness_num").val()==""){
						$("#login_msg").text("사업자등록번호를 입력해 주세요").css("color","red");
					}else{
						$("form[action='/member/login']").submit();
					}
				}else{
					$("form[action='/member/login']").submit();
				}
			}
		}

})




// 카카오 아이디로 로그인
	// 1. 카카오에 인증코드 요청
function loginWithKakao() {
    Kakao.Auth.authorize({
    	redirectUri:'http://localhost:8080/kakaologin'        
    });    
}
//카카오 아이디로 로그인 컨트롤러 확인하기




//구글 아이디로 로그인
$("#google_img").on("click",function(){
	$(".nsm7Bb-HzV7m-LgbsSe").click();
})

// 1. 구글 버튼 부분. 인증 요청 
window.onload = function () {
	google.accounts.id.initialize({
		client_id: "1021807136832-ee5020m3rjgegr7phn8ki82n3rttnqcd.apps.googleusercontent.com",
		callback: handleCredentialResponse
	});
	
	google.accounts.id.renderButton(
		document.getElementById("buttonDiv"),
		{ theme: "outline", size: "large" }
	);
}

// 2. 인증 된 후 발급된 아이디 토큰을 서버로 넘김
function handleCredentialResponse(response) {
    console.log("Encoded JWT ID token: " + response.credential);
    var id_token = response.credential;
    var xhr = new XMLHttpRequest();
	  
    xhr.open('POST', 'http://localhost:8080/googlelogin');
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send('idtoken=' + id_token);
	
	setTimeout(function(){
		location.href="/nhome";		  
	},1000);
}
//구글 아이디로 로그인 컨트롤러 확인하기




//네이버 아이디로 로그인
$("#naverIdLogin_loginButton").on("click",function(){
	$("#naverIdLogin").click();
})
  
//  1. 네이버 버튼 부분. 인증 요청(naverlogin.jsp의 js에서 받음.)
var naverLogin = new naver.LoginWithNaverId(
	{
		clientId: "GNxlB6b6TI5o3gXTOcXx",
  		// 본인의 Client ID로 수정, 띄어쓰기는 사용하지 마세요.
		callbackUrl: "http://localhost:8080/member/naverlogin",
  		// 본인의 callBack url로 수정하세요.
		isPopup: false,
		loginButton: {color: "white", type: 2,height:40}
	}
);

naverLogin.init();
//네이버 아이디로 로그인 naverlogin.jsp 확인하기