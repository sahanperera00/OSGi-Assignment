package orderpublisher;

public interface IOrderService {
	
	//Mega Order related
	public void createOrder(ItemModel item);
	public void removeOrder(OrderModel order);
	public void viewAllOrders();
	public void viewOrder(OrderModel order);
	public void updateOrder(OrderModel order);
	
	//Item reference
	
	
}
