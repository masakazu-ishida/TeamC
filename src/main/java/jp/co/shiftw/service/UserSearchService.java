package jp.co.shiftw.service;

import java.sql.Connection;
import java.sql.SQLException;

import jp.co.shiftw.dao.UsersDAO;
import jp.co.shiftw.dto.UsersDTO;
import jp.co.shiftw.util.CommonConstants;
import jp.co.shiftw.util.ConnectionUtil;

public class UserSearchService {
	public static UsersDTO searchUserByUserId(String userId) {
		UsersDTO user = null;

		//CommonConstantsのLOOKUP_NAMEに接続
		ConnectionUtil.mode = ConnectionUtil.MODE.TEST;
		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {

			try {
				UsersDAO dao = new UsersDAO(conn);
				user = dao.findById(userId);

			} catch (SQLException e) {

				e.printStackTrace();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}
}
