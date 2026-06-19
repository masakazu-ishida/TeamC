/**
 * 
 */
package jp.co.sars.service;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;

import jp.co.sars.dao.ItemsDAO;
import jp.co.sars.dao.ItemsInCartDAO;
import jp.co.sars.dao.PurchaseDetailsDAO;
import jp.co.sars.dao.PurchasesDAO;
import jp.co.sars.dto.ItemsDTO;
import jp.co.sars.dto.ItemsInCartDTO;
import jp.co.sars.dto.PurchaseDetailsDTO;
import jp.co.sars.dto.PurchasesDTO;
import jp.co.sars.util.ConnectionUtil;

/**
 * 
 */
public class PurchaseCommitService {

	java.util.Date utilDate = new java.util.Date();

	public Map<String, Object> execute(String userId, String destination, Date date) throws ServletException {

		String jndiName = "java:comp/env/jdbc/ecsite";
		List<PurchasesDTO> list = new ArrayList<>();
		int userPrice = 0;

		try (Connection conn = ConnectionUtil.getConnection(jndiName)) {
			conn.setAutoCommit(false);

			try {
				PurchasesDAO pcDAO = new PurchasesDAO(conn);
				PurchasesDTO pcDTO = new PurchasesDTO();

				pcDTO.setPurchasedUser(userId);
				pcDTO.setDestination(destination);
				pcDTO.setPurchasedDate(date);
				pcDTO.setCancel(false);

				int count = pcDAO.insert(pcDTO);

				ItemsInCartDAO iicDAO = new ItemsInCartDAO(conn);
				PurchaseDetailsDAO pcdDAO = new PurchaseDetailsDAO(conn);
				ItemsDAO itemsDAO = new ItemsDAO(conn);

				List<ItemsInCartDTO> byUser = iicDAO.findByUser(userId);
				List<PurchaseDetailsDTO> purchaseList = new ArrayList<>();

				for (ItemsInCartDTO cart : byUser) {
					int amount = cart.getAmount();
					int itemId = cart.getItemId();

					ItemsDTO currentItem = itemsDAO.findById(itemId);
					int currentStock = currentItem.getStock();
					int price = cart.getItems().getPrice();
					userPrice += (price * amount);

					if (amount > currentStock) {
						throw new ServletException();
					}

					PurchaseDetailsDTO pcdDTO = new PurchaseDetailsDTO();
					pcdDTO.setPurchaseId(pcDTO.getPurchaseId());
					pcdDTO.setAmount(amount);
					pcdDTO.setItemId(itemId);
					pcdDAO.insert(pcdDTO);

					/*
					ItemsDTO itemsDTO = new ItemsDTO();
					itemsDTO.setAmount(amount);
					itemsDTO.setItemId(itemId);
					itemsDAO.updateRow(itemsDTO);
					 */
					ItemsDTO itemsDTO = cart.getItems();
					itemsDTO.setAmount(amount);
					itemsDAO.updateRow(itemsDTO);

					//pcdDTO.setItems(cart.getItems());
					pcdDTO.setItems(itemsDTO);

					purchaseList.add(pcdDTO);
				}

				if (pcDTO.getDestination() == null || pcDTO.getDestination().trim().isEmpty()) {
					pcDTO.setDestination("ご自宅");
				}

				pcDTO.setList(purchaseList);
				list.add(pcDTO);

				// カートから商品を削除
				for (ItemsInCartDTO cart : byUser) {
					iicDAO.delete(userId, cart.getItemId());
				}

				conn.commit();

			} catch (Exception e) {
				// TODO: handle exception
				conn.rollback();
				throw e;

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		Map<String, Object> result = new HashMap<>();
		result.put("cartList", list);
		result.put("userPrice", userPrice);
		return result;

	}

}
