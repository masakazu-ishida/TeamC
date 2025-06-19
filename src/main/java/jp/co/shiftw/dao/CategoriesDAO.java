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

		String sql = "SELECT category_id,name FROM Categories";

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

	//category_idを主キーにレコードを取得
	public CategoriesDTO findById(int category_id) {

		String sql = "SELECT category_id,name FROM categories WHERE category_id = ?";

		//dtoの初期化
		CategoriesDTO dto = null;

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, categoryId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				dto = new CategoriesDTO();

				dto.setCategoryId(rs.getInt("category_id"));
				dto.setName(rs.getString("name"));
			}

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return dto;

	}
}
