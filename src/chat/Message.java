package chat;

import java.sql.Timestamp;

import org.json.simple.JSONObject;

public class Message implements java.io.Serializable {
	private static final long serialVersionUID = 7116948049833402318L;
	
	// Attributes
	private int id;
	private String movietitle;
	private String title;
	private String userid;
	private String message;
	private String time;

	// Constructor
	public Message(String userid, String message) {
		this.userid = userid;
		this.message = message;
	}

	public Message(int id, String movietitle, String title, String userid, String message, Timestamp time) {
		this.id = id;
		this.movietitle = movietitle;
		this.title = title;
		this.userid = simpleName(userid);
		this.message = message;
		this.time = simpleDate(time);
	}

	public String getUserid() {return userid;}
	public int getId() {return id;}
	public String getMessage() {return message;}
	public void setTime(Timestamp time) {this.time = simpleDate(time);}
	public String getMovietitle() {return movietitle;}
	public void setMovietitle(String movietitle) {this.movietitle = movietitle;}
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	
	// json 형태로 출력
	@SuppressWarnings("unchecked")
	public JSONObject toJSON(String current_name) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", getId());
		jsonObj.put("movietitle", getMovietitle());
		jsonObj.put("title", getTitle());
		jsonObj.put("userid", getUserid());
		jsonObj.put("message", getMessage());
		jsonObj.put("time", time.toString());
		jsonObj.put("mine", ( current_name != null && 
				current_name.equals(getUserid()) ) ? "mine" : "");

		return jsonObj;
	}

	private String simpleDate(Timestamp time) {
		String simpleTime = new String();
		
		
		for(int i=time.toString().indexOf('-') + 1; i<time.toString().lastIndexOf(' '); i++) {
			simpleTime += time.toString().charAt(i);
		}
		
		return simpleTime;
	}
	
	private String simpleName(String name) {
		String simpleName = new String();
		
		if(name.length() > 13) {
			for(int i=0; i<10; i++) {
				simpleName += name.charAt(i);
			}
			simpleName += "...";
		}
		else {
			simpleName = name;
		}
		
		return simpleName;
	}
}
