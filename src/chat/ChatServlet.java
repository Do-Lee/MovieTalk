package chat;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.json.simple.*;

@WebServlet("/ChatServlet")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChatServlet() {
		super();
	}
	
	private String getMode(HttpServletRequest request) {
		return (String) request.getParameter("_method");
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
		
		HttpSession session = request.getSession(true);

		String userid = (String) session.getAttribute("name");
		String content = request.getParameter("content");
		
		if(userid == null) {
			return;
		}
		
//		if(getMode(request).equals("createChat")) {
//			// 방 개설 함수
//		} 
			try {
				if (ChatDAO.sendMessage(new Message(userid, content))) {					
					response.getWriter().write("ok");
				} 
				else {
					response.getWriter().write("메세지 전송에 실패했습니다..");
				}
			} 
			catch (Exception e) {
				response.getWriter().write(e.getMessage());
			}
	}

}
