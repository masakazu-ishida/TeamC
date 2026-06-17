package jp.co.sars.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;

import jp.co.sars.dao.ItemsInCartDAO;
import jp.co.sars.dto.ItemsInCartDTO;
import jp.co.sars.util.ConnectionUtil;

public class CartService {

	public Map<String, Object> execute(String userId) throws ServletException {
		String jndiName = "java:comp/env/jdbc/ecsite";

		List<ItemsInCartDTO> cartList = new ArrayList<>();
		int userPrice = 0;

		try (Connection conn = ConnectionUtil.getConnection(jndiName)) {
			ItemsInCartDAO cartDAO = new ItemsInCartDAO(conn);

			cartList = cartDAO.findByUser(userId);

			for (ItemsInCartDTO cart : cartList) {
				int price = cart.getItems().getPrice();
				int amount = cart.getAmount();
				userPrice += (price * amount);

			}

		} catch (Exception e) {

		}
		Map<String, Object> result = new HashMap<>();
		result.put("cartList", cartList);
		result.put("userPrice", userPrice);

		return result;

	}
}