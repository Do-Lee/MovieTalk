package movie;

import java.io.Serializable;
import java.sql.Timestamp;

public class Movie implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String movietitle;
	private String subtitle;
	private String link;
	private String image;
	private String director;
	private String actor;
	private String pubDate;
	private String userRating;
	private String chattitle;
	private String opener;
	private String contents;
	private Timestamp time;
	
	private int lastId;
	
	public Movie(String movietitle, String subtitle, String link, String image, String director, String actor, String pubDate, String userRating) {
		this.movietitle = movietitle;
		this.subtitle = subtitle;
		this.link = link;
		this.image = image;
		this.director = director;
		this.actor = actor;
		this.pubDate = pubDate;
		this.userRating = userRating;
	}
	
	public Movie(int id, String movietitle, String subtitle, String link, String image, String director, String actor, String pubDate, String userRating, String chattitle, String opener, String contents, Timestamp time) {
		this.id = id;
		this.movietitle = movietitle;
		this.subtitle = subtitle;
		this.link = link;
		this.image = image;
		this.director = director;
		this.actor = actor;
		this.pubDate = pubDate;
		this.userRating = userRating;
		this.chattitle = chattitle;
		this.opener = opener;
		this.contents = contents;
		this.time = time;
	}
	
	public int getId() {return id;}
	public String getMovietitle() {return movietitle;}
	public String getSubtitle() {return subtitle;}
	public String getLink() {return link;}
	public String getImage() {return image;}
	public String getDirector() {return director;}
	public String getActor() {return actor;}
	public String getPubDate() {return pubDate;}
	public String getUserRating() {return userRating;}
	public float getUserRatingInFloat() {return (this.userRating.equals("")) ? 0.0f : Float.parseFloat(this.userRating);}
	public String getChattitle() {return chattitle;}
	public String getOpener() {return opener;}
	public String getContents() {return contents;}
	public Timestamp getTime() {return time;}
	public int getLastId() {return lastId;}
	
	public String toString() {
		return movietitle + " / " + subtitle + " / " + link + " / " + image + " / " + 
				director + " / " + actor + " / " + pubDate + " / " + userRating;
	}
}
