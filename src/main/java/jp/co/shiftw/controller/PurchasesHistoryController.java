package jp.co.shiftw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.shiftw.dto.PurchasesDTO;
import jp.co.shiftw.service.PurchasesHistoryService;

/**
 * Servlet implementation class PurchasesHistoryController
 */
@WebServlet(name = "purchasesHistory", urlPatterns = { "/admin/purchasesHistory" })
public class PurchasesHistoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PurchasesHistoryController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("user_id");

		if (userId == "") {
			System.out.println("aaa");
			userId = null;
		}

		System.out.println(userId);

		List<PurchasesDTO> dtos = PurchasesHistoryService.searchPurchasesByUserId(userId);

		request.setAttribute("purchases", dtos);

		request.getRequestDispatcher("../WEB-INF/admin/purchases_history.jsp").forward(request, response);

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
