package jp.co.shiftw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.shiftw.dto.CategoriesDTO;
import jp.co.shiftw.dto.ItemsDTO;

public class ItemsDAO extends BaseDAO {

	public ItemsDAO(Connection conn) {
		super(conn);

	}

	public void findByCond(int categoryId, String name) throws SQLException {

		String sql = "select name, color, manufacturer, price from items where name = ? and category_id = ?";

		ItemsDTO dto = null;

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			CategoriesDTO cate = new CategoriesDTO();

			ps.setString(1, name);
			ps.setInt(2, categoryId);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				dto = new ItemsDTO();

				dto.setName(rs.getString("name"));
				//dto.setCategory(rs.getInt("cate.category"));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
