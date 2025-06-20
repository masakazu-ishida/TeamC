package jp.co.shiftw.service;

import java.sql.Connection;

import jp.co.shiftw.util.CommonConstants;
import jp.co.shiftw.util.ConnectionUtil;

public class CartDeleteService {
	public static void cartDeleteService(String userId, int itemId) {

		ConnectionUtil.mode = ConnectionUtil.MODE.TEST;
		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
