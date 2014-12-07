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

public class UserAuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> errorMsgs = new ArrayList<String>();
		String actionUrl = "";
		request.setCharacterEncoding("UTF-8");
		try {
			String userid = request.getParameter("userid");
			String pwd = request.getParameter("pwd");
			User user = UserDAO.checkIdPwd(userid, pwd);
			if(user != null) {// 성공
				request.setAttribute("user", user);
				HttpSession session = request.getSession();
				session.setAttribute("idx", Integer.toString(user.getId()));
				session.setAttribute("userid", user.getUserid());
				session.setAttribute("name", user.getName());
				session.setAttribute("email", user.getEmail());
				response.sendRedirect(request.getContextPath() + "/index.jsp");
				return;
			} else if(UserDAO.checkID(request.getParameter("userid")) == null) {	// ID 없을 때
				errorMsgs.add("존재하지 않는 ID입니다.");
				actionUrl = "error.jsp";
			} else {	// 비밀번호 틀릴 때
				errorMsgs.add("비밀번호가 틀립니다.");
				actionUrl = "error.jsp";
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
