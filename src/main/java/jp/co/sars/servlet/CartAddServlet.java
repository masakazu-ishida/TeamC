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

import jp.co.sars.service.CartAddService;

/**
 * Servlet implementation class CartAdd
 */
@WebServlet("/addCart")
public class CartAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartAddServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = "/cart";

		//セッション情報の取得
		HttpSession session = request.getSession(true);

		//取得した情報をString型に変換
		String userId = (String) session.getAttribute("userId");

		String itemIdStr = request.getParameter("itemId");
		String amountStr = request.getParameter("amount");

		//型変換
		int itemId = Integer.parseInt(itemIdStr);
		int amount = Integer.parseInt(amountStr);

		//セッションしていなかったらitemIdとamountをセッションで渡してログイン画面へ遷移
		if (userId == null) {
			path = "/LoginServlet";
			session.setAttribute("pendingItemId", itemId);
			session.setAttribute("pendingAmount", amount);
			request.setAttribute("path", "/addCart");
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);

			return;

		}

		CartAddService cartAdd = new CartAddService();

		try {
			cartAdd.addCartItem(userId, itemId, amount);

			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

}
