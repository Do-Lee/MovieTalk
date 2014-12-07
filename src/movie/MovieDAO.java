package movie;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import common.PageResult;

public class MovieDAO {
	public static DataSource getDataSource() throws NamingException {
		Context initContext = null;
		Context environmentContext = null;

		initContext = new InitialContext();
		environmentContext = (Context) initContext.lookup("java:comp/env");

		return (DataSource) environmentContext.lookup("jdbc/WebDB");
	}
	public static PageResult<Movie> getPage(int page, int numItemsInPage) 
			throws SQLException, NamingException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;		

		if (page <= 0) {
			page = 1;
		}

		DataSource ds = getDataSource();
		PageResult<Movie> result = new PageResult<Movie>(numItemsInPage, page);

		int startPos = (page - 1) * numItemsInPage;

		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();

			// movies 테이블: movie 수 페이지수 개산
			rs = stmt.executeQuery("SELECT COUNT(*) FROM movies");
			rs.next();

			result.setNumItems(rs.getInt(1));

			rs.close();	rs = null;
			stmt.close();	stmt = null;

			// movies 테이블 SELECT
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM movies ORDER BY title LIMIT " + startPos + ", " + numItemsInPage);

			while(rs.next()) {
				result.getList().add(new Movie(rs.getInt("id"),
						rs.getString("title"),
						rs.getString("link"),
						rs.getString("image"),
						rs.getString("subtitle"),
						rs.getString("pubdate"),
						rs.getString("director"),
						rs.getString("actor"),
						Float.toString(rs.getFloat("userrating"))));
			}
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}

		return result;
	}

	public static Movie findById(int id) throws NamingException, SQLException{
		Movie movie = null;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		DataSource ds = getDataSource();

		try {
			conn = ds.getConnection();

			// 질의 준비
			stmt = conn.prepareStatement("SELECT * FROM movies WHERE id = ?");
			stmt.setInt(1, id);

			// 수행
			rs = stmt.executeQuery();

			if (rs.next()) {
				movie = new Movie(rs.getInt("id"),
						rs.getString("title"),
						rs.getString("link"),
						rs.getString("image"),
						rs.getString("subtitle"),
						rs.getString("pubdate"),
						rs.getString("director"),
						rs.getString("actor"),
						Float.toString(rs.getFloat("userrating")));

			}	
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}

		return movie;
	}

	// 영화 제목을 입력받아, 네이버 영화 검색 API로부터 parsing된 영화 정보를 얻어 DB에 삽입
	// API로부터 모든 영화 정보를 가져올 순 없음...(검색어 기반으로 XML을 출력해주기 때문에 검색어가 없으면 아무 정보도 출력 x) 
	public static void create(String title) throws SQLException, NamingException {
		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DataSource ds = getDataSource();

		try {
			conn = ds.getConnection();
			for(Movie movie: RSSParser.getAllMovies(title)) {
				if (findMovie(movie.getMovietitle(), movie.getSubtitle()) == null) {
					stmt = conn.prepareStatement(
							"INSERT INTO movies(title, link, image, subtitle, pubdate, director, actor, userrating) "
									+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)"
							);
					stmt.setString(1, movie.getMovietitle());
					stmt.setString(2, movie.getLink());
					stmt.setString(3, movie.getImage());
					stmt.setString(4, movie.getSubtitle());
					stmt.setString(5, movie.getPubDate());
					stmt.setString(6, movie.getDirector());
					stmt.setString(7, movie.getActor());
					stmt.setFloat(8, movie.getUserRatingInFloat());
					result = stmt.executeUpdate();
				}
			}
		} 
		
		finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		//return (result == 1);
	}

	public static boolean createChat(Movie movie) throws SQLException, NamingException {
		int result;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		DataSource ds = getDataSource();

		try {
			conn = ds.getConnection();
			
			// 질의 준비
			stmt = conn.prepareStatement("INSERT INTO movies(movietitle, chattitle, opener, contents) "
					+ "VALUES (?, ?, ?, ?);");
			stmt.setString(1, movie.getMovietitle());
			stmt.setString(2, movie.getChattitle());
			stmt.setString(3, movie.getOpener());
			stmt.setString(4, movie.getContents());
			
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
	
	public static boolean update(Movie movie) throws SQLException, NamingException {
		int result;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DataSource ds = getDataSource();

		try {
			conn = ds.getConnection();

			// 질의 준비
			stmt = conn.prepareStatement(
					"UPDATE movies " +
							"SET  title = ?, link = ?, image = ?, subtitle = ?, pubdate = ?, director = ?, actor = ?, userrating = ?" +
							"WHERE id=?"
					);
			stmt.setString(1,  movie.getMovietitle());
			stmt.setString(2,  movie.getLink());
			stmt.setString(3,  movie.getImage());
			stmt.setString(4,  movie.getSubtitle());
			stmt.setString(5,  movie.getPubDate());
			stmt.setString(6,  movie.getDirector());
			stmt.setString(7,  movie.getActor());
			stmt.setFloat(8,  movie.getUserRatingInFloat());
			stmt.setInt(9,  movie.getId());

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
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DataSource ds = getDataSource();

		try {
			conn = ds.getConnection();

			// 질의 준비
			stmt = conn.prepareStatement("DELETE FROM movies WHERE id=?");
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
	
	public static Movie findMovie(String title) throws NamingException, SQLException {
		Movie movie = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DataSource ds = getDataSource();
		create(title);
		try {
			conn = ds.getConnection();
			// 질의 준비
			stmt = conn.prepareStatement("SELECT * FROM movies WHERE title = ?");
			stmt.setString(1, title);
			
			// 수행
			rs = stmt.executeQuery();

			if (rs.next()) {
				movie = new Movie(rs.getString("title"),
						rs.getString("subtitle"),
						rs.getString("link"),
						rs.getString("image"),
						rs.getString("director"),
						rs.getString("actor"),
						rs.getString("pubdate"),
						Float.toString(rs.getFloat("userrating")));
			}	
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		return movie;
	}
	
	public static Movie findMovie(String title, String subtitle) throws NamingException, SQLException {
		Movie movie = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DataSource ds = getDataSource();
		
		try {
			conn = ds.getConnection();
			// 질의 준비
			stmt = conn.prepareStatement("SELECT * FROM movies WHERE title = ? and subtitle = ?");
			stmt.setString(1, title);
			stmt.setString(2, subtitle);
			
			// 수행
			rs = stmt.executeQuery();

			if (rs.next()) {
				movie = new Movie(rs.getString("title"),
						rs.getString("subtitle"),
						rs.getString("link"),
						rs.getString("image"),
						rs.getString("director"),
						rs.getString("actor"),
						rs.getString("pubdate"),
						Float.toString(rs.getFloat("userrating")));
			}	
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		return movie;
	}

	public static Vector<Movie> getMovieList(String title) throws SQLException, NamingException {
		Vector<Movie> movieList = new Vector<Movie>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DataSource ds = getDataSource();
		
		try {
			conn = ds.getConnection();

			// 질의 준비
			stmt = conn.prepareStatement("SELECT * FROM movies WHERE title LIKE ?");
			stmt.setString(1, "%" + title + "%");
			// 수행
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Movie movie = new Movie(rs.getInt("id"), rs.getString("title"), rs.getString("link"),
								rs.getString("image"), rs.getString("subtitle"), rs.getString("pubdate"),
								rs.getString("director"), rs.getString("actor"), Float.toString(rs.getFloat("userrating")));
				movieList.add(movie);
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
		return movieList;
	}
	
	public static PageResult<Movie> getSearchPage(int page, int numItemsInPage, String title) 
			throws SQLException, NamingException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;		
		DataSource ds = getDataSource();
		
		if (page <= 0) {
			page = 1;
		}
		
		PageResult<Movie> result = new PageResult<Movie>(numItemsInPage, page);

		int startPos = (page - 1) * numItemsInPage;

		try {
			conn = ds.getConnection();
			// movies 테이블: movie 수 페이지수 계산
			stmt = conn.prepareStatement("SELECT COUNT(*) FROM movies WHERE title LIKE %?%");
			stmt.setString(1, title);
			
			rs = stmt.executeQuery();
			rs.next();

			result.setNumItems(rs.getInt(1));

			rs.close();
			rs = null;
			stmt.close();
			stmt = null;

			// movies 테이블 SELECT
			stmt = conn.prepareStatement("SELECT * FROM movies WHERE title LIKE %?% ORDER BY title LIMIT " + startPos + ", " + numItemsInPage);
			stmt.setString(1, title);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				result.getList().add(new Movie(rs.getInt("id"),
						rs.getString("title"),
						rs.getString("link"),
						rs.getString("image"),
						rs.getString("subtitle"),
						rs.getString("pubdate"),
						rs.getString("director"),
						rs.getString("actor"),
						Float.toString(rs.getFloat("userrating"))));
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
