package jp.co.shiftw.dto;

import java.sql.Date;

// 購入履歴のDTO
public class PurchasesDTO {
	private int purchaseId; //注文ID
	private String purchasedUser; //注文者
	private Date purchasedDate; //注文日
	private String destination; //配送先
	private boolean canse; //キャンセル

	public int getPurchaseId() {
		return purchaseId;
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

	public boolean isCanse() {
		return canse;
	}

	public void setCanse(boolean canse) {
		this.canse = canse;
	}

}
