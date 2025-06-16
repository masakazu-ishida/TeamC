package jp.co.shiftw.service;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.shiftw.dao.AdminDAO;
import jp.co.shiftw.dto.AdminDTO;

public class AdminService {

	public static AdminDTO loginAdmin(Connection conn, String adminId, String password) throws SQLException {
		AdminDAO dao = new AdminDAO(conn);

		return dao.Login(adminId, password);
	}

}
