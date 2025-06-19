package jp.co.shiftw.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;

import jp.co.shiftw.dao.ItemsDAO;
import jp.co.shiftw.dao.PurchasesDAO;
import jp.co.shiftw.dto.CartDTO;
import jp.co.shiftw.dto.ItemsDTO;
import jp.co.shiftw.dto.PurchaseDetailsDTO;
import jp.co.shiftw.util.CommonConstants;
import jp.co.shiftw.util.ConnectionUtil;

public class PurchaseAddSearvice {
	public static void purchaseAdd(String userId, List<CartDTO> cartList, String destination) {
		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {
			conn.setAutoCommit(false);

			try {
				// 注文テーブルに注文を追加
				PurchasesDAO dao = new PurchasesDAO(conn);
				dao.create(userId, destination);

				// カートの中身を注文詳細に変換して注文詳細テーブルに追加
				for (CartDTO cart : cartList) {
					ItemsDTO item = cart.getItems();
					int itemId = item.getItemId();

				}

				List<PurchaseDetailsDTO> purchaseDetails = purchase.getPurchaseDetails();

				// 注文詳細にあるアイテムの注文数をもとに商品の在庫数をもとに戻す
				for (PurchaseDetailsDTO detail : purchaseDetails) {
					ItemsDAO itemsDAO = new ItemsDAO(conn);

					int itemId = detail.getItem().getItemId();
					int amount = detail.getAmount();

					// itemの在庫数を注文数分増やす
					itemsDAO.changeStock(itemId, amount);
				}

				dao.cancel(purchaseId);
				conn.commit();
			} catch (SQLException e) {
				conn.rollback();
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
}
