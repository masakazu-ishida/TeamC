
package jp.co.sars.dto;

import java.sql.Date;
import java.util.List;

public class PurchasesDTO {

	//注文テーブル
	private int purchaseId;//注文ID(PK)
	private String purchasedUser;//注文者(FK)
	private Date purchasedDate;//注文日
	private String destination;//発送先
	private boolean cancel;//キャンセル

	private List<PurchaseDetailsDTO> list;

	//ゲッターセッター

	public int getPurchaseId() {
		return purchaseId;
	}

	public List<PurchaseDetailsDTO> getList() {
		return list;
	}

	public void setList(List<PurchaseDetailsDTO> list) {
		this.list = list;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getPurchasedUser() {
		return purchasedUser;
	}

	public void setPurchasedUser(String purchasedUser) {
		this.purchasedUser = purchasedUser;
	}

	public Date getPurchasedDate() {
		return purchasedDate;
	}

	public void setPurchasedDate(Date purchasedDate) {
		this.purchasedDate = purchasedDate;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public boolean isCancel() {
		return cancel;
	}

	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}

}