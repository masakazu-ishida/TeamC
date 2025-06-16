package jp.co.shiftw.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jp.co.shiftw.dao.PurchasesDAO;
import jp.co.shiftw.dto.PurchasesDTO;

public class PurchasesHistoryService {
	public static List<PurchasesDTO> searchPurchasesByUserId(Connection conn, String userId) throws SQLException {
		PurchasesDAO dao = new PurchasesDAO(conn);

		return dao.findByUserId(userId);
	}
}
