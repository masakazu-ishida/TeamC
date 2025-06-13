package jp.co.shiftw.service;

import java.sql.Connection;

import jp.co.shiftw.util.CommonConstants;
import jp.co.shiftw.util.ConnectionUtil;

public class CartListService {

	private String lookupString = CommonConstants.LOOKUP_NAME;

	Connection conn = ConnectionUtil.getConnection(lookupString);
}
