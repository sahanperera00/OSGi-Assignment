package orderpublisher;
import java.util.Scanner;

public class OrderService implements IOrderService{
	
	Scanner in = new Scanner(System.in);

	@Override
	public void createOrder(ItemModel item) {
		// TODO Auto-generated method stub
		
		System.out.println("Please enter the quantity you wish to order: ");
		int quantity = in.nextInt();
		
		OrderModel order = new OrderModel();
		order.addItem(item, quantity);
		System.out.println("Item " + item.getItemName()+ " has been added.");
		
	}

	@Override
	public void removeOrder(OrderModel order) {
		// TODO Auto-generated method stub
		String orderNumber = order.getOrderId();
		order = null;
		System.out.println("Order No: " + orderNumber + " has been removed successfully!");
	}

	@Override
	public void viewAllOrders() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewOrder(OrderModel order) {
		// TODO Auto-generated method stub
		order.displayOrder();
	}

	@Override
	public void updateOrder(OrderModel order) {
		// TODO Auto-generated method stub
		System.out.println("Please choose to add or remove the item.");
		System.out.println("1. Add or change the item.\n2. Remove the item.");
		int choice = in.nextInt();
		
		if(choice == 1) {
			System.out.println("");
		}
		
	}
	

	
}
