package jp.co.shiftw.dto;

import java.util.Date;

public class CartDTO {

	private int userId; //ユーザーID
	private ItemsDTO items; //ItemsDTO
	private int amount; //商品の数量
	private Date bookedDate; //カート登録日

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public ItemsDTO getItems() {
		return items;
	}

	public void setItems(ItemsDTO items) {
		this.items = items;
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

}
