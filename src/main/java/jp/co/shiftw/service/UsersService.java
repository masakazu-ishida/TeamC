package jp.co.shiftw.service;

import java.sql.Connection;

import jp.co.shiftw.dao.UsersDAO;
import jp.co.shiftw.dto.UsersDTO;
import jp.co.shiftw.util.CommonConstants;
import jp.co.shiftw.util.ConnectionUtil;

public class UsersService {

	public static UsersDTO loginUsers(String user_id, String password) {

		UsersDTO usersdto = new UsersDTO();
		ConnectionUtil.mode = ConnectionUtil.MODE.TEST;
		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {
			UsersDAO dao = new UsersDAO(conn);
			usersdto = dao.findById(user_id);

			if (usersdto != null) {
				if (usersdto.getPassword().equals(password)) {
					return usersdto;
				} else {
					return null;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return usersdto;

	}

}
