package jp.co.shiftw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.shiftw.dto.CartDTO;
import jp.co.shiftw.dto.ItemsDTO;
import jp.co.shiftw.util.ConnectionUtil;

public class CartDAO extends BaseDAO {

	public CartDAO(Connection conn) {
		super(conn);
	}

	CartDTO CartList(String userId) {

		String sql = "SELECT items.name, items.color, items.manufacturer, items.price, items_in_cart.amount\n"
				+ "From items_in_cart \n"
				+ "INNER JOIN items \n"
				+ "ON items_in_cart.item_id = items.item_id \n"
				+ "WHERE user_id = ? ";

		CartDTO cart = null;

		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			try (PreparedStatement ps = conn.prepareStatement(sql)) {

				ps.setString(1, userId);

				System.out.println("接続完了");

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					ItemsDTO items = new ItemsDTO();
					List<ItemsDTO> item = new ArrayList<>();

					items.setName(rs.getString("name"));
					items.setManufacturer(rs.getString("manufacturer"));
					items.setColor(rs.getString("color"));
					items.setPrice(rs.getInt("price"));

					item.add(items);

					cart.setItems(item);
					cart.setAmount(rs.getInt("amount"));

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return cart;
	}
}
