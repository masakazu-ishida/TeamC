package jp.co.shiftw.service;

import java.sql.Connection;

import jp.co.shiftw.dao.CartDAO;
import jp.co.shiftw.util.CommonConstants;
import jp.co.shiftw.util.ConnectionUtil;

public class CartDeleteService {
	public static void cartDelete(String userId, int itemId) {

		ConnectionUtil.mode = ConnectionUtil.MODE.TEST;
		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {

			CartDAO cartDao = new CartDAO(conn);
			cartDao.cartDelete(userId, itemId);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
