package jp.co.shiftw.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;

import jp.co.shiftw.dao.CartDAO;
import jp.co.shiftw.util.CommonConstants;
import jp.co.shiftw.util.ConnectionUtil;

public class CartAddService {

	public static void CartAdd(String userId, int itemId, int amount, Date bookedDate) {

		ConnectionUtil.mode = ConnectionUtil.MODE.TEST;
		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {

			CartDAO dao = new CartDAO(conn);

			dao.CartCerate(userId, itemId, amount, bookedDate);

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
