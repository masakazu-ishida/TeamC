package jp.co.shiftw.dto;

import java.sql.Date;

public class PurchasesDTO {
	private int purchaseId;
	private String purchasedUser;
	private Date purchasedDate;
	private String destination;
	private boolean canse;

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
