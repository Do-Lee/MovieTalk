package facebook;



public class FacebookUser implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String fbid;
	private String userid;
	private String pwd;
	private String name;
		
	public FacebookUser() {
	}

	public FacebookUser(int id, String fbid, String userid, String pwd, String name) {
		super();
		this.id = id;
		this.fbid = fbid;
		this.userid = userid;
		this.pwd = pwd;
		this.name = name;
	}

	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getfbId() {return fbid;}
	public void setfbId(String fb_id) {this.fbid = fb_id;}
	public String getUserid() {return userid;}
	public void setUserid(String userid) {this.userid = userid;}
	public String getPwd() {return pwd;}
	public void setPwd(String pwd) {this.pwd = pwd;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

}
