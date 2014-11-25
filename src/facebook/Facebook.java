package facebook;

import java.io.*;
import java.net.*;
import java.util.*;
import com.restfb.*;
import com.restfb.types.User;

public class Facebook {
	public static final String APPID = "1500964636829366";
	public static final String APPSECRET ="34702249ffc852f6dc7144b2e4b3cb38";
	public static final String SITEURL="http://54.64.43.225/MovieTalk";
	
	private static final String PERMISSIONS="public_profile,user_friends";
	private FacebookClient facebookClient;
	
	private Facebook(String accessToken) {
		this.facebookClient = new DefaultFacebookClient(accessToken);
	}
	
	public static Facebook getInstance(String code) throws IOException {
		return new Facebook(getAccessToken(code));
	}
	
	public static String getOAuthURL() throws IOException {
		String redirectURL = Facebook.SITEURL + "/FBAuthServlet.do";
		String oauthURL = "https://www.facebook.com/v2.0/dialog/oauth?" +
			"client_id="+ Facebook.APPID +
			"&redirect_uri=" + URLEncoder.encode(redirectURL, "UTF-8") +
			"&scope=" + Facebook.PERMISSIONS;
		return oauthURL;
	}
	
	private static String getAccessToken(String code) throws IOException {
		String redirectURL = Facebook.SITEURL + "/FBAuthServlet.do";
		String accessToken = "";
		String accessTokenURL = "https://graph.facebook.com/oauth/access_token?" +
				"client_id=" + Facebook.APPID +
				"&redirect_uri=" + URLEncoder.encode(redirectURL, "UTF-8") +
				"&client_secret=" + Facebook.APPSECRET +
				"&code=" + code;
		
		URL siteURL = new URL(accessTokenURL);
		URLConnection urlConn = siteURL.openConnection();
		BufferedReader in = new BufferedReader(
				new InputStreamReader(urlConn.getInputStream()));
		String inputLine = null;
		while((inputLine = in.readLine())!= null) {
			accessToken = inputLine.split("&")[0].split("=")[1];
		}
		in.close();
		
		return accessToken;
	}
	
	public List<User> getFriends() {
		List<User> friends = new ArrayList<User>();
		String fields = "id, name, link, first_name, last_name, gender";
		Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class, Parameter.with("fields", fields));
		
		for(List<User> myFriendConnection: myFriends) {
			for(User friend : myFriendConnection) {
				friends.add(friend);
			}
		}
		return friends;
	}
	
	public String getAPPID() {return Facebook.APPID;}
	public String getSITEURL() {return Facebook.SITEURL;}

	public User getCurrentUser() {
		return facebookClient.fetchObject("me", User.class);
	}
}
