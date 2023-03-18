package itempublisher;

public class ItemModel {

	private int id;
	private String ItemId;
	private String ItemName;
	private String ItemType;
	private String description;
	private double price;
	private int discount;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItemId() {
		return ItemId;
	}
	public void setItemId(String itemId) {
		ItemId = itemId;
	}
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getItemType() {
		return ItemType;
	}
	public void setItemType(String itemType) {
		ItemType = itemType;
	}
	
	
	
}
