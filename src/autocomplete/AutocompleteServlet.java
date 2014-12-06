package autocomplete;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Vector;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import chat.*;

/**
 * Servlet implementation class ContrySuggest
 */
@WebServlet("/DataSuggestServlet")
public class AutocompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AutocompleteServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");

		String query = request.getParameter("query");
		PrintWriter out = response.getWriter();
		
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
					if( message.getTitle().contains(query)) {	
						out.println("<li onclick=\"fill('"+message.getTitle()+"');\">"
									+ message.getTitle()+"</li>"); 
					}
				}
			}
		}
		out.close();
	}
}
