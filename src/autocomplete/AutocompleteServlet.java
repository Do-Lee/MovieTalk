package autocomplete;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import movie.Movie;
import movie.MovieDAO;
import movie.RSSParser;
import chat.*;

@WebServlet("/DataSuggestServlet")
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
		
		String mode = (String)request.getParameter("_method");
		
		if (mode == null) {
			Vector<Message> chatList = null;
			try {
				chatList = ChatDAO.getChatList(query);
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
			}
			
			if (query == null){
				out.println("<div>Cannot access</div>");
			}
			else{
				if(query.length() > 0) {
					for( Message message : chatList ) {
						System.out.println(message.getMovietitle());
						
						if( message.getMovietitle().contains(query)) {	
							out.println("<li onclick=\"fill('" + message.getMovietitle() + "');\">"
										+ message.getMovietitle()+"</li>"); 
						}
					}
				}
			}
		} else {
			ArrayList<Movie> movieList = RSSParser.getAllMovies(query);
			try {
				MovieDAO.create(query);
			} catch (SQLException | NamingException e) {}
			
			if (query == null){
				out.println("<div>Cannot access</div>");
			}
			else{
				if(query.length() > 0) {
					for( Movie movie : movieList ) {
						System.out.println(movie.getTitle());
						
						if( movie.getTitle().contains(query)) {	
							out.println("<li onclick=\"fill('" + movie.getTitle() + "');\">"
										+ movie.getTitle() + "</li>"); 
						}
					}
				}
			}
		}
	}
}

