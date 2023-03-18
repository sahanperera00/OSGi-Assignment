package userServiceSubscriber;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import userpublisher.UserModel;
import userpublisher.UserPublisher;
import userpublisher.IUserPublisher;

public class UserActivator implements BundleActivator {

	@SuppressWarnings("rawtypes")
	ServiceReference serviceReference;

	Scanner input = new Scanner(System.in);

	@SuppressWarnings("unchecked")
	public void start(BundleContext context) throws Exception {
		serviceReference = context.getServiceReference(IUserPublisher.class.getName());
		IUserPublisher servicePublish = (IUserPublisher) context.getService(serviceReference);
		serviceImplement(servicePublish);
	}
	
	public void stop(BundleContext context) throws Exception {
		context.ungetService(serviceReference);
	}

	public void serviceImplement(IUserPublisher servicePublish) {
		@SuppressWarnings("resource")
		int option = 0;
		int check = 0;
		UserModel instance = null;
		
		do {
			if(check == 1) {
				System.out.println("\nHello " + instance.getFirstName() + " " + instance.getLastName());
				System.out.println("===============================");
				System.out.println("1  - View user details");
				System.out.println("2  - Update user details");
				System.out.println("3  - Logout user\n");
				System.out.print("Choose your option: ");
				
				option = input.nextInt();
				
				switch(option) {
					case 1: servicePublish.viewUser(instance);
						break;
					case 2: servicePublish.updateUser(instance);
						break;
					case 3: 
//						servicePublish.deleteUser();
						check = 0;
						break;
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
					case 2: instance = servicePublish.loginUser();
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
}
