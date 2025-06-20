package jp.co.shiftw.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;

import jp.co.shiftw.dao.ItemsDAO;
import jp.co.shiftw.dao.PurchaseDetailsDAO;
import jp.co.shiftw.dao.PurchasesDAO;
import jp.co.shiftw.dto.CartDTO;
import jp.co.shiftw.dto.ItemsDTO;
import jp.co.shiftw.util.CommonConstants;
import jp.co.shiftw.util.ConnectionUtil;

public class PurchaseService {
	public static boolean purchase(String userId, List<CartDTO> cartList, String destination) {
		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {
			conn.setAutoCommit(false);

			try {
				// 注文テーブルに注文を追加
				PurchasesDAO dao = new PurchasesDAO(conn);
				int purchaseId = dao.create(userId, destination);

				// カートの中身を注文詳細に変換して注文詳細テーブルに追加
				for (CartDTO cart : cartList) {
					ItemsDTO item = cart.getItems();
					int itemId = item.getItemId();
					int amount = cart.getAmount();

					//商品の注文個数分商品の在庫数を減らそうとする
					ItemsDAO itemsDAO = new ItemsDAO(conn);
					boolean isChanged = itemsDAO.changeStock(itemId, -amount);

					if (!isChanged) {
						conn.rollback();
						return false;
					}

					//注文を追加する
					PurchaseDetailsDAO purchaseDetailsDAO = new PurchaseDetailsDAO(conn);
					purchaseDetailsDAO.create(purchaseId, itemId, amount);

					//カートを削除する
					CartDeleteService.cartDelete(userId, itemId);
				}
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

		return true;
	}
}
