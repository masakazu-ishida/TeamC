package jp.co.sars.servlet;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jp.co.sars.dto.PurchasesDTO;
import jp.co.sars.service.PurchaseCancelCommitService;

/**
 * Servlet implementation class PurchaseCancelCommitServlet
 */
@WebServlet("/PurchaseCancelCommit")
public class PurchaseCancelCommitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PurchaseCancelCommitServlet() {
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
		String path = "/WEB-INF/purchaseCancelCommit.jsp";

		HttpSession session = request.getSession(false);

		//セッションがない場合不正アクセス
		if (session == null) {
			path = "/WEB-INF/error.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);

			return;

		}

		//購入履歴一覧表示画面からの注文IDをパラメータで受け取る
		int purchaseId = Integer.parseInt(request.getParameter("purchaseId"));

		//Serviceの追加
		PurchaseCancelCommitService service = new PurchaseCancelCommitService();
		try {
			PurchasesDTO purchase = service.execute(purchaseId);

			request.setAttribute("purchase", purchase);

			RequestDispatcher rd = request.getRequestDispatcher(path);

			rd.forward(request, response);

		} catch (SQLException | ServletException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
