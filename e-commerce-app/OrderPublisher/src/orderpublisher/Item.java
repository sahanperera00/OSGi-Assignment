package orderpublisher;

public class Item {
	
	private String itemId;
	private int kioskId;
	private String itemName;
	private double price;
	
	public Item() {
		this.itemId = null;
		this.kioskId = 0;
		this.itemName = null;
		this.price = 0.0;
	}
	
	//overloaded constructor
	public Item(String itemId,int kioskId, String itemName, double price) {
		this.itemId = itemId;
		this.kioskId = kioskId;
		this.itemName = itemName;
		this.price = price;
	}
	
	//getters
	public String getItemId() {
		return this.itemId;
	}
	public int getKioskId() {
		return this.kioskId;
	}
	public String getItemName() {
		return this.itemName;
	}
	public double getItemPrice() {
		return this.price;
	}
	
	//Setters
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public void setKioskId(int kioskId) {
		this.kioskId = kioskId;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public void setItemPrice(double price) {
		this.price = price;
	}
}
