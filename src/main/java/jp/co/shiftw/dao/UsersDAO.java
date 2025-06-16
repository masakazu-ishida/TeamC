package jp.co.shiftw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.shiftw.dto.UsersDTO;

public class UsersDAO extends BaseDAO {

	public UsersDAO(Connection conn) {
		super(conn);
		// TODO 自動生成されたコンストラクター・スタブ

	}

	public UsersDTO Login(String user_id, String password) throws SQLException {

		String sql = "SELECT * FROM Users WHERE user_id = ? AND password = ? ";

		UsersDTO dto = null;

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, user_id);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				dto = new UsersDTO();

				dto.setUser_id(rs.getString("user_id"));
				dto.setPassword(rs.getString("password"));
				dto.setName(rs.getString("name"));
				dto.setAddress(rs.getString("address"));

			}
		}

		return dto;
	}

}
