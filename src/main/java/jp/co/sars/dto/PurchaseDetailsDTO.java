package jp.co.sars.dto;

public class PurchaseDetailsDTO {

	//注文詳細テーブル
	private int purchaseDetailId;//注文詳細ID(PK)
	private int purchaseId;//注文ID(FK)
	private int itemId;//商品ID(FK)
	private int amount;//注文数

	//結合用の注文テーブルと商品テーブル
	private PurchasesDTO purchases;
	private ItemsDTO items;

	public void setPurchases(PurchasesDTO purchases) {
		this.purchases = purchases;
	}

	public void setItems(ItemsDTO items) {
		this.items = items;
	}

	public PurchasesDTO getPurchases() {
		return purchases;
	}

	public ItemsDTO getItems() {
		return items;
	}

	public int getPurchaseDetailId() {
		return purchaseDetailId;
	}

	public void setPurchaseDetailId(int purchaseDetailId) {
		this.purchaseDetailId = purchaseDetailId;
	}

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
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
}
