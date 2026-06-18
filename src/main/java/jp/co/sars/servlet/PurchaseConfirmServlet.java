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
import jp.co.sars.service.PurchaseConfirmService;

/**
 * Servlet implementation class PurchaseConfirmServlet
 */
@WebServlet("/purchaseConfirm")
public class PurchaseConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PurchaseConfirmServlet() {
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
		String path = "/WEB-INF/purchaseConfirm.jsp";

		HttpSession session = request.getSession(true);
		String userId = (String) session.getAttribute("userId");
		if (userId == null) {
			path = "/Login2";//ログイン画面へ遷移
			request.setAttribute("path", "/TeamC/purchaseConfirm");//urlをログインに渡す
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);

			return;

		}

		PurchaseConfirmService pcs = new PurchaseConfirmService();
		Map<String, Object> cart = pcs.execute(userId);

		List<ItemsInCartDTO> cartList = (List<ItemsInCartDTO>) cart.get("cartList");
		int userPrice = (int) cart.get("userPrice");

		request.setAttribute("cart", cartList);
		request.setAttribute("userPrice", userPrice);
		request.setAttribute("userId", userId);

		request.getRequestDispatcher(path).forward(request, response);

	}

}
