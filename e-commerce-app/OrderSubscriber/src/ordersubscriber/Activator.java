package ordersubscriber;

import orderpublisher.IOrderServices;
import orderpublisher.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

	ServiceReference orderServiceReference;
	
	Scanner input = new Scanner(System.in);
	
	
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Order Subscriber Up and Running\n\n\n\n");
		orderServiceReference = bundleContext.getServiceReference(IOrderServices.class.getName());
		IOrderServices order = (IOrderServices) bundleContext.getService(orderServiceReference);
		
		displayKiosk(order);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Order Subscriber Stopped!");
	}
	
	private void displayKiosk(IOrderServices order) {
		
		//Ideal method is, fetching item data to an arraylist and viewing data throughout
		System.out.println("== Lanka Mount Castle ==");
		System.out.println("Welcome to the Lanka Mount Kiosk!");
		System.out.println("1. Order merchandise\n2. Get your order history\n3. Delete orders\n4. Update pending orders\n5. View one Order");
		System.out.println("0. Exit the Kiosk\n:");
		
		
		int option = input.nextInt();
		do {
			switch(option) {
			case 1: displayMenu(order);
					break;
			case 2: showOrders(order);
					break;
			case 3: deleteOrders(order);
					break;
			case 4: updateOrders(order);
					break;
			case 5: viewOneOrder(order);
					break;
			case 0: System.out.println("Thank you for using Lanka Mount Castle Kiosk. Have a pleasant Day!");
					return;
					
			default:System.out.println("Invalid input, Please try again.\n:");
					option = input.nextInt();
					break;
					
			}
			
			System.out.println("\n1. Order merchandise\n2. Get your order history\n3. Delete orders\n4. Update pending orders");
			System.out.println("0. Exit the Kiosk\n:");
			option = input.nextInt();


		}while(option < 5);
			
	}
	
	//This method should be fetched by an ItemModel - but temporarily this will be made
	
	private void displayMenu(IOrderServices order) {
		
		Item caps = new Item("K2121", 2121, "Caps", 750.0);
		Item shirts = new Item("K2133",2133,"Shirts",1500.0);
		Item pants = new Item("K3142",3142,"Pants",2450.0);
		Item cropTops = new Item("K4132",4132,"Torso",1250.0);
		Item tShirts = new Item("K1234",1234,"T-Shirts",1500);
		
		
		List<Item> items = new ArrayList<Item>();
		items.add(caps);
		items.add(shirts);
		items.add(pants);
		items.add(cropTops);
		items.add(tShirts);
		
		System.out.println("KCode\t\tItem Name\tItem Price");
		for(Item x : items) {
			System.out.println(x.getKioskId() + "\t\t" + x.getItemName() + "\t\t Rs." + x.getItemPrice());
		}
		System.out.println("Please enter the KCode you wish to purchase: ");
		int product = input.nextInt();
		order.createOrder(product, items);
		
		
		//After the SQL content -> Consider the flow of information
		
//		if(orderReturn) {
//			System.out.println("Do you wish to add another item? (y/n)");
//			String value = input.nextLine().trim();
//			if(value.equalsIgnoreCase("y")) {
//				displayMenu(order);
//			}
//		}
		
	}
	private void showOrders(IOrderServices order) {
		
		order.viewAllOrders();
	}
	
	//Yet to be implemented
	private void deleteOrders(IOrderServices order) {
		order.viewOrder();
		order.deleteOrder();
	}
	
	private void updateOrders(IOrderServices order) {
		
		order.updateOrder();
	}
	
	private void viewOneOrder(IOrderServices order) {
		order.viewOrder();
	}
}
