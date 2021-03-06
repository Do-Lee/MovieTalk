package user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.PageResult;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserServlet() {
        super();
    }
    
	private int getIntFromParameter(String str, int defaultValue) {
		int id;
		
		try {
			id = Integer.parseInt(str);
		} 
		catch (Exception e) {
			id = defaultValue;
		}
		return id;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		String actionUrl = "";
		boolean ret;
		
		HttpSession session = request.getSession();
		
		int id = getIntFromParameter(request.getParameter("id"), -1);
		
		if (op == null && id > 0) {
			op = "show";
		}
		
		try {
			if (op == null || op.equals("index")) {
				int page = getIntFromParameter(request.getParameter("page"), 1);
				PageResult<User> users = UserDAO.getPage(page, 10);
				request.setAttribute("users", users);
				request.setAttribute("page", page);
				actionUrl = "index.jsp";
			} else if(op.equals("login")) {
				actionUrl = "login.jsp";
			} else if (op.equals("show")) {
				User user = UserDAO.findById(id);
				request.setAttribute("user", user);
				actionUrl = "update.jsp";
			} else if (op.equals("update")) {
				User user = UserDAO.findById(id);
				request.setAttribute("user", user);
				request.setAttribute("method", "PUT");
				actionUrl = "update.jsp";
			} else if (op.equals("delete")) {
				ret = UserDAO.remove(id);
				request.setAttribute("result", ret);
				
				if (ret) {
					request.setAttribute("msg", "사용자 정보가 삭제되었습니다.");
					actionUrl = "success.jsp";
				} else {
					request.setAttribute("error", "사용자 정보 삭제에 실패했습니다.");
					actionUrl = "error.jsp";
				}	
			} else if (op.equals("register")) {
				request.setAttribute("method", "POST");
				request.setAttribute("user", new User());
				actionUrl = "register.jsp";
			} else if (op.equals("admin")) {
				int page = getIntFromParameter(request.getParameter("page"), 1);
				PageResult<User> users = UserDAO.getPage(page, 10);
				request.setAttribute("users", users);
				
				actionUrl = "user_index.jsp";
			} else if (op.equals("mine")) {
				int page = getIntFromParameter(request.getParameter("page"), 1);
				PageResult<User> users = UserDAO.getUserPage(page, 10, (String) session.getAttribute("userid"));
				request.setAttribute("users", users);
				
				actionUrl = "user_index.jsp";
			} else if (op.equals("logout")) {
				session.invalidate();
				
				response.sendRedirect(request.getContextPath() + "/index.jsp");
				return;
			} else {
				request.setAttribute("error", "알 수 없는 명령입니다");
				actionUrl = "error.jsp";
			}
		} catch (SQLException | NamingException e) {
			request.setAttribute("error", e.getMessage());
			e.printStackTrace();
			actionUrl = "error.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(actionUrl);
		dispatcher.forward(request,  response);
		
	}

	private String getMode(HttpServletRequest request) {
		return (String) request.getParameter("_method");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean ret = false;
		String actionUrl;
		String msg;
		User user = new User();

		request.setCharacterEncoding("utf-8");
		
		String userid = (String)request.getParameter("userid");
		String name = (String)request.getParameter("nickname");
		String pwd = (String)request.getParameter("password");
		String pwd_confirm = (String)request.getParameter("pwd_confirm");
		String email = (String)request.getParameter("email");
		
		List<String> errorMsgs = new ArrayList<String>();
		
		if (getMode(request) != null) {
			switch(getMode(request)) {
			case "register":
				try {
					if (userid == null || userid.trim().length() == 0) {
						errorMsgs.add("ID를 반드시 입력해주세요.");
					} else if (UserDAO.checkID(userid) != null) {
						errorMsgs.add("이미 존재하는 ID입니다.");
					}
				} catch (NamingException | SQLException e) {
					e.printStackTrace();
				}
				
				try {
					if (name == null || name.trim().length() == 0) {
						errorMsgs.add("닉네임을 반드시 입력해주세요.");
					} else if (UserDAO.checkName(name) != null) {
						errorMsgs.add("이미 존재하는 닉네임입니다.");
					}
				} catch (NamingException | SQLException e) {
					e.printStackTrace();
				}
				break;
			case "update":
				try {
					if (name == null || name.trim().length() == 0) {
						errorMsgs.add("닉네임을 반드시 입력해주세요.");
					} else if (UserDAO.checkName(name) != null) {
						if (!UserDAO.checkID(userid).getName().equals(name))	// 타인이 해당 닉네임을 가지고 있을 경우
							errorMsgs.add("이미 존재하는 닉네임입니다.");
					}
				} catch (NamingException | SQLException e) {
					e.printStackTrace();
				}
				break;
			}
			
			if (pwd == null || pwd.length() < 6) {
				errorMsgs.add("비밀번호는 6자 이상 입력해주세요.");
			} 
			
			if (!pwd.equals(pwd_confirm)) {
				errorMsgs.add("비밀번호가 일치하지 않습니다.");
			}
			
			if (email == null || email.trim().length() == 0) {
				errorMsgs.add("E-mail을 반드시 입력해주세요.");
			}
			
			user.setPwd(pwd);
			user.setUserid(userid);
			user.setName(name);
			user.setEmail(email);
		} else {
			user.setId(getIntFromParameter((String)request.getParameter("id"), -1));
		}
		
		try {
			if (getMode(request).equals("register")) {
				ret = UserDAO.create(user);
				msg = "<b>" + name + "</b>님의 사용자 정보가 등록되었습니다.";
			} else {
				ret = UserDAO.update(user);
				actionUrl = "success.jsp";
				msg = "<b>" + name + "</b>님의 사용자 정보가 수정되었습니다.";
			}
			if (ret != true) {
				errorMsgs.add("변경에 실패했습니다.");
				actionUrl = "error.jsp";
			} else {
				request.setAttribute("msg", msg);
				actionUrl = "success.jsp";
				
			}
		} catch (SQLException | NamingException e) {
			actionUrl = "error.jsp";
		}
		request.setAttribute("errorMsgs", errorMsgs);
		RequestDispatcher dispatcher = request.getRequestDispatcher(actionUrl);
		dispatcher.forward(request,  response);
	}
}
