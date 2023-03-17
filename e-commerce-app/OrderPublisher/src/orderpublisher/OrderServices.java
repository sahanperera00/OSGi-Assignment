package orderpublisher;

import ecommercedb.EcommerceDb;
import ecommercedb.IEcommerceDb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class OrderServices implements IOrderServices {
	
	private Connection connection = null;
	private Statement statement = null;
	private IEcommerceDb ecommerceDb;
	private ResultSet resultSet;
	private PreparedStatement preparedStatement;
	
	
	Scanner input = new Scanner(System.in);
	 
	public OrderServices() {
		super();
		ecommerceDb = (IEcommerceDb) new EcommerceDb();
		connection = ecommerceDb.connection();
	}
	
	@Override
	public boolean createOrder(int product,List<Item> items) {
		// TODO Auto-generated method stub
		for(Item x: items) {
			if(product == x.getKioskId()) {
				System.out.printf("Please enter the quantity: ");
				int qty = input.nextInt();
				double total = qty * x.getItemPrice();
				
				String preparedOrder  = "INSERT INTO orders(itemName, itemNo, price, qty, total) VALUES(?,?,?,?,?)";
				try {
					
					preparedStatement= connection.prepareStatement(preparedOrder);
					
					//Assigning the values to the parameters
					
					preparedStatement.setString(1, x.getItemName());
					preparedStatement.setString(2,x.getItemId());
					preparedStatement.setDouble(3, x.getItemPrice());
					preparedStatement.setInt(4, qty);
					preparedStatement.setDouble(5, total);

					
					preparedStatement.executeUpdate();
					System.out.println("Order successfully created!");
					return true;
					
				}catch(SQLException e) {
					System.out.println("Error with creating an order");
					System.out.println(e.getMessage());
					return false;
				}
			}
		}
		return false;
	}

	
	@Override
	public ArrayList<Order> viewAllOrders() {
		// TODO Auto-generated method stub
		ArrayList<Order> orderList = new ArrayList<Order>();
		try {
			statement = connection.createStatement();
			String selectAll = "SELECT * FROM orders";
			resultSet = statement.executeQuery(selectAll);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		try {	
			while(resultSet.next()) {
				Order order = new Order();
				
				order.setOrderId(resultSet.getString("orderId"));
				order.setItemName(resultSet.getString("itemName"));
				order.setItemNo(resultSet.getString("itemNo"));
				order.setPrice(resultSet.getDouble("price"));
				order.setQty(resultSet.getInt("qty"));
				order.setTotal(resultSet.getDouble("total"));
				orderList.add(order);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("OrderID\tItem\tItemNo\tPrice\tQty\tTotal");
		for(Order x: orderList) {
			System.out.println(x.getOrderId() + "\t" + x.getItemName() + "\t" + x.getItemNo() + "\t" + x.getPrice() + "\t" + x.getQty() + "\t" + x.getTotal());
			
		}
		System.out.println("\n");
		return orderList;
	}

	
	@Override
	public void viewOrder() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOrder() {
		// TODO Auto-generated method stub
		
		System.out.println("Please specify the Order number you wish to delete");
		int orderNo = input.nextInt();
		
	}
	
	public void updateOrder() {
		
		System.out.print("\nPlease enter the Order you wish to update:");
		ArrayList<Order> orderlist =this.viewAllOrders();
		
		String orderNo = input.nextLine().trim();
		boolean status = false;
		while(!status) {
			for(Order x :orderlist) {
				if(orderNo.equalsIgnoreCase(x.getOrderId())) {
					status = true;
					String delete = "DELETE FROM ORDERS WHERE OrderId = '" +orderNo+"'";
					
					try {
						statement = connection.createStatement();
						statement.executeUpdate(delete);
						System.out.println("Rental Successfully Deleted");
						
					}catch(SQLException e) {
						System.out.println("Error with Order");
						System.out.println(e.getMessage());
					}
					
					
					break;
				}else {
					status = false;
				}
			}
			if(status == false) {
				System.out.println("Order number mentioned does not exist. Please try again.\n");
				break;
			}
		}
		
	}

}
