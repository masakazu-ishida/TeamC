package jp.co.sars.service;

import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.ServletException;

import jp.co.sars.dao.UserDAO;
import jp.co.sars.dto.UserDTO;
import jp.co.sars.util.ConnectionUtil;

public class LoginService {
	public UserDTO Login(String userId, String password) throws SQLException, ServletException {
		String lookupString = "java:comp/env/jdbc/ecsite";

		//接続の取得
		try (Connection conn = ConnectionUtil.getConnection(lookupString)) {
			UserDAO dao = new UserDAO(conn);
			UserDTO dto = dao.findById(userId);
			//主キー検索でnullが返ってきた場合
			if (dto == null) {
				return dto;

			}
			//主キーで取ってきたパスワードとユーザが入力したパスワードがあっているか判定
			else if (dto.getPassword().equals(password)) {
				return dto;
			}
			//パスワードが間違っている場合
			else {
				dto = null;
				return dto;
			}
		}
	}
}
