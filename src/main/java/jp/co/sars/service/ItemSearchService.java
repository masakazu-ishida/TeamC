package jp.co.sars.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;

import jp.co.sars.dao.ItemsDAO;
import jp.co.sars.dto.ItemsDTO;
import jp.co.sars.util.ConnectionUtil;

public class ItemSearchService {

	public List<ItemsDTO> execute(String keyword, String category) throws ServletException {

		String jndiName = "java:comp/env/jdbc/ecsite";

		List<ItemsDTO> itemList = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection(jndiName)) {
			ItemsDAO itemsDAO = new ItemsDAO(conn);
			if (keyword != null && category.equals("1")) {
				itemList = itemsDAO.findAm(keyword);
			} else if (keyword != null && category.equals("2")) {
				itemList = itemsDAO.findAll2(keyword, 1);
			} else if (keyword != null && category.equals("3")) {
				itemList = itemsDAO.findAll2(keyword, 2);
			} else if (keyword == null && category.equals("1")) {
				itemList = itemsDAO.findAll();
			} else if (keyword == null && category.equals("2")) {
				itemList = itemsDAO.findByCategory(1);
			} else if (keyword == null && category.equals("3")) {
				itemList = itemsDAO.findByCategory(2);
			}

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return itemList;

	}

}
