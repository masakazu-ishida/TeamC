package jp.co.shiftw.dto;

//注文詳細のDTO
public class PurchaseDetailsDTO {
	private int purchaseDetailId; //注文詳細ID
	private PurchasesDTO purchase; //注文ID
	private ItemsDTO item; //商品
	private int amount; //注文数

	public int getPurchaseDetailId() {
		return purchaseDetailId;
	}

	public void setPurchaseDetailId(int purchaseDetailId) {
		this.purchaseDetailId = purchaseDetailId;
	}

	public PurchasesDTO getPurchase() {
		return purchase;
	}

	public void setPurchase(PurchasesDTO purchase) {
		this.purchase = purchase;
	}

	public ItemsDTO getItem() {
		return item;
	}

	public void setItem(ItemsDTO item) {
		this.item = item;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}