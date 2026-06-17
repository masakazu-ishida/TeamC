
package jp.co.sars.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.sars.dto.ItemsInCartDTO;
import jp.co.sars.service.CartService;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartServlet() {
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
		String path = "/WEB-INF/cart.jsp";

		//セッション情報の取得
		HttpSession session = request.getSession();

		//取得した情報をString型に変換
		String userId = (String) session.getAttribute("userId");

		//セッションしていなかったらurlを渡してログイン画面へ遷移
		if (userId == null) {
			path = "";
			request.setAttribute("path", "/TeamC/cart");
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);

			return;

		}

		//サービスのインスタンス化
		CartService cart = new CartService();

		//ハッシュマップに格納された合計金額とリストを受け取る
		Map<String, Object> cartMap = cart.execute(userId);

		List<ItemsInCartDTO> cartList = (List<ItemsInCartDTO>) cartMap.get("cartList");
		int userPrice = (int) cartMap.get("userPrice");

		request.setAttribute("cartList", cartList);
		request.setAttribute("userPrice", userPrice);

		request.getRequestDispatcher(path).forward(request, response);

	}
}
