package movie;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.PageResult;


@WebServlet("/movie")
public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Attributes
	String tempMovieTitle;
	String tempChatTitle;
	
    public MovieServlet() {
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

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String op = request.getParameter("op");
		String movietitle = request.getParameter("movietitle");
		String chattitle = request.getParameter("chattitle");
		String actionUrl = "";
		String query = request.getParameter("query");
		
		HttpSession session = request.getSession();
		
		boolean ret = false;
		int page = getIntFromParameter(request.getParameter("page"), 1);
		
		try {
			if(movietitle != null && chattitle != null) {
				ArrayList<Movie> moives = MovieDAO.findAllMoviesByMovieTitle(movietitle);
				request.setAttribute("moives", moives);
				request.setAttribute("chattitle", chattitle);
				
				tempMovieTitle = movietitle;
				tempChatTitle = chattitle;
				
				actionUrl = "chat.jsp";
			} else if(op == null) {
				ArrayList<Movie> moives = MovieDAO.findAllMoviesByMovieTitle(tempMovieTitle);
				request.setAttribute("moives", moives);
				request.setAttribute("chattitle", tempChatTitle);
			System.out.println(tempChatTitle);
				
				actionUrl = "movie?movietitle=" + tempMovieTitle + "&chattitle=" + tempChatTitle;
			} else if (op.equals("delete")) {
				ret = MovieDAO.remove(getIntFromParameter(request.getParameter("id"), 1));
				request.setAttribute("result", ret);
				
				if (ret) {
					request.setAttribute("msg", "사용자 정보가 삭제되었습니다.");
					actionUrl = "success.jsp";
				} else {
					request.setAttribute("error", "사용자 정보 삭제에 실패했습니다.");
					actionUrl = "error.jsp";
				}
			} else if (op.equals("search")) {
				PageResult<Movie> movies = MovieDAO.getSearchPage(page, 10, query);
				request.setAttribute("movies", movies);
				request.setAttribute("page", page);
				actionUrl = "search.jsp";
			} else if(op.equals("admin")){
				PageResult<Movie> movies = MovieDAO.getPage(page, 10);
				request.setAttribute("movies", movies);

				actionUrl = "chat_index.jsp";
			} else if(op.equals("mine")) {
				String name = (String) session.getAttribute("name");
				PageResult<Movie> movies = MovieDAO.getUserPage(page, 10, name);
				request.setAttribute("movies", movies);
				movies.getList();
				
				actionUrl = "chat_index.jsp";
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String op = request.getParameter("op");
		String actionUrl = "";
		String query = request.getParameter("query");
		
		int id = getIntFromParameter(request.getParameter("id"), -1);
		
		if (op == null && id > 0) {
			op = "show";
		}
		
		try {
			if(op.equals("create")) {
				boolean ret = false;
				
				try {
					ret = MovieDAO.createChat((String)request.getParameter("movietitle"), 
							(String)request.getParameter("chattitle"), 
							(String)request.getParameter("name"), 
							(String)request.getParameter("contents"));
				} catch (SQLException | NamingException e) {
					System.out.println("Error");
					request.setAttribute("error", e.getMessage());
					e.printStackTrace();
					actionUrl = "error.jsp";
				}

				if (ret) {
					actionUrl = "index.jsp";
				} else {
					request.setAttribute("error", "중복된 Title이 존재하여 개설에 실패했습니다.");
					actionUrl = "error.jsp";
				}
			}
			else if (op == null || op.equals("search")) {
				MovieDAO.create(query.contains(" ") ? query : query.trim());
				int page = getIntFromParameter(request.getParameter("page"), 1);
				PageResult<Movie> movies = MovieDAO.getSearchPage(page, 10, query);
				for(Movie movie : movies.getList()) System.out.println(movie.toString());
				request.setAttribute("movies", movies);
				request.setAttribute("page", page);
				actionUrl = "search.jsp";
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
		dispatcher.forward(request, response);
	}
}
