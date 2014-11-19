package user;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userid;
	private String pwd;
	private String email;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String userid, String pwd, String email) {
		super();
		setUserid(userid);
		setPwd(pwd);
		setEmail(email);
	}

	public String getUserid() {return userid;}
	public void setUserid(String userid) {this.userid = userid;}
	public String getPwd() {return pwd;}
	public void setPwd(String pwd) {this.pwd = pwd;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
}
