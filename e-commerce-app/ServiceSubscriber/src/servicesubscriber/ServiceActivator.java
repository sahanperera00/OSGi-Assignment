package servicesubscriber;

import com.mtit.service.ServiceModel;
import com.mtit.service.ServicePublish;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class ServiceActivator implements BundleActivator {

	@SuppressWarnings("rawtypes")
	ServiceReference serviceReference;


	@SuppressWarnings("unchecked")
	public void start(BundleContext context) throws Exception {
		serviceReference = context.getServiceReference(ServicePublish.class.getName());
		ServicePublish servicePublish = (ServicePublish) context.getService(serviceReference);
		serviceImplement(servicePublish);
	}

	
	public void stop(BundleContext context) throws Exception {
		context.ungetService(serviceReference);
	}

	public void serviceImplement(ServicePublish service) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int option = 0;
		int check = 0;
		ServiceModel instance = null;
		
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
					case 1: service.viewUser(instance);
						break;
					case 2: service.updateUser(instance);
						break;
					case 3: service.deleteUser();
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
					case 1: service.registerUser();
						break;
					case 2: instance = service.loginUser();
						check = 1;
						break;
				}
			}
		} while(option != 99);
		return ;
	}
}
