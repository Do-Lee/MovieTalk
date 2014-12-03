package user;

import java.io.IOException;
import java.io.PrintWriter;
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
		String defaultContentType = response.getContentType();
		request.setCharacterEncoding("UTF-8");
		try {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=utf-8");
			if(UserDAO.checkIdPwd(request.getParameter("userid"), request.getParameter("pwd")) != null) {// 성공
				HttpSession session = request.getSession();
				session.setAttribute("id", request.getParameter("userid"));
				session.setAttribute("pwd", request.getParameter("pwd"));
				actionUrl = "index.jsp";
			} else if(UserDAO.findById(request.getParameter("userid")) == null) {	// ID 없을 때
				out.println("<script>");  
				out.println("alert('ID가 없습니다. 회원가입을 하시기 바랍니다.');");  
				out.println("</script>");
				actionUrl = "register.jsp";
			} else {	// 비밀번호 틀릴 때
				out.println("<script>");  
				out.println("alert('ID가 없습니다. 회원가입을 하시기 바랍니다.');");  
				out.println("</script>");
				actionUrl = "login.jsp";
			}
		} catch (NamingException | SQLException e) {
			errorMsgs.add(e.getMessage());
			e.printStackTrace();
		}
		response.setContentType(defaultContentType);
		request.setAttribute("errorMsgs", errorMsgs);
		RequestDispatcher dispatcher = request.getRequestDispatcher(actionUrl);
		dispatcher.forward(request,  response);
	}
}
