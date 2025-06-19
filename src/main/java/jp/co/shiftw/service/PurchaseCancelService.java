package jp.co.shiftw.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;

import jp.co.shiftw.dao.PurchasesDAO;
import jp.co.shiftw.util.CommonConstants;
import jp.co.shiftw.util.ConnectionUtil;

public class PurchaseCancelService {
	public static void cancelPurchase(int purchaseId) {
		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {
			PurchasesDAO dao = new PurchasesDAO(conn);
			dao.cancel(purchaseId);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
}
