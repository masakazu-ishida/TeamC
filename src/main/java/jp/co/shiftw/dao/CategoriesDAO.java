package jp.co.shiftw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.shiftw.dto.CategoriesDTO;

public class CategoriesDAO extends BaseDAO {

	public CategoriesDAO(Connection conn) {
		super(conn);
		// TODO 自動生成されたコンストラクター・スタブ

	}

	public List<CategoriesDTO> findAll() throws SQLException {

		String sql = "SELECT * FROM Categories";

		List<CategoriesDTO> list = new ArrayList<>();

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				CategoriesDTO dto = new CategoriesDTO();

				dto.setCategoryId(rs.getInt("category_id"));
				dto.setName(rs.getString("name"));

				list.add(dto);

			}
		}

		return list;
	}
}
