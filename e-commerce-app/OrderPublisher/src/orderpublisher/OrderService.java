package orderpublisher;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderService implements IOrderService{
	
	Scanner in = new Scanner(System.in);
	
//	private Connection connection = null;
//	private Statement statement = null;
//	private IEComDatabase ecomDatabase;
//	private ResultSet resultSet;
	

	public OrderService() {
		super();
		//ecomDatabase = (IEcomDatabase)new EcomDatabase(); 
		
	}
	public void createOrder(ItemModel item) {
		

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
		
		ArrayList<OrderModel> list = new ArrayList<OrderModel>();
//		try {
//			
//			statement = connection.createStatement();
//			String SelectAll = "SELECT * FROM orderDetails";
//		}catch (SQLException e) {
//			e.printStackTrace();
//		}
	}

	@Override
	public void viewOrder() {
		// TODO Auto-generated method stub
		
		String orderId;
		System.out.print("Enter Customer ID: ");
		orderId = in.nextLine().trim();
		
		
		String getbyId = "SELECT * FROM orderDetails WHERE id = '"+orderId +"'";
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(orderId);
			while(resultSet.next()) {
				System.out.printf("\n%20d %20s %20d %20s \n",resultSet.getInt("id"),resultSet.getString("customerName"),resultSet.getInt("customerTele"),resultSet.getString("rentedVehicle"));
			}
		}
		
	}

	@Override
	public void updateOrder(OrderModel order) {
		// TODO Auto-generated method stub
		
		//Update method pending
		System.out.println("Please choose to add or remove the item.");
		System.out.println("1. Add or change the item.\n2. Remove the item.");
		
		
	}
	

	
}
