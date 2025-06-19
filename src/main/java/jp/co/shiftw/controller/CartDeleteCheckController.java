package jp.co.shiftw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.shiftw.dto.CartDTO;

/**
 * Servlet implementation class CartDeleteCheckController
 */
@WebServlet("/CartDeleteCheckController")
public class CartDeleteCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartDeleteCheckController() {
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

		int deleteId = Integer.parseInt(request.getParameter("purchase_id")); //前のページでセットしたpurchaseIdを取得

		CartDTO dto = CartDeleteCheckService. //purchaseIdをキーとしてDTOを検索

				request.setAttribute("purchase", purchase); //ゲットしたDTOをJSPに表示させるためにセット

		String path = "/CartListController";

		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		String path = "/CartListController";

		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);

	}
}
