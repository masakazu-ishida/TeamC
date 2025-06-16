package jp.co.shiftw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jp.co.shiftw.dao.BaseDAO;
import jp.co.shiftw.dto.CartDTO;
import jp.co.shiftw.dto.ItemsDTO;
import jp.co.shiftw.util.ConnectionUtil;

class CartDAOTest {

	@BeforeEach
	void init() {
		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			BaseDAO dao = new BaseDAO(conn);

			try {
				dao.insertBatch("sqlFiles/init.sql");
			} catch (Exception e) {
				throw e;
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void CartListTest() {

		String sql = "SELECT items.name, items.color, items.manufacturer, items.price, items_in_cart.amount\n"
				+ "From items_in_cart \n"
				+ "INNER JOIN items \n"
				+ "ON items_in_cart.item_id = items.item_id \n"
				+ "WHERE user_id = ? ";

		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			try (PreparedStatement ps = conn.prepareStatement(sql)) {

				ps.setString(1, "user");

				System.out.println("接続完了");

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					CartDTO cart = new CartDTO();
					ItemsDTO item = new ItemsDTO();

					item.setName(rs.getString("name"));
					item.setManufacturer(rs.getString("manufacturer"));
					item.setColor(rs.getString("color"));
					item.setPrice(rs.getInt("price"));

					cart.setItems(item);
					cart.setAmount(rs.getInt("amount"));

					System.out.print(cart.getItems());
					System.out.println("," + cart.getAmount());

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}