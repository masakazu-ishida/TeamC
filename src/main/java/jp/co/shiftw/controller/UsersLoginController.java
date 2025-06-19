package jp.co.shiftw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.shiftw.dto.UsersDTO;
import jp.co.shiftw.service.UsersService;

/**
 * Servlet implementation class UsersLoginController
 */
@WebServlet("/UsersLoginController")
public class UsersLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsersLoginController() {
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
		//JSPへフォワード
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/main/UsersLogin.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		//ユーザー情報の取得
		String userId = request.getParameter("user_id");
		String password = request.getParameter("password");

		UsersDTO dto = UsersService.loginUsers(userId, password);

		request.setAttribute("UsersDTO", dto);

		String source = (String) request.getAttribute("source");
		//IDパスワードチェック処理
		if (dto != null) {

			if (source == null || source == "") {
				//メイン画面にフォワード
				String path = "/MainController";
				RequestDispatcher rd = request.getRequestDispatcher(path);
				rd.forward(request, response);

				//カート一覧から飛んできた場合
			} else if (source.equals("1")) {
				String path = "/CartListController";
				RequestDispatcher rd = request.getRequestDispatcher(path);
				rd.forward(request, response);

			} else if (source.equals("2")) {
				String path = "/CartAddController";
				RequestDispatcher rd = request.getRequestDispatcher(path);
				rd.forward(request, response);

			}

		} else {
			String path = "/UsersLogin.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		}

	}
}
