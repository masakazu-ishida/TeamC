package jp.co.shiftw.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import jp.co.shiftw.dao.CartDAO;
import jp.co.shiftw.dto.CartDTO;
import jp.co.shiftw.dto.ItemsDTO;
import jp.co.shiftw.util.CommonConstants;
import jp.co.shiftw.util.ConnectionUtil;

public class CartListService {

	public static List<CartDTO> cartList(String userId) {

		List<CartDTO> cartList = new ArrayList<>();

		ConnectionUtil.mode = ConnectionUtil.MODE.TEST;
		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {
			//try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			CartDAO dao = new CartDAO(conn);
			cartList = dao.cartList(userId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartList;
	}

	public static int totalAmount(List<CartDTO> cartList) {

		int amount = 0;
		int totalAmount = 0;

		for (CartDTO items : cartList) {
			ItemsDTO item = items.getItems();

			amount = item.getPrice() * items.getAmount();
			totalAmount = totalAmount + amount;

		}
		return totalAmount;
	}
}
