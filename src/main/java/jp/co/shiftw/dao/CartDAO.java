package jp.co.shiftw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.shiftw.dto.CartDTO;
import jp.co.shiftw.dto.ItemsDTO;

public class CartDAO extends BaseDAO {

	public CartDAO(Connection conn) {
		super(conn);
	}

	public List<CartDTO> CartList(String userId) throws SQLException {

		List<CartDTO> cartList = new ArrayList<>();
		String sql = "SELECT items.name, items.color, items.manufacturer, items.price, items_in_cart.amount\n"
				+ "From items_in_cart \n"
				+ "INNER JOIN items \n"
				+ "ON items_in_cart.item_id = items.item_id \n"
				+ "WHERE user_id = ? ";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, userId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ItemsDTO item = new ItemsDTO();

				item.setName(rs.getString("name"));
				item.setColor(rs.getString("color"));
				item.setManufacturer(rs.getString("manufacturer"));
				item.setPrice(rs.getInt("price"));

				CartDTO cart = new CartDTO();

				cart.setItems(item);
				cart.setAmount(rs.getInt("amount"));

				cartList.add(cart);
			}

		}
		return cartList;
	}

}
