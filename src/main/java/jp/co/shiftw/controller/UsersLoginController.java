package jp.co.shiftw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

		//IDパスワードチェック処理
		if (dto != null) {

<<<<<<< HEAD
			//遷移先の判定処理
			switch (source) {
			//カート一覧へ遷移
			case 1: {
=======
			HttpSession session = request.getSession();
			session.setAttribute("userId", dto.getName());
			session.setAttribute("pass", dto.getPassword());
>>>>>>> branch 'master' of https://github.com/masakazu-ishida/TeamC.git

			if (session.getAttribute("source") != null) {

				int source = (int) session.getAttribute("source");
				//遷移先の判定処理
				switch (source) {
				//カート一覧へ遷移
				case 1:

<<<<<<< HEAD
				break;
			}
=======
					String path = "/CartListController";
>>>>>>> branch 'master' of https://github.com/masakazu-ishida/TeamC.git

<<<<<<< HEAD
			//カート追加へ遷移
			case 2:{
=======
					RequestDispatcher req1 = request.getRequestDispatcher(path);
					req1.forward(request, response);
>>>>>>> branch 'master' of https://github.com/masakazu-ishida/TeamC.git

<<<<<<< HEAD
				String path = "/CartAddController";
=======
					break;
>>>>>>> branch 'master' of https://github.com/masakazu-ishida/TeamC.git

				//カート追加へ遷移
				case 2:

<<<<<<< HEAD
				break;
			}
			//デフォルトでメイン画面に遷移
			default:{
			
=======
					path = "/CartAddController";

					RequestDispatcher req2 = request.getRequestDispatcher(path);
					req2.forward(request, response);

					break;
				}

			} else {
>>>>>>> branch 'master' of https://github.com/masakazu-ishida/TeamC.git
				String path = "/MainController";

				RequestDispatcher req3 = request.getRequestDispatcher(path);
				req3.forward(request, response);
			}
			}

		} else {
			//ログイン画面へ遷移
			String path = "/WEB-INF/main/UsersLogin.jsp";

			RequestDispatcher req4 = request.getRequestDispatcher(path);
			req4.forward(request, response);

		}

	}

}
