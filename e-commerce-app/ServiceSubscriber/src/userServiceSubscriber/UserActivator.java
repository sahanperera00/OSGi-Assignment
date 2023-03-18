package userServiceSubscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import itempublisher.ItemPublish;
import orderpublisher.IOrderServices;
import orderpublisher.Item;
import userpublisher.UserModel;
import userpublisher.IUserPublisher;

public class UserActivator implements BundleActivator {

	@SuppressWarnings("rawtypes")
	ServiceReference userServiceReference;
	@SuppressWarnings("rawtypes")
	ServiceReference orderServiceReference;
	@SuppressWarnings("rawtypes")
	ServiceReference itemServiceReference;

	Scanner input = new Scanner(System.in);

	@SuppressWarnings("unchecked")
	public void start(BundleContext context) throws Exception {
		userServiceReference = context.getServiceReference(IUserPublisher.class.getName());
		IUserPublisher userServicePublish = (IUserPublisher) context.getService(userServiceReference);
		
		orderServiceReference = context.getServiceReference(IOrderServices.class.getName());
		IOrderServices orderServicePublish = (IOrderServices) context.getService(orderServiceReference);

		itemServiceReference = context.getServiceReference(ItemPublish.class.getName());
		ItemPublish itemServicePublish = (ItemPublish) context.getService(itemServiceReference);
		
		serviceImplement(userServicePublish, orderServicePublish, itemServicePublish);
	}
	
	public void stop(BundleContext context) throws Exception {
		context.ungetService(userServiceReference);
	}

	public void serviceImplement(IUserPublisher servicePublish, IOrderServices orderServicePublish, ItemPublish itemServicePublish) {
		int option = 0;
		int check = 0;
		UserModel instance = null;
		
		do {
			if(check == 1) {
				System.out.println("\nHello " + instance.getFirstName() + " " + instance.getLastName());
				System.out.println("===============================");
				System.out.println("1  - View user details");
				System.out.println("2  - Update user details");
				System.out.println("3  - Logout user");
				
//				String x = instance.getRole();
//				System.out.println(x);
				
				if(instance.getRole().equalsIgnoreCase("admin")) {
					System.out.print("4  - Items\n");
					
					System.out.print("Choose your option: ");
					option = input.nextInt();
					
					switch(option) {
					case 1: servicePublish.viewUser(instance);
						break;
					case 2: servicePublish.updateUser(instance);
						break;
					case 3: 
//						servicePublish.deleteUser(instance);
						check = 0;
						break;
					case 4: 
						displayItem(itemServicePublish);
						break;
					}
				} else {
					System.out.println("4  - Orders");
					System.out.println("5  - Payments\n");
					
					System.out.print("Choose your option: ");
					option = input.nextInt();
					
					switch(option) {
					case 1: servicePublish.viewUser(instance);
						break;
					case 2: servicePublish.updateUser(instance);
						break;
					case 3: 
//						servicePublish.deleteUser(instance);
						check = 0;
						break;
					case 4: 
						displayKiosk(orderServicePublish);
						break;
					case 5: 
						
						break;
					}
				}
			} else {
				System.out.println("\nE-Commerce App");
				System.out.println("===============================");
				System.out.println("1  - Register User");
				System.out.println("2  - Login User");
				System.out.println("99 - Exit\n");
				System.out.print("Choose your option: ");
				
				option = input.nextInt();
				
				switch(option) {
					case 1: 
						registerImplementation(servicePublish);
						break;
					case 2: 
						instance = servicePublish.loginUser();
						check = 1;
						break;
				}
			}
		} while(option != 99);
		return ;
	}
	
	public void registerImplementation(IUserPublisher servicePublish) {
		int option = 0;
		
		do {
			System.out.println("\n1  - Register as an Admin");
			System.out.println("2  - Register as a Client");
			System.out.println("-1 - Back\n");
			System.out.print("Choose your option: ");
			
			option = input.nextInt();
			
			switch(option) {
				case 1: 
					servicePublish.registerUser("admin");
					break;
				case 2: 
					servicePublish.registerUser("client");
					break;
				case -1:
					break;
				default:
					System.out.println("\nInvalid Number!");
			}
		} while(option != -1);
	}
	
	private void displayItem(ItemPublish item) {
		
		@SuppressWarnings("resource")
		
		Scanner input = new Scanner(System.in);
		int option;
		String subOption = "y";
		String password = "Chanux123";
		String username = "Chanux123";
		
		
		System.out.println("\n\n\n");
		System.out.println("=================Lanka Mount Castle===================\n");
		
		System.out.println("==========This is Inventory Management Section========\n");
		System.out.println("\n\nPlease Enter Admin Username: ");
		username = input.nextLine().trim();
		
		if(username.equals("Chanux123")) {
			System.out.println("\nPlease Enter Admin Password: ");
			password = input.nextLine().trim();
			if(password.equals("Chanux123")) {
				
				System.out.println("\n-------------------WELCOME BACK-----------------------\n");
				System.out.println("1  - Add an Item");
				System.out.println("2  - Get all Item details");
				System.out.println("3  - Get details about a specific Item ");
				System.out.println("4  - Update an Item ");
				System.out.println("5  - Delete an Item ");
				System.out.println("0  - Exit the Managemnet\n:");
				System.out.println("\n=====================================================");
				System.out.println("\n=====================================================");
				
				System.out.print("\n\nChoose an option from above : \n\n");
				
				option = Integer.parseInt(input.nextLine().trim());
				
				switch(option) {
					case 1:
						item.addItem();
						
						while(subOption.equals("y")) {
							System.out.println("\n\nDo you want to Add Another product (y/n)");
							subOption = input.nextLine().trim();
				
							if(subOption.equals("y")||subOption.equals("Y")) {
								item.addItem();
							}
						}
						displayItem(item);
						break;
					case 2:
						item.getAllItems();
						displayItem(item);
						break;
					case 3:
						item.getByID();
						displayItem(item);
						break;
					case 4:
						item.updateItem();
						displayItem(item);
					case 5:
						item.deleteItem();
						displayItem(item);
						break;
					case 0: 
						System.out.println("Thank you for using Lanka Mount Castle Management . Have a Great Day!");
					return;
					
					default:
						System.out.println("Incorrect Input. Try Again...");
						displayItem(item);
				}
			}else {
				System.out.println("Sorry Invalid Password !!! Try Again!! ");
			}
		}else {
			System.out.println("Sorry Invalid Username!!! Try Again!!!");
		}
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
