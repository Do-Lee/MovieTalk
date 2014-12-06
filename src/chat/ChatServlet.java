package chat;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.json.simple.*;

import common.PageResult;

@WebServlet("/ChatServlet")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChatServlet() {
		super();
	}
	
	private String getMode(HttpServletRequest request) {
		return (String) request.getParameter("_method");
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String current_name = "";

		if(session != null && session.getAttribute("name") != null) {
			current_name = (String) session.getAttribute("name");
		}
		
		System.out.println(current_name);
		
		int last = -1;
		
		try {
			last = Integer.parseInt(request.getParameter("last"));
		} 
		catch(NumberFormatException e) {}

		JSONObject resultObj = new JSONObject();

		try {
			List<Message> chatList = ChatDAO.getChatList(last);
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		request.setCharacterEncoding("UTF-8");
		String op = request.getParameter("op");
		
		
		int id = getIntFromParameter(request.getParameter("id"), -1);
		
		if (op == null && id > 0) {
			op = "show";
		}
		
//		if(getMode(request).equals("createChat")) {
//			// 방 개설 함수
//		} 
			try {
				if (op.equals("search")) {
					String actionUrl = "";
					String query = request.getParameter("query");
					int page = getIntFromParameter(request.getParameter("page"), 1);
					PageResult<Message> chats = ChatDAO.getSearchPage(page, 10, query);
					for(Message message : chats.getList()) System.out.println(message.toString());
					request.setAttribute("chats", chats);
					request.setAttribute("page", page);
					actionUrl = "search.jsp";
					
					RequestDispatcher dispatcher = request.getRequestDispatcher(actionUrl);
					dispatcher.forward(request, response);
				} else {
					HttpSession session = request.getSession(true);

					String userid = (String) session.getAttribute("name");
					String content = request.getParameter("content");
					
					if(userid == null) {
						return;
					}
					
					if (ChatDAO.sendMessage(new Message(userid, content))) {					
						response.getWriter().write("ok");
					} 
					else {
						response.getWriter().write("메세지 전송에 실패했습니다..");
					}
				}
				
			} 
			catch (Exception e) {
				response.getWriter().write(e.getMessage());
			}
	}

}
