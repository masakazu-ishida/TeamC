package jp.co.sars.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;

import jp.co.sars.dao.PurchasesDAO;
import jp.co.sars.dto.PurchasesDTO;
import jp.co.sars.util.ConnectionUtil;

public class PurchaseHistoryService {
	public List<PurchasesDTO> execute(String userId) throws SQLException, ServletException {
		String lookupString = "java:comp/env/jdbc/ecsite";

		//接続の取得
		try (Connection conn = ConnectionUtil.getConnection(lookupString)) {
			PurchasesDAO dao = new PurchasesDAO(conn);
			List<PurchasesDTO> list = new ArrayList<PurchasesDTO>();

			//ユーザーIDで検索
			list = dao.findByUser(userId);

			return list;
		}
	}

}
