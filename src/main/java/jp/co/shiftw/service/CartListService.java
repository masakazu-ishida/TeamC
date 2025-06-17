package jp.co.shiftw.service;

import java.sql.Connection;
import java.util.List;

import jp.co.shiftw.dao.CartDAO;
import jp.co.shiftw.dto.CartDTO;
import jp.co.shiftw.util.ConnectionUtil;

public class CartListService {

	public static List<CartDTO> CartList(String userId) {

		List<CartDTO> cartList = null;

		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			CartDAO dao = new CartDAO(conn);
			cartList = dao.CartList(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartList;
	}
}
