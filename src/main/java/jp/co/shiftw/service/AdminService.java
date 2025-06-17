package jp.co.shiftw.service;

import java.sql.Connection;

import jp.co.shiftw.dao.AdminDAO;
import jp.co.shiftw.dto.AdminDTO;
import jp.co.shiftw.util.ConnectionUtil;

public class AdminService {

	public static AdminDTO loginAdmin(String adminId) {

		AdminDTO adminDTO = new AdminDTO();

		try (Connection conn = ConnectionUtil.getConnectionForJUnit()) {
			AdminDAO dao = new AdminDAO(conn);
			adminDTO = dao.findById(adminId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return adminDTO;
	}

}
