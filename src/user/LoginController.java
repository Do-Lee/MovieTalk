package user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		List<String> errorMsgs = new ArrayList<String>();
		String actionUrl = "";
		request.setCharacterEncoding("UTF-8");
		try {
			if(UserDAO.checkIdPwd(request.getParameter("userid"), request.getParameter("pwd")) != null) {// 성공
				HttpSession session = request.getSession();
				session.setAttribute("id", request.getParameter("userid"));
				actionUrl = "index.jsp";
			} else if(UserDAO.findById(request.getParameter("userid")) == null) {	// ID 없을 때
				actionUrl = "register.jsp";
			} else {	// 비밀번호 틀릴 때
				actionUrl = "login.jsp";
			}
		} catch (NamingException | SQLException e) {
			errorMsgs.add(e.getMessage());
			e.printStackTrace();
		}
		request.setAttribute("errorMsgs", errorMsgs);
		RequestDispatcher dispatcher = request.getRequestDispatcher(actionUrl);
		dispatcher.forward(request,  response);
	}
}
