package orderpublisher;

import java.util.ArrayList;
import java.util.List;

public interface IOrderServices {
	
	public boolean createOrder(int product,List<Item> items);
	public void viewOrder();
	public ArrayList<Order> viewAllOrders();
	public void deleteOrder();
	public void updateOrder();
	
	
}
