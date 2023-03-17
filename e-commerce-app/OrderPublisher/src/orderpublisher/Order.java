package orderpublisher;

public class Order {
	
	private String orderId;
	private String itemName;
	private String itemNo;
	private double price;
	private int qty;
	private double total;
	
	public Order() {
		this.orderId = null;
		this.itemName = null;
		this.itemNo = null;
		this.price = 0.0;
		this.qty = 0;
		this.total = 0.0;
	}
	
	public Order(String orderId, String itemName, String itemNo, double price, int qty) {
		this.orderId = orderId;
		this.itemName = itemName;
		this.itemNo = itemNo;
		this.price = price;
		this.qty = qty;
		this.total = this.qty * this.price;
	}

	//Getters
	public double getTotal() {
		return total;
	}
	
	public String getOrderId() {
		return orderId;
	}

	public String getItemName() {
		return itemName;
	}

	public String getItemNo() {
		return itemNo;
	}

	public double getPrice() {
		return price;
	}

	public int getQty() {
		return qty;
	}

	//Setters
	public void setTotal(double total) {
		this.total = total;
	}
	
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
	
	
}
