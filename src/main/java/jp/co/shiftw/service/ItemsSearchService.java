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

		//リストの作成
		List<ItemsDTO> list = new ArrayList<>();

		//CommonConstantsのLOOKUP_NAMEに接続
		ConnectionUtil.mode = ConnectionUtil.MODE.TEST;
		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {

			//トランザクション処理を行う
			try {
				ItemsDAO dao = new ItemsDAO(conn);
				list = dao.findByCond(categoryId, name);

			} catch (SQLException e) {

				e.printStackTrace();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	public static List<ItemsDTO> findByCondForPaging(int categoryId, String name, int pageNumber) {

		//リストの作成
		List<ItemsDTO> list = new ArrayList<>();

		//CommonConstantsのLOOKUP_NAMEに接続
		ConnectionUtil.mode = ConnectionUtil.MODE.TEST;
		try (Connection conn = ConnectionUtil.getConnection(CommonConstants.LOOKUP_NAME)) {

			//トランザクション処理を行う
			try {
				ItemsDAO dao = new ItemsDAO(conn);
				list = dao.findByCondForPaging(categoryId, name, pageNumber);

			} catch (SQLException e) {

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
