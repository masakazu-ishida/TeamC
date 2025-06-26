package jp.co.shiftw.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;

import jp.co.shiftw.dao.CartDAO;
import jp.co.shiftw.dto.CartDTO;
import jp.co.shiftw.util.CommonConstants;
import jp.co.shiftw.util.ConnectionUtil;

public class CartAddService {

	public static void CartAdd(String userId, int itemId, int amount, Date bookedDate) {

		ConnectionUtil.mode = ConnectionUtil.MODE.TEST;
		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {

			CartDAO dao = new CartDAO(conn);

			//追加するアイテムが既にカート内に存在するかをチェック
			CartDTO dto = dao.CartItem(userId, itemId);

			if (dto.getAmount() == 0) {
				dao.CartCerate(userId, itemId, amount, bookedDate);
			} else {
				int amountBefore = dto.getAmount();
				dao.cartAmountEdit(userId, itemId, amountBefore + amount); //現在カートに入れている商品数と今回入れた商品数を足す
			}

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
