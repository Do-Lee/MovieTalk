package user;

import java.sql.*;

import javax.naming.*;
import javax.sql.*;

import common.PageResult;

public class UserDAO {
	public static DataSource getDataSource() throws NamingException {
		Context initContext = null;
		Context environmentContext = null;
		
		initContext = new InitialContext();
		environmentContext = (Context) initContext.lookup("java:comp/env");
		
		return (DataSource) environmentContext.lookup("jdbc/WebDB");
	}
	
	public static Connection getConnection() {
		String dbUrl = "jdbc:mysql://localhost:3306/movietalk";
		String dbId = "webproject";
		String dbPass = "test";
		
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, dbId, dbPass);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static PageResult<User> getPage(int page, int numItemsInPage) 
			throws SQLException, NamingException {
		Connection conn = getConnection();
		Statement stmt = null;
		ResultSet rs = null;		

		if (page <= 0) {
			page = 1;
		}
		
		//DataSource ds = getDataSource();
		PageResult<User> result = new PageResult<User>(numItemsInPage, page);
		
		
		int startPos = (page - 1) * numItemsInPage;
		
		try {
			//conn = ds.getConnection();
			conn = getConnection();
			stmt = conn.createStatement();
			
			// users 테이블: user 수 페이지수 개산
	 		rs = stmt.executeQuery("SELECT COUNT(*) FROM users");
			rs.next();
			
			result.setNumItems(rs.getInt(1));
			
			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
			
	 		// users 테이블 SELECT
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM users ORDER BY name LIMIT " + startPos + ", " + numItemsInPage);
			
			while(rs.next()) {
				result.getList().add(new User(rs.getInt("id"),
							rs.getString("userid"),
							rs.getString("name"),
							rs.getString("pwd"),
							rs.getString("email")
						));
			}
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		
		return result;		
	}
	
	public static User checkIdPwd(String userid, String pwd) throws NamingException, SQLException {
		User user = null;
		
		Connection conn = getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		//DataSource ds = getDataSource();
		
		try {
			//conn = ds.getConnection();
			// 질의 준비
			stmt = conn.prepareStatement("SELECT * FROM users WHERE userid = ? and pwd = ?");
			stmt.setString(1, userid);
			stmt.setString(2, pwd);
			
			// 수행
			rs = stmt.executeQuery();

			if (rs.next()) {
				user = new User(rs.getInt("id"),
						rs.getString("userid"),
						rs.getString("name"),
						rs.getString("pwd"),
						rs.getString("email"));
			}	
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		
		return user;
	}
	
	public static User findById(String userid) throws NamingException, SQLException {
		User user = null;
		
		Connection conn = getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		//DataSource ds = getDataSource();
		
		try {
			//conn = ds.getConnection();
			// 질의 준비
			stmt = conn.prepareStatement("SELECT * FROM users WHERE userid = ?");
			stmt.setString(1, userid);
			
			// 수행
			rs = stmt.executeQuery();

			if (rs.next()) {
				user = new User(rs.getInt("id"),
						rs.getString("userid"),
						rs.getString("name"),
						rs.getString("pwd"),
						rs.getString("email"));

			}	
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		
		return user;
	}
	
	public static User findById(int id) throws NamingException, SQLException{
		User user = null;
		
		Connection conn = getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		//DataSource ds = getDataSource();
		
		try {
			//conn = ds.getConnection();
			// 질의 준비
			stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
			stmt.setInt(1, id);
			
			// 수행
			rs = stmt.executeQuery();

			if (rs.next()) {
				user = new User(rs.getInt("id"),
						rs.getString("userid"),
						rs.getString("name"),
						rs.getString("pwd"),
						rs.getString("email"));

			}	
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		
		return user;
	}
	
	public static boolean create(User user) throws SQLException, NamingException {
		int result;
		Connection conn = getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		//DataSource ds = getDataSource();
		
		try {
			//conn = ds.getConnection();
			// 질의 준비
			stmt = conn.prepareStatement(
					"INSERT INTO users(userid, name, pwd, email) " +
					"VALUES(?, ?, ?, ?)"
					);
			stmt.setString(1,  user.getUserid());
			stmt.setString(2,  user.getName());
			stmt.setString(3,  user.getPwd());
			stmt.setString(4,  user.getEmail());
			
			// 수행
			result = stmt.executeUpdate();
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		
		return (result == 1);
	}
	
	public static boolean update(User user) throws SQLException, NamingException {
		int result;
		Connection conn = getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		//DataSource ds = getDataSource();
		
		try {
			//conn = ds.getConnection();
			// 질의 준비
			stmt = conn.prepareStatement(
					"UPDATE users " +
					"SET  name=?, email=?" +
					"WHERE id=?"
					);
			stmt.setString(1,  user.getName());
			stmt.setString(2,  user.getEmail());
			stmt.setInt(3,  user.getId());
			
			// 수행
			result = stmt.executeUpdate();
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		
		return (result == 1);		
	}
	
	public static boolean remove(int id) throws NamingException, SQLException {
		int result;
		Connection conn = getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		//DataSource ds = getDataSource();
		
		try {
			//conn = ds.getConnection();
			// 질의 준비
			stmt = conn.prepareStatement("DELETE FROM users WHERE id=?");
			stmt.setInt(1,  id);
			
			// 수행
			result = stmt.executeUpdate();
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		
		return (result == 1);		
	}
}
