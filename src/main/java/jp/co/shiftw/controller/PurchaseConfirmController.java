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

/**
 * Servlet implementation class PurchaseConfirmController
 */
@WebServlet(name = "purchaseConfirm", urlPatterns = { "/purchaseConfirm" })
public class PurchaseConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PurchaseConfirmController() {
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
		String path = "/WEB-INF/main/purchase_confirm.jsp";

		//セッションに登録されているユーザーIDを取得
		HttpSession session = request.getSession(false);
		String userId = (String) session.getAttribute("userId");

		//カートの中身を取得
		List<CartDTO> cartList = CartListService.cartList(userId);

		//画面出力項目の設定
		request.setAttribute("cartList", cartList);

		request.getRequestDispatcher(path).forward(request, response);
	}

}
