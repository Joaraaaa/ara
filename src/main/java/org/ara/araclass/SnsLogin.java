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

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class SnsLogin {
	
// 카카오 아이디로 로그인
	
  // 카카오 로그인
	@RequestMapping(value = "/kakaologin", method = RequestMethod.GET)
	public MemberVO kakaoLogin(String code) {

		// 2. 받은 인증코드를 카카오로 보내서 엑세스 토큰을 받아내는 클래스(서비스에 만들어져있음.)
		

		//access_token이 포함된 JSON String을 받아온다.
		String accessTokenJsonData = getAccessTokenJsonData(code);
		System.out.println(accessTokenJsonData);
		if(accessTokenJsonData=="error") {
			System.out.println("error");
			return null;
		}

		//JSON String -> JSON Object

		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(accessTokenJsonData);
			JSONObject jsonObj = (JSONObject) obj;
			System.out.println(jsonObj.get("access_token"));
			String accessToken = (String) jsonObj.get("access_token");
//			session.setAttribute("accessToken",accessToken);

			// 3. 받아낸 엑세스 토큰을 보내서 사용자 정보를 받아내는 클래스(서비스에 만들어져있음.)
			
			//유저 정보가 포함된 JSON String을 받아온다.
			String userInfo = getUserInfo(accessToken);
			System.out.println(userInfo);
			//JSON String -> JSON Object
			JSONParser userInfoParser = new JSONParser();
			Object uobj = userInfoParser.parse(userInfo);
			JSONObject userInfojsonObj = (JSONObject) uobj;
			System.out.println(userInfojsonObj.get("id"));

			// 4. 사용자 정보 추출
			JSONObject kakaoAccountJsonObject = (JSONObject)userInfojsonObj.get("kakao_account");
			JSONObject propertiesJsonObject = (JSONObject)userInfojsonObj.get("properties");
			String email = kakaoAccountJsonObject.get("email").toString();
			String id = userInfojsonObj.get("id").toString();
			String nickname = propertiesJsonObject.get("nickname").toString();
			

			// 5. MemberVO에 담기. ---- 끝!
			MemberVO mvo = new MemberVO();
			mvo.setSns(true);
			mvo.setEmail("K+"+email);
			mvo.setPassword(id);
			mvo.setN_name("K+"+nickname);
			System.out.println(mvo);
			
			
			return mvo;
			
//			try {
//				ms.signUp(member);
//			}catch(Exception e) {
//				System.out.println("이미 회원이다.");
//			}
//			session.setAttribute("userInfo",ms.login(member));

		} catch (ParseException e) {
			e.printStackTrace();
		}


		return null;
	}
	
  // 인증 코드를 사용해 엑세스 토큰 받기
	// 해당 주소로 인증 코드를 포함한 정보를 보내고
	// 엑세스 토큰이 담긴 데이터를 받아 kakaoLogin에 리턴
	private final String GRANT_TYPE= "authorization_code";
    private final String CLIENT_ID = "5e6002f33ccf1b9303c9b3929c19c2a7";
    private final String REDIRECT_URI= "http://localhost:8080/snscheck";
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
    // 전달받은 사용자 정보가 담긴 데이터를 받아 kakaoLogin으로 리턴한다.
	private final String HTTP_REQUEST = "https://kapi.kakao.com/v2/user/me";

	public String getUserInfo(String accessToken){
		try {
			String jsonData = "";

			// URI를 URL객체로 저장
			URL url = new URL(HTTP_REQUEST + "?access_token=" + accessToken);

			// 버퍼 데이터(응답 메세지)를 한 줄씩 읽어서 jsonData에 저장
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
