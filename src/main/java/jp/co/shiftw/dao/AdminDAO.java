package jp.co.shiftw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.shiftw.dto.AdminDTO;

public class AdminDAO extends BaseDAO {

	public AdminDAO(Connection conn) {
		super(conn);
		// TODO 自動生成されたコンストラクター・スタブ

	}

	public AdminDTO Login(int adminId, String password) throws SQLException {

		String sql = "SELECT * FROM administrators WHERE admin_id = ? AND password = ?";

		AdminDTO dto = null;

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, adminId);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				dto = new AdminDTO();

				dto.setAdminId(rs.getInt("adminId"));
				dto.setPassword(rs.getString("password"));

			}

		}

		return dto;
	}

}
