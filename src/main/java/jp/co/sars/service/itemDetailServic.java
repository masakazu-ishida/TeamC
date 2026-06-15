package jp.co.sars.service;

import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.ServletException;

import jp.co.sars.dao.ItemsDAO;
import jp.co.sars.dto.ItemsDTO;
import jp.co.sars.util.ConnectionUtil;

public class itemDetailServic {

	public ItemsDTO execute(int itemId) throws ServletException {

		String jndiName = "java:comp/env/jdbc/ecsite";

		ItemsDTO item = new ItemsDTO();
		try (Connection conn = ConnectionUtil.getConnection(jndiName)) {
			ItemsDAO dao = new ItemsDAO(conn);

			item = dao.findById1(itemId);

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return item;
	}
}
