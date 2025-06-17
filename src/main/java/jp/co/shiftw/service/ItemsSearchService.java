package jp.co.shiftw.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.shiftw.dao.ItemsDAO;
import jp.co.shiftw.dto.ItemsDTO;
import jp.co.shiftw.util.CommonConstants;
import jp.co.shiftw.util.ConnectionUtil;

public class ItemsSearchService {

	public static List<ItemsDTO> findByCond(int categoryId, String name) {

		List<ItemsDTO> list = new ArrayList<>();

		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {

			conn.setAutoCommit(false);

			try {
				ItemsDAO dao = new ItemsDAO(conn);
				list = dao.findByCond(categoryId, name);

				conn.commit();

			} catch (SQLException e) {

				conn.rollback();
				e.printStackTrace();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

}
