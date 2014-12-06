package facebook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;

public class Facebook{	
	// 자신의 facebook appID
	public static final String APPID = "1500964636829366";
	// 자신의 facebook app Secret;
	public static final String APPSECRET = "34702249ffc852f6dc7144b2e4b3cb38";
	// facebook에 등록시킬 사이트주소
	public static final String SITEURL="http://localhost:8080/MovieTalk";
	//public static final String SITEURL="http://54.64.172.207/MovieTalk";
	// facebook에 요청할  권한 리스트
	private static final String PERMISSIONS ="user_about_me";	

	private FacebookClient facebookClient;
	private Facebook(String accessToken) {this.facebookClient = new DefaultFacebookClient(accessToken);}

	// setters & getters
	public static Facebook getInstance(String code) throws IOException{return new Facebook(getAccessToken(code));}
	public User getCurrentUser() {return facebookClient.fetchObject("me", User.class);}
	public String getAPPID() {return Facebook.APPID;}
	public String getSITEURL() {return Facebook.SITEURL;}
	
	// facebook에서 얻은 code로 access token 얻음
	public static String getOAuthURL() throws IOException{		
		// facebook 인증 후 돌아올 redirect_url
		String redirectURL = Facebook.SITEURL +"/FBAuthServlet.do";		
		String oauthURL = "https://www.facebook.com/dialog/oauth?" +
			"client_id="+ Facebook.APPID +
			"&redirect_uri=" + URLEncoder.encode(redirectURL, "UTF-8")+
			"&scope="+ Facebook.PERMISSIONS;
		return oauthURL;
	}
	
	// facebook 사용자 정보를 가져오기 위해 access token을 넘겨준다.
	private static String getAccessToken(String code) throws IOException{
		String redirectURL = Facebook.SITEURL + "/FBAuthServlet.do";
		String accessToken = "";
		String accessTokenURL = "https://graph.facebook.com/oauth/access_token?" +
			"client_id="+ Facebook.APPID + 
			"&redirect_uri=" + URLEncoder.encode(redirectURL, "UTF-8") + 
			"&client_secret=" + Facebook.APPSECRET + 
			"&code=" + code;

		URL siteURL = new URL(accessTokenURL);
		URLConnection urlConn = siteURL.openConnection();        
		BufferedReader in = new BufferedReader(
			new InputStreamReader(urlConn.getInputStream()));
		String inputLine = null;        
		while ((inputLine = in.readLine()) != null){ 
			accessToken = inputLine.split("&")[0].split("=")[1];	        		
		}
		in.close();
		return accessToken;
	}	

	
	
}
