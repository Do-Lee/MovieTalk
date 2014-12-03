package facebook;



public class FacebookUser implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String fbid;
	private String name;
		
	public FacebookUser() {
	}

	public FacebookUser(int id, String fbid, String name) {
		super();
		this.id = id;
		this.fbid = fbid;
		this.name = name;
	}

	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getfbId() {return fbid;}
	public void setfbId(String fb_id) {this.fbid = fb_id;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

}
