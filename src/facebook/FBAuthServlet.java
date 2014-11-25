package facebook;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restfb.types.User;

public class FBAuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String code = request.getParameter("code");
		
		request.setCharacterEncoding("UTF-8");
		
		if(code == null) {
			String oauthUTL = Facebook.getOAuthURL();
			response.sendRedirect(oauthUTL);
		} else {
			Facebook facebook = Facebook.getInstance(code);
			
			User me = facebook.getCurrentUser();
			List<User> friends = facebook.getFriends();
			
			request.setAttribute("me", me);
			request.setAttribute("friends", friends);
			request.setAttribute("facebook", facebook);
			
			RequestDispatcher view = request.getRequestDispatcher("myFacebook.jsp");
			view.forward(request, response);
		}
		
		
		
	}
	
}
