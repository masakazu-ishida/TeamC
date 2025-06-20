package jp.co.shiftw.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import jp.co.shiftw.dao.CategoriesDAO;
import jp.co.shiftw.dto.CategoriesDTO;
import jp.co.shiftw.util.CommonConstants;
import jp.co.shiftw.util.ConnectionUtil;

public class CategoryTagService {

	public static List<CategoriesDTO> categories() {

		List<CategoriesDTO> list = new ArrayList<>();

		ConnectionUtil.mode = ConnectionUtil.MODE.TEST;
		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {

			CategoriesDAO dao = new CategoriesDAO(conn);
			list = dao.findAll();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
