package chat;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import user.UserDAO;

import common.PageResult;

@WebServlet("/chat")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChatServlet() {
		super();
	}

	private int getIntFromParameter(String str, int defaultValue) {
		int id;

		try {
			id = Integer.parseInt(str);
		} catch (Exception e) {
			id = defaultValue;
		}
		return id;
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String op = request.getParameter("op");
		String actionUrl = "";

		HttpSession session = request.getSession();

		int id = getIntFromParameter(request.getParameter("id"), -1);

		if (op == null && id > 0) {
			op = "";
			Message msg = null;
			try {
				msg = ChatDAO.findChat((String)request.getParameter("movietitle"));
			} catch (NamingException | SQLException e) {}
			request.setAttribute("msg", msg);
		}

		if(op != null) {
			try {
				if(op.equals("admin")) {
					int page = getIntFromParameter(request.getParameter("page"), 1);
					PageResult<Message> chats = ChatDAO.getPage(page, 10);
					request.setAttribute("chats", chats);

					actionUrl = "chat_index.jsp";
				}  else if(op.equals("mine")) {
					String userid = (String) session.getAttribute("name");
					int page = getIntFromParameter(request.getParameter("page"), 1);
					PageResult<Message> chats = ChatDAO.getPage(page, userid, 10);
					request.setAttribute("chats", chats);

					actionUrl = "chat_index.jsp";
		
				} else if (op.equals("delete")) {
					boolean ret = UserDAO.remove(id);
					request.setAttribute("result", ret);

					if (ret) {
						request.setAttribute("msg", "사용자 정보가 삭제되었습니다.");
						actionUrl = "success.jsp";
					} else {
						request.setAttribute("error", "사용자 정보 삭제에 실패했습니다.");
						actionUrl = "error.jsp";
					}	
				}
			} catch (SQLException | NamingException e) {
				System.out.println("Error");
				request.setAttribute("error", e.getMessage());
				e.printStackTrace();
				actionUrl = "error.jsp";
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher(actionUrl);
			dispatcher.forward(request,  response);
		} else {
			String current_name = "";

			if(session != null && session.getAttribute("name") != null) {
				current_name = (String) session.getAttribute("name");
			}

			int last = -1;

			try {
				last = Integer.parseInt(request.getParameter("last"));
			} 
			catch(NumberFormatException e) {}

			String title = request.getParameter("title");

			JSONObject resultObj = new JSONObject();

			try {
				List<Message> chatList = ChatDAO.getChatList(last, title);
				
				JSONArray jsonList = new JSONArray();
				for(Message chat : chatList) {
					jsonList.add(chat.toJSON(current_name));
				}
				if (chatList.size() > 0) {
					last = chatList.get(chatList.size() - 1).getId();
				}

				resultObj.put("size", chatList.size());
				resultObj.put("messages", jsonList);
				resultObj.put("last", last);
			} 
			catch (Exception e) {
				e.printStackTrace();
				resultObj.put("ERROR", e.getMessage());
			} 
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(resultObj.toJSONString());

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(true);

		String userid = (String) session.getAttribute("name");
		String content = request.getParameter("content");
		String title = request.getParameter("title");

		
		if(userid == null) {
			return;
		}

		try {
			if (ChatDAO.sendMessage(new Message(title, userid, content))) {	

				response.getWriter().write("ok");
			} else {
				System.out.println("Error");
				response.getWriter().write("메세지 전송에 실패했습니다..");
			}
		} catch (Exception e) {
			response.getWriter().write(e.getMessage());
		}
	}
}