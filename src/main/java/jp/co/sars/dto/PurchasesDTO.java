package jp.co.sars.dto;

import java.sql.Date;

public class PurchasesDTO {

	//注文テーブル
	private int purchaseId;//注文ID(PK)
	private String purchasedUser;//注文者(FK)
	private Date purchasedDate;//注文日
	private String destination;//発送先
	private boolean cancel;//キャンセル

	//結合先Itemsテーブル
	private ItemsDTO items;

	//結合先PurchaseDetailsテーブル
	private PurchaseDetailsDTO purchaseDetails;

	//ゲッターセッター

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

	public boolean isCancel() {
		return cancel;
	}

	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}

	public ItemsDTO getItems() {
		return items;
	}

	public void setItems(ItemsDTO items) {
		this.items = items;
	}

	public PurchaseDetailsDTO getPurchaseDetails() {
		return purchaseDetails;
	}

	public void setPurchaseDetails(PurchaseDetailsDTO purchaseDetails) {
		this.purchaseDetails = purchaseDetails;
	}

}
