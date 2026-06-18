package jp.co.sars.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.sars.dto.ItemsInCartDTO;
import jp.co.sars.service.RemoveFromCartConfirmService;

/**
 * Servlet implementation class RemoveFromCartConfirmServlet
 */
@WebServlet("/cartDelete")
public class RemoveFromCartConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveFromCartConfirmServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String path = "/WEB-INF/removeFromCartConfirm.jsp";

		//セッション情報の取得
		HttpSession session = request.getSession(true);

		//取得した情報をString型に変換
		String userId = (String) session.getAttribute("userId");

		//セッションしていなかったらエラー画面へ遷移
		if (userId == null) {
			path = "";//エラー画面のパス入れる
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);

		} else {

			String itemId = request.getParameter("itemId");
			int item = Integer.parseInt(itemId);

			RemoveFromCartConfirmService service = new RemoveFromCartConfirmService();

			ItemsInCartDTO cartDeleteItem = service.execute(userId, item);

			request.setAttribute("item", cartDeleteItem);
			request.setAttribute("userId", userId);

			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);

		}
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
