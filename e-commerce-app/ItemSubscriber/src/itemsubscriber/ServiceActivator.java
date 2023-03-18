
package itemsubscriber;

import java.util.Scanner;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import itempublisher.ItemPublish;

public class ServiceActivator implements BundleActivator {
	
	@SuppressWarnings("rawtypes")
	ServiceReference ItemserviceReference;

	
    @Override
	public void start(BundleContext context) throws Exception {
		
		System.out.println("Start Subscriber Service");
		ItemserviceReference = context.getServiceReference(ItemPublish.class.getName());
		@SuppressWarnings("unchecked")
		ItemPublish item = (ItemPublish) context.getService(ItemserviceReference);
		displayItem(item);
	}
 
    
    @Override
	public void stop(BundleContext context) throws Exception {
		
    	System.out.println("Good Bye !!!");
    	context.ungetService(ItemserviceReference);
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
    
  
    
    
    

}