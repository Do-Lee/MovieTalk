package facebook;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.restfb.types.User;


@WebServlet("/FBAuthServlet") 
public class FBAuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code"); 

		request.setCharacterEncoding("UTF-8");

		// facebook에서 전달받은 인증 code가 없을 경우
		if( code == null){			
			String oauthURL = Facebook.getOAuthURL();
			response.sendRedirect (oauthURL);
		} else{			
			Facebook facebook = Facebook.getInstance(code); 
			
			// 현재 페이스북 사용자 정보를 가져온다.
			User me = facebook.getCurrentUser();

			// request의 attribute로 담는다.
			request.setAttribute("me", me);
			request.setAttribute("facebook", facebook);
			
			String fbid = me.getId();
			String actionUrl="";
			
			try {
				FacebookUser fbuserinfo = FacebookUserDAO.findByFbId(fbid);
				if(fbuserinfo == null ) {
					actionUrl = "fbuser?op=register";
				} else {
					HttpSession session = request.getSession();
					session.setAttribute("id", fbid);
					actionUrl = "fbuser?op=index";
				}
			} catch (SQLException | NamingException e) {
				request.setAttribute("error", e.getMessage());
				e.printStackTrace();
				actionUrl = "error.jsp";
			}
			
			RequestDispatcher view = request.getRequestDispatcher(actionUrl); 
			view.forward(request, response);
		}
	}
}
