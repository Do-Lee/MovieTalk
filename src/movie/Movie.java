package movie;

import java.io.Serializable;

public class Movie implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private String subtitle;
	private String link;
	private String image;
	private String director;
	private String actor;
	private String pubDate;
	private String userRating;
	
	public Movie(String title, String subtitle, String link, String image, String director, String actor, String pubDate, String userRating) {
		this.title = title;
		this.subtitle = subtitle;
		this.link = link;
		this.image = image;
		this.director = director;
		this.actor = actor;
		this.pubDate = pubDate;
		this.userRating = userRating;
	}
	
	public Movie(int id, String title, String subtitle, String link, String image, String director, String actor, String pubDate, String userRating) {
		this.id = id;
		this.title = title;
		this.subtitle = subtitle;
		this.link = link;
		this.image = image;
		this.director = director;
		this.actor = actor;
		this.pubDate = pubDate;
		this.userRating = userRating;
	}
	
	public int getId() {return id;}
	public String getTitle() {return title;}
	public String getSubtitle() {return subtitle;}
	public String getLink() {return link;}
	public String getImage() {return image;}
	public String getDirector() {return director;}
	public String getActor() {return actor;}
	public String getPubDate() {return pubDate;}
	public String getUserRating() {return userRating;}
	public float getUserRatingInFloat() {
		return (this.userRating.equals("")) ? 0.0f : Float.parseFloat(this.userRating);
	}
	
	public String toString() {
		return title + " / " + subtitle + " / " + link + " / " + image + " / " + 
				director + " / " + actor + " / " + pubDate + " / " + userRating;
	}
}
