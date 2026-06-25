package jp.co.sars.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.sars.dao.ItemsInCartDAO;
import jp.co.sars.dto.ItemsInCartDTO;
import jp.co.sars.dto.PurchaseDetailsDTO;
import jp.co.sars.dto.PurchasesDTO;
import jp.co.sars.service.PurchaseCommitService;
import jp.co.sars.util.ConnectionUtil;

/**
 * Servlet implementation class PurchaseCommit
 */
@WebServlet("/PurchaseCommit")
public class PurchaseCommitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PurchaseCommitServlet() {
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
		String path = "/WEB-INF/purchaseCommit.jsp";

		HttpSession session = request.getSession(true);
		String userId = (String) session.getAttribute("userId");
		if (userId == null) {
			path = "/WEB-INF/error.jsp";//エラー画面に遷移
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);

			return;

		}

		long currentTime = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(currentTime);
		String destination = request.getParameter("destination");
		String par = request.getParameter("kounyuu");

		if (par.equals("1")) {
			par = "代金引換";
		}

		PurchaseCommitService pcs = new PurchaseCommitService();
		try {

			Map<String, Object> map = pcs.execute(userId, destination, date);

			List<PurchasesDTO> purchaseList = (List<PurchasesDTO>) map.get("cartList");
			List<PurchaseDetailsDTO> purchase = null;

			if (purchaseList != null && !purchaseList.isEmpty()) {
				purchase = purchaseList.get(0).getList();
			}

			int userPrice = (int) map.get("userPrice");

			request.setAttribute("purchase", purchase);
			request.setAttribute("userPrice", userPrice);
			request.setAttribute("userId", userId);
			request.setAttribute("par", par);
			request.setAttribute("destination", purchaseList.get(0).getDestination());

			request.getRequestDispatcher(path).forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", "購入処理に失敗しました");
			try (Connection conn = ConnectionUtil.getConnection("java:comp/env/jdbc/ecsite")) {
				ItemsInCartDAO iicDAO = new ItemsInCartDAO(conn);

				List<ItemsInCartDTO> cartList = iicDAO.findByUser(userId);

				request.setAttribute("cart", cartList);

			} catch (Exception i) {
				i.printStackTrace();
			}

			String cartPath = "/WEB-INF/cart.jsp";
			request.getRequestDispatcher(cartPath).forward(request, response);
		}
	}
}
