package autocomplete;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Vector;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import movie.Movie;
import movie.MovieDAO;

@WebServlet("/autocomplete")
public class AutocompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AutocompleteServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");

		String query = request.getParameter("query");
		PrintWriter out = response.getWriter();
		
		Vector<Movie> movieList = null;
		try {
			movieList = MovieDAO.getMovieList(query);
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
			
		if (query == null){
			out.println("<div>Cannot access</div>");
		} else{
			if(query.length() > 0) {
				for( Movie movie : movieList ) {						
					if( movie.getMovietitle().contains(query)) {	
						out.println("<li onclick=\"fill('" + movie.getMovietitle() + "');\">"
									+ movie.getMovietitle()+"</li>"); 
					}
				}	
			}
		}
		
	}
}

