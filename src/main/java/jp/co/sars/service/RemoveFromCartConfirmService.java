package jp.co.sars.service;

import java.sql.Connection;

import jakarta.servlet.ServletException;

import jp.co.sars.dao.ItemsInCartDAO;
import jp.co.sars.dto.ItemsInCartDTO;
import jp.co.sars.util.ConnectionUtil;

public class RemoveFromCartConfirmService {

	public ItemsInCartDTO execute(String userId, int itemId) throws ServletException {

		String jndiName = "java:comp/env/jdbc/ecsite";
		ItemsInCartDTO cart = new ItemsInCartDTO();
		try (Connection conn = ConnectionUtil.getConnection(jndiName)) {
			ItemsInCartDAO dao = new ItemsInCartDAO(conn);

			cart = dao.findById(userId, itemId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cart;

	}

}
