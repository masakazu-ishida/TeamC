package jp.co.shiftw.service;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.shiftw.dao.UsersDAO;
import jp.co.shiftw.dto.UsersDTO;

public class UsersService {

	public static UsersDTO loginUsers(Connection conn, String user_id, String password) throws SQLException {
		UsersDAO dao = new UsersDAO(conn);

		return dao.findById(user_id);

	}

}
