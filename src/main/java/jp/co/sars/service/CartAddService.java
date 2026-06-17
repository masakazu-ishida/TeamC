package jp.co.sars.service;

import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.ServletException;

import jp.co.sars.dao.ItemsInCartDAO;
import jp.co.sars.dto.ItemsInCartDTO;
import jp.co.sars.util.ConnectionUtil;

public class CartAddService {

	public int addCartItem(String userId, int itemId, int amount) throws SQLException, ServletException {
		String jndiName = "java:comp/env/jdbc/ecsite";
		int resultCount = 0;

		try (Connection conn = ConnectionUtil.getConnection(jndiName)) {
			ItemsInCartDAO cartDAO = new ItemsInCartDAO(conn);

			//itemsInCartテーブル内にすでに同じ商品が入っている確認を行う
			ItemsInCartDTO findItem = cartDAO.findById(userId, itemId);

			//もしitemsInCartテーブル内にすでに同じ商品の注文が入っていた場合
			if (findItem != null) {
				int newAmount = findItem.getAmount() + amount;//合計計算

				ItemsInCartDTO updateCart = new ItemsInCartDTO();
				updateCart.setUserId(userId);
				updateCart.setItemId(itemId);
				updateCart.setAmount(newAmount); //合計した数量をセットする

				resultCount = cartDAO.update(updateCart);//成功したら1がかえってくるように
			} else {
				//存在しなかった場合はinsertを行う
				ItemsInCartDTO cartDTO = new ItemsInCartDTO();
				cartDTO.setUserId(userId);
				cartDTO.setItemId(itemId);
				cartDTO.setAmount(amount);
				//現在の日付をセットする
				cartDTO.setBookedDate(new java.sql.Date(System.currentTimeMillis()));

				cartDAO.insert(cartDTO);

				resultCount = cartDAO.update(cartDTO);//成功したら1がかえってくるように
			}
		}
		return resultCount;
	}
}
