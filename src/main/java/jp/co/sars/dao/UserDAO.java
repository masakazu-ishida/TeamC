package jp.co.sars.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sars.dto.UserDTO;

public class UserDAO {
	private Connection con;

	public UserDAO(Connection con) {
		this.con = con;
	}

	// データの挿入 (Create)
	public int insert(UserDTO user) throws SQLException {
		String sql = "INSERT INTO public.users (user_id, password, name, address) VALUES (?, ?, ?, ?)";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, user.getUserId());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getAddress());
			return ps.executeUpdate();
		}
	}

	// 主キーによる検索 
	public UserDTO findById(String userId) throws SQLException {
		String sql = "SELECT user_id, password, name, address FROM public.users WHERE user_id = ?";
		UserDTO user = null;
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, userId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {

					//mapRowはResultSetからDTOへの変換メソッド。複数箇所で利用するので共通化
					user = mapRow(rs);
					return user;
				}
			}
		}
		return null; // 見つからない場合はnullを返す
	}

	// 全件検索 
	public List<UserDTO> findAll() throws SQLException {
		String sql = "SELECT user_id, password, name, address FROM public.users";
		List<UserDTO> list = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				//mapRowはResultSetからDTOへの変換メソッド。複数箇所で利用するので共通化
				UserDTO user = mapRow(rs);
				list.add(user);
			}
		}
		return list;
	}

	// データの更新 
	public int update(UserDTO user) throws SQLException {
		String sql = "UPDATE public.users SET password = ?, name = ?, address = ? WHERE user_id = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, user.getPassword());
			ps.setString(2, user.getName());
			ps.setString(3, user.getAddress());
			ps.setString(4, user.getUserId());
			return ps.executeUpdate();
		}
	}

	// データの削除
	public int delete(String userId) throws SQLException {
		String sql = "DELETE FROM public.users WHERE user_id = ?";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, userId);
			return ps.executeUpdate();
		}
	}

	// ResultSetからエンティティへのマッピングを行う共通メソッド
	//主キー検索と全件検索で重複を防ぐため
	private UserDTO mapRow(ResultSet rs) throws SQLException {
		UserDTO user = new UserDTO();
		user.setUserId(rs.getString("user_id"));
		user.setPassword(rs.getString("password"));
		user.setName(rs.getString("name"));
		user.setAddress(rs.getString("address"));
		return user;
	}
}
