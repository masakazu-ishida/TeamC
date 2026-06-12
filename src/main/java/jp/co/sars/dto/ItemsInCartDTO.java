package jp.co.sars.dto;

import java.sql.Date;

public class ItemsInCartDTO {
	private String userId;//会員ID
	private int itemId;//商品ID
	private int amount;//数量
	private Date bookedDate;//登録日

	//結合先Items
	private ItemsDTO items;//商品名

	//ゲッターセッター

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getBookedDate() {
		return bookedDate;
	}

	public void setBookedDate(Date bookedDate) {
		this.bookedDate = bookedDate;
	}

	public ItemsDTO getItems() {
		return items;
	}

	public void setItems(ItemsDTO items) {
		this.items = items;
	}

}
