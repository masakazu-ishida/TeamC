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

public class PurchaseConfirmService {

	public Map<String, Object> execute(String userId) throws ServletException {

		String jndiName = "java:comp/env/jdbc/ecsite";
		List<ItemsInCartDTO> list = new ArrayList<>();
		int userPrice = 0;

		try (Connection conn = ConnectionUtil.getConnection(jndiName)) {
			ItemsInCartDAO dao = new ItemsInCartDAO(conn);

			list = dao.findByUser(userId);

			for (ItemsInCartDTO cart : list) {
				int price = cart.getItems().getPrice();
				int amount = cart.getAmount();
				userPrice += (price * amount);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		///二つ戻り値を返す
		/// https://qiita.com/niwasawa/items/f9ed6552e18b2fa6bcbe
		Map<String, Object> result = new HashMap<>();
		result.put("cartList", list);
		result.put("userPrice", userPrice);

		return result;

	}

}
