package jp.co.shiftw.service;

import java.sql.SQLException;
import java.util.List;

import jp.co.shiftw.dao.ItemsDAO;
import jp.co.shiftw.dto.ItemsDTO;

public class ItemsSearchService {

	public static List<ItemsDTO> findByCond(int categoryId, String name) throws SQLException {

		ItemsDAO dao = new ItemsDAO(conn);

		return dao.findByCond(categoryId, name);

	}

	{

	}

}
