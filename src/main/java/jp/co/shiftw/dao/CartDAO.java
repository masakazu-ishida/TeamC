package jp.co.shiftw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jp.co.shiftw.dto.CartDTO;

public class CartDAO extends BaseDAO {

	public CartDAO(Connection conn) {
		super(conn);
	}

	public List<CartDTO> CartList(String user_id) {

		String sql = "SELECT items.name, items.color, items.manufacturer, items.price, items_in_cart.amount\n"
				+ "From items_in_cart"
				+ "INNER JOIN items"
				+ "ON items_in_cart.item_id = items.item_id"
				+ "WHERE user_id = ?";

		List<CartDTO> cartList = new ArrayList<>();

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, user_id);

			System.out.println("接続完了");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				//				CartDTO cart = new CartDTO();
				//				ItemsDTO item = new ItemsDTO();

				//				item.setName(rs.getString("name"));
				//				item.setColor(rs.getString("color"));
				//				item.setManufacturer(rs.getString("manufacturer"));
				//				item.setPrice(rs.getInt("price"));
				//				cart.setAmount(rs.getInt("amount"));
				//				
				//				cart.setItems(item);

				System.out.println(rs.getString("name"));
				System.out.println(rs.getString("color"));
				System.out.println(rs.getString("manufacturer"));
				System.out.println(rs.getInt("price"));
				System.out.println(rs.getInt("amount"));

				//				cart.setItems(item);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return cartList;
	}

}
