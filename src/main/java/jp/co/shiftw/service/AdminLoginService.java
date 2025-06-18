package jp.co.shiftw.service;

import java.sql.Connection;

import jp.co.shiftw.dao.AdminDAO;
import jp.co.shiftw.dto.AdminDTO;
import jp.co.shiftw.util.CommonConstants;
import jp.co.shiftw.util.ConnectionUtil;

public class AdminLoginService {

	public static AdminDTO loginAdmin(String adminId, String password) {

		AdminDTO adminDTO = null;
		ConnectionUtil.mode = ConnectionUtil.MODE.TEST;
		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {
			AdminDAO dao = new AdminDAO(conn);
			adminDTO = dao.findById(adminId);

			if (adminDTO != null) {
				if (adminDTO.getPassword().equals(password)) {

					return adminDTO;
				} else {
					return null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return adminDTO;
	}

}
