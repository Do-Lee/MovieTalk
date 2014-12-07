package chat;
import java.sql.*;
import java.util.*;
import javax.naming.*;
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
						rs.getString("image"),
						rs.getString("opener"),
						rs.getString("writer"),
						rs.getString("contents"),
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
	
	public static PageResult<Message> getPage(int page, String opener, int numItemsInPage) throws SQLException, NamingException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;		

		if ( page <= 0 ) {
			page = 1;
		}
		
		DataSource ds = getDataSource();
		PageResult<Message> result = new PageResult<Message>(numItemsInPage, page);
		int startPos = (page - 1) * numItemsInPage;
		
		try {
			conn = ds.getConnection();
			
			stmt = conn.prepareStatement("SELECT COUNT(*) FROM chats WHERE opener = ?");
			stmt.setString(1, opener);
			
			// chats 테이블: chat 수 페이지수 개산
	 		rs = stmt.executeQuery();
	 		
			rs.next();
			
			result.setNumItems(rs.getInt(1));
			
			rs.close();
			rs = null;
			stmt.close();
			stmt = null;
			
	 		// chats 테이블 SELECT
			stmt = conn.prepareStatement("SELECT * FROM chats WHERE opener = ? ORDER BY id LIMIT " + startPos + ", " + numItemsInPage);
			stmt.setString(1, opener);
			rs = stmt.executeQuery();
			while(rs.next()) {
				result.getList().add(new Message(rs.getInt("id"),
						rs.getString("movietitle"),
						rs.getString("title"),
						rs.getString("image"),
						rs.getString("opener"),
						rs.getString("writer"),
						rs.getString("contents"),
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
	
	public static List<Message> getChatList(int last, String title) throws SQLException, NamingException {
		
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
				stmt = conn.prepareStatement("SELECT * FROM chats WHERE id > ? and title = ?");
				stmt.setInt(1,  last);
				stmt.setString(2, title);
				
			} else {
				// 마지막 100개의 메시지만..
				stmt = conn.prepareStatement("SELECT * FROM "
						+ "(SELECT * FROM chats WHERE title = ? ORDER BY id DESC LIMIT 100 ) t " 
						+ "ORDER BY id ;");
				stmt.setString(1, title);
			}

			// 수행
			rs = stmt.executeQuery();

			while (rs.next()) {
				Message msg = new Message(rs.getInt("id"), rs.getString("movietitle"), rs.getString("title"), 
										rs.getString("opener"), rs.getString("writer"), rs.getString("image"),
										rs.getString("contents"), rs.getString("message"), rs.getTimestamp("created_at"));
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
			stmt = conn.prepareStatement("UPDATE chats SET writer = ?, message = ? WHERE title = ?"); // problem
			stmt.setString(1, msg.getWriter());
			stmt.setString(2, msg.getMessage());
			stmt.setString(3, msg.getTitle());
			
			// 수행
			result = stmt.executeUpdate();
			
			System.out.println(msg.getWriter());
			System.out.println(msg.getMessage());
			System.out.println(msg.getTitle());
		} 
		finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}

		return (result == 1);
	}
	
	public int getMessages(String title) throws NamingException, SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DataSource ds = getDataSource();
		
		try {
			conn = ds.getConnection();
			// 질의 준비
			stmt = conn.prepareStatement("SELECT count(message) FROM chats WHERE title = ?");
			stmt.setString(1, title);
			
			// 수행
			rs = stmt.executeQuery();
//			if (rs.next()) {
//				return rs.getIn
//			}	
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		return 0;
	}
	
	public ArrayList<Message> findAllChatsByTitle(String title) throws NamingException, SQLException {
		ArrayList<Message> chatList = null;
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
			chatList = new ArrayList<Message>();
			if (rs.next()) {
				message = new Message(rs.getInt("id"),
						rs.getString("movietitle"),
						rs.getString("title"),
						rs.getString("image"),
						rs.getString("opener"),
						rs.getString("writer"),
						rs.getString("contents"),
						rs.getString("message"),
						rs.getTimestamp("created_at"));
				chatList.add(message);
			}	
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		return chatList;
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
						rs.getString("image"),
						rs.getString("opener"),
						rs.getString("writer"),
						rs.getString("contents"),
						rs.getString("message"),
						rs.getTimestamp("created_at"));
			}	
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		return message;
	}
	
	public static boolean remove(int id) throws NamingException, SQLException {
		int result;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DataSource ds = getDataSource();

		try {
			conn = ds.getConnection();

			// 질의 준비
			stmt = conn.prepareStatement("DELETE FROM chats WHERE id=?");
			stmt.setInt(1, id);

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
	
	public Message getNewMessage() throws NamingException, SQLException {
		Message message = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DataSource ds = getDataSource();
		
		try {
			conn = ds.getConnection();
			// 질의 준비
			stmt = conn.prepareStatement("SELECT * FROM chats ORDER BY created_at DESC LIMIT 1");
			
			// 수행
			rs = stmt.executeQuery();

			if (rs.next()) {
				message = new Message(rs.getInt("id"),
						rs.getString("movietitle"),
						rs.getString("title"),
						rs.getString("image"),
						rs.getString("opener"),
						rs.getString("writer"),
						rs.getString("contents"),
						rs.getString("message"),
						rs.getTimestamp("created_at"));
			}	
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		return message;
	}
	
	public Message getHotMessage() throws NamingException, SQLException {
		Message message = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DataSource ds = getDataSource();
		
		try {
			conn = ds.getConnection();
			// 질의 준비
			/*		stmt = conn.prepareStatement("SELECT COUNT(message) FROM chats "
					+ "WHERE title=? GROUP BY MINUTE(created_at)");
			//stmt.setString(1, x);
			// 수행
			rs = stmt.executeQuery();

			if (rs.next()) {
				message = new Message(rs.getInt("id"),
						rs.getString("movietitle"),
						rs.getString("title"),
						rs.getString("image"),
						rs.getString("opener"),
						rs.getString("writer"),
						rs.getString("contents"),
						rs.getString("message"),
						rs.getTimestamp("created_at"));
			}	*/
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		return message;
	}


}