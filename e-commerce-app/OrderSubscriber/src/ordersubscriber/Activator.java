package ordersubscriber;

import orderpublisher.IOrderService;
import orderpublisher.ItemModel;
import orderpublisher.OrderModel;

import java.util.ArrayList;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;




public class Activator implements BundleActivator {
	
	ServiceReference OrderServiceReference;
	
	public void start(BundleContext context) throws Exception {
		System.out.println("Start Order Subscriber\n=====================");
		
		//Register the Order Subscriber Service
		OrderServiceReference = context.getServiceReference(IOrderService.class.getName());
		IOrderService orderService = (IOrderService)context.getService(OrderServiceReference);
		
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Hello! Welcome to Fresheez!");
		System.out.println("============================");
		System.out.println("1. Order from Market \n2. Show all Orders\n3. Update Order\n4. Delete Order\n0. Exit");
		
		
		int choice = in.nextInt();
		while(choice != 1 || choice != 2 || choice != 3 || choice != 4) {
			
			switch(choice){
				case 1: marketMenu(orderService);
						break;
				
				case 2: viewAllOrders(orderService);
						break;
				
				case 3: updateOrders(orderService);
						break;
				
				case 4: deleteOrders(orderService);
						break;
				
				default: System.out.println("Invalid, Please try again");
						 choice = in.nextInt();
						 break;
			}
				System.out.print("1. Order from Market \n2. Show all Orders\n3. Update Order\n4. Delete Order\n0. Exit");
				choice = in.nextInt();
				if(choice == 0) {
					System.out.println("Thank you for visiting Whatever this is. Have a Nice Day!");
					break;
				}
		}
	}
	
	
	private void marketMenu(IOrderService order) {
		
		Scanner in = new Scanner(System.in);
		
		ItemModel cap = new ItemModel(25, "Caps", 250.0);
		ItemModel shirt = new ItemModel(23, "Shirts", 1000.0);
		ItemModel pants = new ItemModel(27,"Pants",1250.0);
		
		ArrayList <ItemModel> items = new <ItemModel> ArrayList();
		
		//Adding the content to the list 
		items.add(cap);
		items.add(shirt);
		items.add(pants);
		
		//Basic Implementation 
		System.out.println("Hello Welcome to Fresheez");
		System.out.println("Please select an Item you wish to order. You May enter the Item Number\n");
		System.out.println("Item Name\tItem No\tItem Price\t");
		
		for(ItemModel x : items) {
			System.out.println(x.getItemName() +"\t" +x.getItemNo() + "\t" + x.getPrice());
		}		
		
		int choice = in.nextInt();
		boolean bool = false;
		ItemModel purchased = new ItemModel(0, null, 0);

			for(ItemModel x : items) {
				if(choice == x.getItemNo()) {
					System.out.println("Item Number: " + x.getItemNo() + " has been put to order!");
					bool = true;
					purchased = x;
					order.createOrder(purchased);
					break;
				}
			}
		}
	
	private void viewAllOrders(IOrderService orderService) {
		System.out.println("Shows All Orders Here");
	}
	
	
	private void updateOrders(IOrderService orderService) {
		System.out.println("SQL Content to update orders");
	}
	

	private void deleteOrders(IOrderService orderService) {
		System.out.println("Deletes one order via SQL");
	}
	
	public void stop(BundleContext context) {
		System.out.println("Thank you for Ordering. Have a nice Day!");
		context.ungetService(OrderServiceReference);
	}

}
