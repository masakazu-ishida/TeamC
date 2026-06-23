package jp.co.sars.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;

import jp.co.sars.dao.PurchasesDAO;
import jp.co.sars.dto.PurchasesDTO;
import jp.co.sars.util.ConnectionUtil;

public class PurchaseCancelConfirmService {
	public PurchasesDTO execute(int purchaseId) throws SQLException, ServletException {
		String lookupString = "java:comp/env/jdbc/ecsite";

		//接続の取得
		try (Connection conn = ConnectionUtil.getConnection(lookupString)) {
			PurchasesDAO dao = new PurchasesDAO(conn);
			List<PurchasesDTO> list = new ArrayList<PurchasesDTO>();

			//注文IDで検索
			list = dao.findByPurchaseId(purchaseId);

			//listの中身がnullでなければ、purchaseに入れる//
			if (list != null && !list.isEmpty()) {

				//データを取り出す
				PurchasesDTO purchase = list.get(0);

				//すでにキャンセル(true)されていたら、nullを返す
				if (purchase.isCancel() == true) {
					return null;
				}

				//キャンセルがfalseなら、そのままデータを返す
				return purchase;
			}

			return null;
		}
	}
}
