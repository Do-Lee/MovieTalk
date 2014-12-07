package chat;

import java.sql.Timestamp;

import org.json.simple.JSONObject;

public class Message implements java.io.Serializable {
	private static final long serialVersionUID = 7116948049833402318L;
	
	// Attributes
	private int id;
	private String movietitle;
	private String title;
	private String image;
	private String opener;
	private String writer;
	private String contents;
	private String message;
	private String time;

	// SendMessage용 Constructor
	public Message(String writer, String message) {
		this.writer = writer;
		this.message = message;
	}

	// 개설용 Constructor
	public Message(String movietitle, String title, String image, String opener, String contents) {
		this.movietitle = movietitle;
		this.title = title;
		this.image = image;
		this.opener = opener;
		this.contents = contents;
	}
	
	public Message(int id, String movietitle, String title, String image, String opener, String writer, String contents, String message) {
		this.id = id;
		this.movietitle = movietitle;
		this.title = title;
		this.image = image;
		this.opener = opener;
		this.writer = simpleName(writer);
		this.contents = contents;
		this.message = message;
	}
	
	public Message(int id, String movietitle, String title, String image, String opener, String writer, String contents, String message, Timestamp time) {
		this(id, movietitle, title, image, opener, writer, contents, message);
		this.time = simpleDate(time);
	}

	// Getters and Setters
	public int getId() {return id;}
	public String getMovietitle() {return movietitle;}
	public void setMovietitle(String movietitle) {this.movietitle = movietitle;}
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	public String getOpener() {return opener;}
	public String getWriter() {return writer;}
	public String getContents() {return contents;}
	public String getMessage() {return message;}
	public void setTime(Timestamp time) {this.time = simpleDate(time);}
	public String getImage() {return image;}

	
	// json 형태로 출력
	@SuppressWarnings("unchecked")
	public JSONObject toJSON(String current_name) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id", getId());
		jsonObj.put("movietitle", getMovietitle());
		jsonObj.put("title", getTitle());
		jsonObj.put("image", getImage());
		jsonObj.put("opener", getOpener());
		jsonObj.put("writer", getWriter());
		jsonObj.put("contents", getContents());
		jsonObj.put("message", getMessage());
		jsonObj.put("time", time.toString());
		jsonObj.put("mine", ( current_name != null && 
				current_name.equals(getOpener()) ) ? "mine" : "");

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
