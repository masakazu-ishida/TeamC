package jp.co.shiftw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.shiftw.dto.CategoriesDTO;
import jp.co.shiftw.dto.ItemsDTO;

public class ItemsDAO extends BaseDAO {

	public ItemsDAO(Connection conn) {
		super(conn);

	}

	//主キー検索を行うメソッド
	public List<ItemsDTO> findByCond(int categoryId, String name) throws SQLException {

		//キーワードとカテゴリを検索するSQL文
		String sql = "select name, color, manufacturer, price from items where name = ? and category_id = ? or category_id * ? = 0";

		ItemsDTO dto = null;

		List<ItemsDTO> list = new ArrayList<>();

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			CategoriesDTO cate = new CategoriesDTO();

			ps.setString(1, name);
			ps.setInt(2, categoryId);
			ps.setInt(3, categoryId);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				dto = new ItemsDTO();

				dto.setName(rs.getString("name"));
				dto.setCategory(cate);
			}

		}
		return list;

	}
}
