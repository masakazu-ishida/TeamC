package jp.co.shiftw.service;

import java.sql.Connection;

import jp.co.shiftw.dao.CartDAO;
import jp.co.shiftw.dto.CartDTO;
import jp.co.shiftw.util.CommonConstants;
import jp.co.shiftw.util.ConnectionUtil;

public class CartDeleteService {
	public static CartDTO cartDelete(String userId, int itemId) {

		CartDTO cartItem = new CartDTO();

		ConnectionUtil.mode = ConnectionUtil.MODE.TEST;
		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {

			CartDAO cartDao = new CartDAO(conn);
			cartItem = cartDao.CartItem(userId, itemId);
			cartDao.cartDelete(userId, itemId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartItem;

	}

}
