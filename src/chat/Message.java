package chat;

import java.sql.Timestamp;

import org.json.simple.JSONObject;

public class Message implements java.io.Serializable {
	private static final long serialVersionUID = 7116948049833402318L;
	
	// Attributes
	private int id;
	private String title;
	private String writer;
	private String message;
	private String time;
	
	// SendMessage용 Constructor
	public Message(String title, String writer, String message) {
		this.title = title;
		this.writer = writer;
		this.message = message;
	}

	public Message(int id, String title, String writer, String message, Timestamp time) {
		this.id = id;
		this.title = title;
		this.writer = simpleName(writer);
		this.message = message;
		this.time = simpleDate(time);
	}

	// Getters and Setters
	public int getId() {return id;}
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	public String getWriter() {return writer;}
	public String getMessage() {return message;}
	public String getTime() {return time;}

	// json 형태로 출력
	@SuppressWarnings("unchecked")
	public JSONObject toJSON(String current_name) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", getId());
		jsonObj.put("title", getTitle());
		jsonObj.put("writer", getWriter());
		jsonObj.put("message", getMessage());
		jsonObj.put("time", getTime());
		jsonObj.put("mine", ( current_name != null && 
				current_name.equals(getWriter()) ) ? "mine" : "");

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
		} else {
			simpleName = name;
		}
		
		return simpleName;
	}
}
