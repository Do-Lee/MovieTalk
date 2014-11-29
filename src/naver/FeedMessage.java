package naver;

public class FeedMessage {
	private String title;
	private String subtitle;
	private String link;
	private String image;
	private String director;
	private String actor;
	private String pubDate;
	private String userRating;
	
	public FeedMessage(String title, String subtitle, String link, String image, 
			String director, String actor, String pubDate, String userRating) {
		this.title = (title != null) ? title : "";
		this.subtitle = (subtitle != null) ? subtitle : "";
		this.link = (link != null) ? link : "";
		this.image = (image != null) ? image : "";
		this.director = (director != null) ? director : "";
		this.actor = (actor != null) ? actor : "";
		this.pubDate = (pubDate != null) ? pubDate : "";
		this.userRating = (userRating != null) ? userRating : "";
	}
	
	public String getTitle() {return title;}
	public String getSubtitle() {return subtitle;}
	public String getLink() {return link;}
	public String getImage() {return image;}
	public String getDirector() {return director;}
	public String getActor() {return actor;}
	public String getPubDate() {return pubDate;}
	public String getUserRating() {return userRating;}
} 