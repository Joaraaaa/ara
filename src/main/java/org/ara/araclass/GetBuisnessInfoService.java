package org.ara.araclass;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONObject;
import org.json.XML;

public class GetBuisnessInfoService {
	   private final String HTTP_REQUEST = "https://bizno.net/api/fapi";

	    public String getUserInfo(String num){
	        try {
	            String info = "";

	            // URI를 URL객체로 저장
	            URL url = new URL(HTTP_REQUEST + "?key=YWhkcm5haGRybjNAbmF2ZXIuY29t&gb=1&q=" + num);

	            // 버퍼 데이터(응답 메세지)를 한 줄씩 읽어서 jsonData에 저장
	            BufferedReader bf;
	            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
	            String line;
	            while((line = bf.readLine()) != null){
	            	info+=line;
	            }
	            JSONObject json = XML.toJSONObject(info);
	            JSONObject responseJsonObject = (JSONObject)json.get("response");
	            JSONObject bodyJsonObject = (JSONObject) responseJsonObject.get("body");
	    		JSONObject itemsJsonObject = (JSONObject) bodyJsonObject.get("items");
	    		JSONObject itemJsonObject = (JSONObject) itemsJsonObject.get("item");
	    		String company = itemJsonObject.get("company").toString();
	    		System.out.println(company);
	    		return company;

	        } catch(Exception e) {
	            return null;
	        }
	    }
}
