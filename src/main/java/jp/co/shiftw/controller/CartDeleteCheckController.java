package jp.co.shiftw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.shiftw.dto.CartDTO;
import jp.co.shiftw.service.CartDeleteCheckService;

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
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		String path = "/WEB-INF/cart_delete.jsp";

		HttpSession session = request.getSession();

		String userId = (String) session.getAttribute("userId");
		int itemId = (int) request.getAttribute("itemId");

		CartDTO cartItem = CartDeleteCheckService.cartDeleteCheckService(userId, itemId);

		request.setAttribute("cartItem", cartItem);

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
