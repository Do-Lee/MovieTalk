package chat;

import java.sql.Timestamp;
import java.util.*;

import org.json.simple.JSONObject;

public class Message implements java.io.Serializable {
	private static final long serialVersionUID = 7116948049833402318L;
	private String movietitle;
	private String title;
	private String userid;
	private String content;
	private int id;
	private Date time;

	public Message(String userid, String content) {
		this.userid = userid;
		this.content = content;
	}

	public Message(int id, String movietitle, String title, String userid, String content, Timestamp time) {
		this.id = id;
		this.movietitle = movietitle;
		this.title = title;
		this.userid = userid;
		this.time = time;
	}

	public String getUserid() {return userid;}
	public int getId() {return id;}
	public String getContent() {return content;}
	public void setTime(Date time) {this.time = time;}
	public String getMovietitle() {return movietitle;}
	public void setMovietitle(String movietitle) {this.movietitle = movietitle;}
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	
	// json 형태로 출력
	public JSONObject toJSON(String current_name) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("movietitle", getMovietitle());
		jsonObj.put("title", getTitle());
		jsonObj.put("userid", getUserid());
		jsonObj.put("content", getContent());
		jsonObj.put("time", time.toString());
		jsonObj.put("id", getId());
		jsonObj.put("mine", (current_name != null && 
				current_name.equals(getUserid())) ? "mine" : "");

		return jsonObj;
	}





}
