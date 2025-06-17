package jp.co.shiftw.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import jp.co.shiftw.dao.PurchasesDAO;
import jp.co.shiftw.dto.PurchasesDTO;
import jp.co.shiftw.util.CommonConstants;
import jp.co.shiftw.util.ConnectionUtil;

public class PurchasesHistoryService {
	public static List<PurchasesDTO> searchPurchasesByUserId(String userId) {
		List<PurchasesDTO> list = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {
			PurchasesDAO dao = new PurchasesDAO(conn);
			list = dao.findByUserId(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}

		return list;
	}
}
