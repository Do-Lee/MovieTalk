package auto;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Vector;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.Movie;
import movie.MovieDAO;

/**
 * Servlet implementation class ContrySuggest
 */
@WebServlet("/DataSuggestServlet")
public class DataSuggestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DataSuggestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");

		String query=request.getParameter("query");
		PrintWriter out=response.getWriter();
		Vector<Movie> movieList = null;
		try {
			movieList = MovieDAO.getMovieList(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (query == null){
			out.println("<div>Cannot access</div>");
		}else{
			if(query.length() > 0)
			{
				for( Movie data : movieList )
				{
					if( data.getTitle().contains(query))
					{	
						out.println("<li onclick=\"fill('"+data+"');\">"+data+"</li>"); 
					}
				}
			}
		}
		out.close();
	}
}
