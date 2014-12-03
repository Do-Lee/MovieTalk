package user;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
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
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		try {
			//if(UserDAO.checkLogin("webmaster", "test") == null) {
			if(UserDAO.checkIdPwd(request.getParameter("userid"), request.getParameter("pwd")) == null) {// 성공
				HttpSession session = request.getSession();
				session.setAttribute("id", request.getParameter("userid"));
				response.sendRedirect("index.jsp");
			} else if(UserDAO.findById(request.getParameter("userid")) == null) {	// ID 없을 때
				
			} else {	// 비밀번호 틀릴 때
				
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}
}
