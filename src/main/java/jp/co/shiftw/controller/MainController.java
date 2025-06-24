package jp.co.shiftw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.shiftw.dto.CategoriesDTO;
import jp.co.shiftw.service.CategoryTagService;

/**
 * Servlet implementation class Main
 */
@WebServlet("/MainController")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		request.setCharacterEncoding("UTF-8");

		String path = "/WEB-INF/main/main.jsp";

		List<CategoriesDTO> categories = CategoryTagService.categories();
		request.setAttribute("categories", categories);

		HttpSession session = request.getSession(false);

		if (session == null) {
			request.setAttribute("url", "/Shift_W/UsersLoginController");
			request.setAttribute("urlName", "ログイン");

		} else {
			request.setAttribute("url", "");
			request.setAttribute("urlName", "会員情報の変更");
			request.setAttribute("logOutUrl", "/Shift_W/logout");
			request.setAttribute("logOut", "ログアウト");
		}

		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
