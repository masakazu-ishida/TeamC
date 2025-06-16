package jp.co.shiftw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
					ItemsDTO items = new ItemsDTO();
					List<ItemsDTO> item = new ArrayList<>();

					items.setName(rs.getString("name"));
					items.setManufacturer(rs.getString("manufacturer"));
					items.setColor(rs.getString("color"));
					items.setPrice(rs.getInt("price"));

					item.add(items);

					cart.setItems(item);
					cart.setAmount(rs.getInt("amount"));

					for (ItemsDTO it : cart.getItems()) {
						System.out.print(it.getName() + "," +
								it.getColor() + "," +
								it.getManufacturer() + "," +
								it.getPrice());
					}

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