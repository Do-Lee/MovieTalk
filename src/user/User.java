package user;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String userid;
	private String name;
	private String pwd;
	private String email;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(int id,String userid, String name,String pwd, String email) {
		super();
		setId(id);
		setUserid(userid);
		setName(name);
		setPwd(pwd);
		setEmail(email);
	}
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getUserid() {return userid;}
	public void setUserid(String userid) {this.userid = userid;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getPwd() {return pwd;}
	public void setPwd(String pwd) {this.pwd = pwd;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}


}
