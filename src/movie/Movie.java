package movie;

import java.io.Serializable;

public class Movie implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private String link;
	private String image;
	private String subtitle;
	private String pubdate;
	private String director;
	private String actor;
	private int userrating;
	
	public Movie() {
	}
	
	public Movie(int id, String title, String link, String image, String subtitle, String pubdate, String director, String actor, int userrating) {
		setId(id);
		setTitle(title);
		setLink(link);
		setImage(image);
		setSubtitle(subtitle);
		setPubdate(pubdate);
		setDirector(director);
		setActor(actor);
		setUserrating(userrating);
	}
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	public String getLink() {return link;}
	public void setLink(String link) {this.link = link;}
	public String getImage() {return image;}
	public void setImage(String image) {this.image = image;}
	public String getSubtitle() {return subtitle;}
	public void setSubtitle(String subtitle) {this.subtitle = subtitle;}
	public String getPubdate() {return pubdate;}
	public void setPubdate(String pubdate) {this.pubdate = pubdate;}
	public String getDirector() {return director;}
	public void setDirector(String director) {this.director = director;}
	public String getActor() {return actor;}
	public void setActor(String actor) {this.actor = actor;}
	public int getUserrating() {return userrating;}
	public void setUserrating(int userrating) {this.userrating = userrating;}

}
