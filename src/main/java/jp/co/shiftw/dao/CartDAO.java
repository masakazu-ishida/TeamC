package jp.co.shiftw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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

	public void CartCerate(String userId, int itemId, int amount, Date bookedDate) throws SQLException {

		//カートに追加するレコードを取得するSQL
		String sql = "INSERT INTO items_in_cart(user_id,item_id,amount,booked_date) VALUES(?,?,?,?)";

		//CartDTO dto =new CartDTO();

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, userId);
			ps.setInt(2, itemId);
			ps.setInt(3, amount);
			ps.setDate(4, new java.sql.Date(bookedDate.getTime()));

			ps.executeUpdate();

		}

	}

	public CartDTO CartItem(String userId, int itemId) throws SQLException {

		CartDTO cartItem = new CartDTO();

		//カートのアイテムを取得するSQL
		String sql = "SELECT items.name, items.color, items.manufacturer, items.price, items_in_cart.amount\n"
				+ "From items_in_cart \n"
				+ "INNER JOIN items \n"
				+ "ON items_in_cart.item_id = items.item_id \n"
				+ "WHERE user_id = ? AND items_in_cart.item_id = ?";
		//CartDTO dto =new CartDTO();

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, userId);
			ps.setInt(2, itemId);

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

			}

		}
		return cartItem;
	}

}
