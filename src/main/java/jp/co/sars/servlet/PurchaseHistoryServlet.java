package jp.co.sars.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.sars.dto.PurchasesDTO;
import jp.co.sars.service.PurchaseHistoryService;

/**
 * Servlet implementation class PurchaseHistoryServlet
 */
@WebServlet("/PurchaseHistory")
public class PurchaseHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PurchaseHistoryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = "/WEB-INF/purchaseHistory.jsp";

		HttpSession session = request.getSession(false);

		//セッションがない場合不正アクセス
		if (session == null) {
			path = "/WEB-INF/error.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);

			return;

		}

		String userId = (String) session.getAttribute("userId");

		PurchaseHistoryService phs = new PurchaseHistoryService();
		try {
			List<PurchasesDTO> list = phs.execute(userId);
			request.setAttribute("list", list);

			RequestDispatcher rd = request.getRequestDispatcher(path);

			rd.forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
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
