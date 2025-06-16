package jp.co.shiftw.dto;

import java.util.Date;
import java.util.List;

public class CartDTO {

	private int userId; //ユーザーID
	private List<ItemsDTO> items; //ItemsDTO
	private int amount; //商品の数量
	private Date bookedDate; //カート登録日

	//Getter&Setter
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<ItemsDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemsDTO> items) {
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
