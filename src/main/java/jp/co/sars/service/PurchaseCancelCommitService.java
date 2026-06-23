package jp.co.sars.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;

import jp.co.sars.dao.ItemsDAO;
import jp.co.sars.dao.PurchasesDAO;
import jp.co.sars.dto.ItemsDTO;
import jp.co.sars.dto.PurchaseDetailsDTO;
import jp.co.sars.dto.PurchasesDTO;
import jp.co.sars.util.ConnectionUtil;

public class PurchaseCancelCommitService {
	public PurchasesDTO execute(int purchaseId) throws SQLException, ServletException {
		String lookupString = "java:comp/env/jdbc/ecsite";

		//接続の取得
		try (Connection conn = ConnectionUtil.getConnection(lookupString)) {
			PurchasesDAO dao = new PurchasesDAO(conn);
			List<PurchasesDTO> list = new ArrayList<PurchasesDTO>();

			//注文IDで検索
			list = dao.findByPurchaseId(purchaseId);

			PurchasesDTO purchase = new PurchasesDTO();

			//listの中身がnullでなければ、purchaseに入れる//
			if (list != null && !list.isEmpty()) {
				purchase = list.get(0);

				//キャンセル済だったらNullを返す
				if (purchase.isCancel()) {
					return null;
				}

				//トランザクション開始
				conn.setAutoCommit(false);
				try {
					//キャンセルをtrueにする
					dao.cancelUpdate(purchaseId);

					ItemsDAO itemDao = new ItemsDAO(conn);
					//キャンセルした商品分ループを回す
					for (PurchaseDetailsDTO detail : purchase.getList()) {

						// itemsDAOに入れるための引数のitemsDTOを用意して、値をDTOに入れる
						ItemsDTO sendItem = new ItemsDTO();
						sendItem.setItemId(detail.getItems().getItemId()); // 商品IDをセット
						sendItem.setAmount(detail.getAmount()); // 買った数量をセット

						// 1件分の在庫を増やすUPDATEを実行
						itemDao.updateSum(sendItem);
					}
					//コミット
					conn.commit();

					return purchase;

				} catch (Exception e) {
					conn.rollback();
					e.printStackTrace();
					return null;
				}
			}
			return null;
		}
	}

}
