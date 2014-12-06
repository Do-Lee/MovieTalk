package chat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import common.PageResult;


public class ChatDAO {
	public static DataSource getDataSource() throws NamingException {
		Context initCtx = null;
		Context envCtx = null;

		// Obtain our environment naming context
		initCtx = new InitialContext();
		envCtx = (Context) initCtx.lookup("java:comp/env");

		// Look up our data source
		return (DataSource) envCtx.lookup("jdbc/WebDB");
	}
	
	public static PageResult<Message> getPage(int page, int numItemsInPage) throws SQLException, NamingException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;		

		if ( page <= 0 ) {
			page = 1;
		}
		
		DataSource ds = getDataSource();
		PageResult<Message> result = new PageResult<Message>(numItemsInPage, page);
		int startPos = (page - 1) * numItemsInPage;
		
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			
			// chats 테이블: chat 수 페이지수 개산
	 		rs = stmt.executeQuery("SELECT COUNT(*) FROM chats");
			rs.next();
			
			result.setNumItems(rs.getInt(1));
			
			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
			
	 		// chats 테이블 SELECT
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM chats ORDER BY id LIMIT " + startPos + ", " + numItemsInPage);
			while(rs.next()) {
				result.getList().add(new Message(rs.getInt("id"),
						rs.getString("movietitle"),
						rs.getString("title"),
						rs.getString("userid"),
						rs.getString("content"),
						rs.getString("message"),
						rs.getTimestamp("created_at")
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
	
	public static List<Message> getChatList(int last) throws SQLException, NamingException {
		
		List<Message> msgList = new ArrayList<Message>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		DataSource ds = getDataSource();
		
		try {
			conn = ds.getConnection();
			
			// 질의 준비
			if (last >= 0) {
				// last 이후의 모든 메시지
				stmt = conn.prepareStatement("SELECT * FROM chats WHERE id > ? ;");
				stmt.setInt(1,  last);
			} else {
				// 마지막 10개의 메시지만..
				stmt = conn.prepareStatement("SELECT * FROM "
						+"(SELECT * FROM chats ORDER BY id DESC LIMIT 100 ) t " 
						+"ORDER BY id ;");
			}

			// 수행
			rs = stmt.executeQuery();

			while (rs.next()) {
				Message msg = new Message(rs.getInt("id"), rs.getString("movietitle"), rs.getString("title"), rs.getString("userid"), 
								rs.getString("content"), rs.getString("message"), rs.getTimestamp("created_at"));
				msgList.add(msg);
			}	
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		
		return msgList;
	}
	public static boolean sendMessage(Message msg) throws SQLException, NamingException {
		int result;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		DataSource ds = getDataSource();

		try {
			conn = ds.getConnection();

			// 질의 준비
			stmt = conn.prepareStatement("INSERT INTO chats(userid, message) VALUES (?, ?);");
			stmt.setString(1, msg.getUserid());
			stmt.setString(2, msg.getMessage());

			// 수행
			result = stmt.executeUpdate();
		} 
		finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}

		return (result == 1);
	}
	
	public static Message findChat(String title) throws NamingException, SQLException {
		Message message = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DataSource ds = getDataSource();
		
		try {
			conn = ds.getConnection();
			// 질의 준비
			stmt = conn.prepareStatement("SELECT * FROM chats WHERE title = ?");
			stmt.setString(1, title);
			
			// 수행
			rs = stmt.executeQuery();

			if (rs.next()) {
				message = new Message(rs.getInt("id"),
						rs.getString("movietitle"),
						rs.getString("title"),
						rs.getString("userid"),
						rs.getString("content"),
						rs.getString("message"),
						rs.getTimestamp("created_at")						
						);
			}	
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		return message;
	}

	public static Vector<Message> getChatList(String movietitle) throws SQLException, NamingException {
		Vector<Message> chatList = new Vector<Message>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DataSource ds = getDataSource();
		
		try {
			conn = ds.getConnection();

			// 질의 준비
			stmt = conn.prepareStatement("SELECT * FROM chats WHERE movietitle LIKE ?");
			stmt.setString(1, "%" + movietitle + "%");
			
			// 수행
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				chatList.add(new Message(rs.getInt("id"),
						rs.getString("movietitle"),
						rs.getString("title"),
						rs.getString("userid"),
						rs.getString("contents"),
						rs.getString("message"),
						rs.getTimestamp("created_at")));
			}
			
		} 
		// 비슷한것을 찾을수 없을 때
		catch (SQLException e) {}
		finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		return chatList;
	}
	public static PageResult<Message> getSearchPage(int page, int numItemsInPage, String movietitle) 
			throws SQLException, NamingException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;		
		DataSource ds = getDataSource();
		
		if (page <= 0) {
			page = 1;
		}
		
		PageResult<Message> result = new PageResult<Message>(numItemsInPage, page);

		int startPos = (page - 1) * numItemsInPage;

		try {
			conn = ds.getConnection();
			// chats 테이블: chat 수 페이지수 계산
			stmt = conn.prepareStatement("SELECT COUNT(*) FROM chats  WHERE movietitle LIKE ?");
			stmt.setString(1, "%" + movietitle + "%");
			
			rs = stmt.executeQuery();
			rs.next();

			result.setNumItems(rs.getInt(1));

			rs.close();
			rs = null;
			stmt.close();
			stmt = null;

			// chats 테이블 SELECT
			stmt = conn.prepareStatement("SELECT * FROM chats WHERE movietitle LIKE ? ORDER BY movietitle LIMIT " + startPos + ", " + numItemsInPage);
			stmt.setString(1, "%" + movietitle + "%");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				result.getList().add(new Message(rs.getInt("id"),
						rs.getString("movietitle"),
						rs.getString("title"),
						rs.getString("userid"),
						rs.getString("content"),
						rs.getString("message"),
						rs.getTimestamp("created_at")						
						));
			}
		} 
		// 비슷한것을 찾을수 없을 때
		catch (SQLException e) {}
		finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		return result;		
	}
}
