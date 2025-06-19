package jp.co.shiftw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.shiftw.dto.CartDTO;
import jp.co.shiftw.service.CartListService;

/**
 * Servlet implementation class CartListController
 */
@WebServlet("/CartListController")
public class CartListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartListController() {
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

		String loginPath = "/WEB-INF/main/UsersLogin.jsp";
		String cartListPath = "/WEB-INF/main/cart_list.jsp";

		HttpSession session = request.getSession(false);

		if (session.getAttribute("userId") == null) {
			session.setAttribute("source", 1);

			RequestDispatcher rd = request.getRequestDispatcher(loginPath);
			rd.forward(request, response);
		} else {
			String userId = (String) session.getAttribute("userId");

			List<CartDTO> cartList = CartListService.cartList(userId);
			int totalAmount = CartListService.totalAmount(cartList);

			request.setAttribute("cartList", cartList);
			request.setAttribute("totalAmount", totalAmount);

			RequestDispatcher rd = request.getRequestDispatcher(cartListPath);
			rd.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

	}

}
