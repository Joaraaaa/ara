package org.ara.araclass;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.ara.model.MemberVO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class SnsLogin {
	
// 카카오 아이디로 로그인
	
  // 카카오 로그인
	public MemberVO kakao(String code) {
		// kakaologin을 통해 인증코드를 전달받은 상태이다.
		// 인증코드를 accessTokenJsonData로 보내 엑세스 토큰을 받자.
		// access_token이 포함된 JSON String을 받아온다.
		String accessTokenJsonData = getAccessTokenJsonData(code);
		
		// 엑세스 토큰이 들어있는 덩어리를 받음!!
		System.out.println(accessTokenJsonData);
		if(accessTokenJsonData=="error") {
			System.out.println("error");
			return null;
		}
		
		
		// JSON String으로 받았다.
		// -> JSON Object 바꿔주자.

		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(accessTokenJsonData);
			JSONObject jsonObj = (JSONObject) obj;
			System.out.println(jsonObj.get("access_token"));
			
			// 엑세스 토큰만 끄집어냈다!!!!!!
			String accessToken = (String) jsonObj.get("access_token");
			

		
			// 엑세스 토큰을 getUserInfo로 보내 사용자 정보를 받아온다.
			// 유저 정보가 포함된 JSON String을 받아온다.
			String userInfo = getUserInfo(accessToken);
			
			// 사용자 정보 덩어리를 받았다!!
			System.out.println(userInfo);
			
			// JSON String -> JSON Object 또 바꿔준다.
			JSONParser userInfoParser = new JSONParser();
			Object uobj = userInfoParser.parse(userInfo);
			JSONObject userInfojsonObj = (JSONObject) uobj;
			JSONObject kakaoAccountJsonObject = (JSONObject)userInfojsonObj.get("kakao_account");
			JSONObject propertiesJsonObject = (JSONObject)userInfojsonObj.get("properties");
			
			// 필요한 사용자 정보를 받았다!!!!!!!!!!
			String email = kakaoAccountJsonObject.get("email").toString();
			String id = userInfojsonObj.get("id").toString();
			String nickname = propertiesJsonObject.get("nickname").toString();
			

			// 사용자 정보를 VO에 담아 한번에 이동하겠다.
			// 나의 사용자 정보 필수 정보는 이메일, 비밀번호, 닉네임 이렇게 단 3개다!
			// 나의 회원은 일반 회원, 네아로 회원, 구아로 회원, 카아로 회원 이렇게 4개다..
			// 나는 이 네가지의 로그인 방식을 모두 구분하고 싶다.
			// 일반회원가입은 '+'기호를 사용하지 못하게 했다.
			// 네이버는 N+, 구글은 G+, 카카오는 K+을 닉네임과 이메일에 붙여 중복이 절대 불가하게 만든다. 절대로.
			// 비밀번호는 도용되지 않을 무언가로 하기.
			MemberVO mvo = new MemberVO();
			mvo.setSns(true);
			mvo.setEmail("K+"+email);
			mvo.setPassword(id);
			mvo.setN_name("K+"+nickname);
			System.out.println(mvo);
			
			return mvo;

		} catch (ParseException e) {
			e.printStackTrace();
		}


		return null;
	}
	
  // 인증 코드를 사용해 엑세스 토큰 받기
	// 해당 주소로 인증 코드를 포함한 정보를 보내고
	// 엑세스 토큰이 담긴 데이터를 받아 kakao에 리턴
	private final String GRANT_TYPE= "authorization_code";
    private final String CLIENT_ID = "5e6002f33ccf1b9303c9b3929c19c2a7";
    private final String REDIRECT_URI= "http://localhost:8080/kakaologin";
    private final String CLIENT_SECRET= "JV5Gqiff8jdrPboqUMx9kVRf9ZlfZRB2";
    private final String TOKEN_URL = "https://kauth.kakao.com/oauth/token";

    public String getAccessTokenJsonData(String code){
        RestTemplate restTemplate = new RestTemplate();

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity request = new HttpEntity(headers);

        // URI 빌더 사용
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(TOKEN_URL)
                .queryParam("grant_type", GRANT_TYPE)
                .queryParam("client_id", CLIENT_ID)
                .queryParam("redirect_uri", REDIRECT_URI)
                .queryParam("code", code)
                .queryParam("client_secret", CLIENT_SECRET);

        // 요청 URI과 헤더를 같이 전송
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                uriComponentsBuilder.toUriString(),
                HttpMethod.POST,
                request,
                String.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        }
        return "error";
    }
    
    
  // 엑세스 토큰을 사용해 사용자 정보 받아오기
    // 해당 주소로 엑세스 토큰을 보내 사용자 정보를 받아내고
    // 전달받은 사용자 정보가 담긴 데이터를 받아 kakao로 리턴한다.
	private final String HTTP_REQUEST = "https://kapi.kakao.com/v2/user/me";

	public String getUserInfo(String accessToken){
		try {
			String jsonData = "";

			// URI를 URL객체로 저장
			URL url = new URL(HTTP_REQUEST + "?access_token=" + accessToken);

			// 데이터를 한 줄씩 읽어서 jsonData에 저장
			BufferedReader bf;
			bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
			String line;
			while((line = bf.readLine()) != null){
				jsonData+=line;
			}
			return jsonData;

		} catch(Exception e) {
			return "success";
		}
	}
}
