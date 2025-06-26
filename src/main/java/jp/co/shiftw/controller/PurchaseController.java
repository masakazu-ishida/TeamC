package jp.co.shiftw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.shiftw.dto.CartDTO;
import jp.co.shiftw.service.CartListService;
import jp.co.shiftw.service.PurchaseService;
import jp.co.shiftw.service.UserSearchService;

/**
 * Servlet implementation class PurchaseController
 */
@WebServlet(name = "purchase", urlPatterns = { "/purchase" })
public class PurchaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PurchaseController() {
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
		String path = "/WEB-INF/main/purchase_complete.jsp";

		//セッションに登録されているユーザーIDを取得
		HttpSession session = request.getSession(false);
		String userId = (String) session.getAttribute("userId");

		//カートの中身を取得
		List<CartDTO> cartList = CartListService.cartList(userId);

		//配送先を取得する
		String destination = request.getParameter("destination");
		if (destination.equals("registered")) {
			destination = UserSearchService.searchUserByUserId(userId).getAddress();
		} else {
			destination = request.getParameter("address");
		}
		int totalAmount = CartListService.totalAmount(cartList);

		//購入処理
		boolean ifPurchase = PurchaseService.purchase(userId, cartList, destination);

		if (ifPurchase) {
			request.setAttribute("message", "以下の商品を購入しました。");
		} else {
			request.setAttribute("message", "注文に失敗しました: 購入した商品の注文数が在庫数を上回っています。");
		}

		//画面出力項目の設定
		request.setAttribute("destination", destination);
		request.setAttribute("totalAmount", totalAmount);
		request.setAttribute("cartList", cartList);

		request.getRequestDispatcher(path).forward(request, response);

	}

}
