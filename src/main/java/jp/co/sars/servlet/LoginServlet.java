package jp.co.sars.servlet;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.sars.dto.UserDTO;
import jp.co.sars.service.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = "/WEB-INF/main.jsp"; //ログイン先パス 現在はメイン
		String message = "";
		request.setCharacterEncoding("UTF-8");

		//JSPで入力した値を取得
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");

		//IDまたはパスワードどちらか1つでも入力していない場合ログイン画面に戻す。
		if (userId.isEmpty() || password.isEmpty()) {
			path = "/WEB-INF/login.jsp";
			message = "IDまたはパスワードを入力してください";
			request.setAttribute("message", message);

			RequestDispatcher rd = request.getRequestDispatcher(path);

			rd.forward(request, response);

			return;

		}

		//LoginServiceの中で主キー検索後にパスワード判定
		LoginService service = new LoginService();
		UserDTO dto = new UserDTO();
		try {

			dto = service.Login(userId, password);

			if (dto == null) {
				message = "IDまたはパスワードが間違っています";
				path = "/WEB-INF/login.jsp";
				request.setAttribute("message", message);

				RequestDispatcher rd = request.getRequestDispatcher(path);

				rd.forward(request, response);

				return;
			}

			//セッション開始
			HttpSession session = request.getSession(true);
			session.setAttribute("userId", dto.getUserId());

			RequestDispatcher rd = request.getRequestDispatcher(path);

			rd.forward(request, response);

		} catch (SQLException | ServletException e) {
			e.printStackTrace();
		}
	}

}
