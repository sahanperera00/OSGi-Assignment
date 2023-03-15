package orderpublisher;

public class OrderModel {
	
	private String orderId;
	ItemModel item;
	private int qty;
	
	public OrderModel() {
		this.orderId = null;
		this.item = null;
		this.qty = 0;
	}
	
	public OrderModel(String orderId,ItemModel item, int qty) {
		this.orderId = orderId;
		this.qty = qty;
		this.item = item;
	}
	
	public void addItem(ItemModel item,int qty) {
		this.item = item;
		this.qty = qty;
	}
	
	public void removeItem() {
		
	}
	
	public double calculateTotal() {
		return this.item.getPrice() * this.qty;
	}
	
	public String getOrderId() {
		return this.orderId;
	}
	
	public void displayOrder() {
		System.out.println("Order Number: " + this.orderId +
				"\nItem Name: " + this.item.getItemName() + 
				"\nItem Number: " + this.item.getItemNo() + 
				"\nItem Price: " + this.item.getPrice()+
				"\nQuantity: " + this.qty + 
				"\nTotal Amount: " + this.qty * item.getPrice());
	}

}