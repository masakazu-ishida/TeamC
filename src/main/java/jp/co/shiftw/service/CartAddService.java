package jp.co.shiftw.service;

import java.sql.Connection;

import jp.co.shiftw.dao.CartDAO;
import jp.co.shiftw.util.CommonConstants;
import jp.co.shiftw.util.ConnectionUtil;

public class CartAddService {

	public static void CartAdd() {

		ConnectionUtil.mode = ConnectionUtil.MODE.TEST;
		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {

			CartDAO dao = new CartDAO(conn);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
