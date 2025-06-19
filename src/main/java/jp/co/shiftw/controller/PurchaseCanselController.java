package jp.co.shiftw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.shiftw.dto.PurchasesDTO;
import jp.co.shiftw.service.PurchaseCancelService;
import jp.co.shiftw.service.PurchasesHistoryService;

/**
 * Servlet implementation class PurchaseCanselController
 */
@WebServlet(name = "admin/purchaseCansel", urlPatterns = { "/admin/purchaseCansel" })
public class PurchaseCanselController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PurchaseCanselController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int purchaseId = Integer.parseInt(request.getParameter("purchase_id")); //前のページでセットしたpurchaseIdを取得

		PurchasesDTO purchase = PurchasesHistoryService.searchPurchasesByPurchaseId(purchaseId); //purchaseIdをキーとしてDTOを検索

		request.setAttribute("purchase", purchase); //ゲットしたDTOをJSPに表示させるためにセット

		request.getRequestDispatcher("/WEB-INF/admin/purchases_cancel_confirm.jsp").forward(request, response); //キャンセル確認画面に遷移
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int purchaseId = Integer.parseInt(request.getParameter("purchase_id")); //前のページでセットしたpurchaseIdを取得
		PurchasesDTO purchase = PurchasesHistoryService.searchPurchasesByPurchaseId(purchaseId); //purchaseIdをキーとしてDTOを検索

		request.setAttribute("purchase", purchase); //ゲットしたDTOをJSPに表示させるためにセット

		PurchaseCancelService.cancelPurchase(purchaseId); //指定された注文をキャンセルにする。

		request.getRequestDispatcher("/WEB-INF/admin/purchases_cancel.jsp").forward(request, response); //キャンセル完了画面に遷移
	}

}
