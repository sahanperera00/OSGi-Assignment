package paymentsubscriber;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import paymentpublisher.IPaymentService;

public class Activator implements BundleActivator {

		ServiceReference paymentServiceReference;

	public void start(BundleContext context) throws Exception {
		System.out.println("Start Subscriber Service");
		//Register Consumer Service
		paymentServiceReference = context.getServiceReference(IPaymentService.class.getName());
		@SuppressWarnings("unchecked")
		IPaymentService payment = (IPaymentService)context.getService(paymentServiceReference);	
		displayPayment(payment);
	}
	

	private void displayPayment(IPaymentService payment) {
		
		
		int option;
		String subOption = "y";
		
		Scanner scan = new Scanner(System.in);
		System.out.println("\n\n\n");
		System.out.println("----------Card Payment Details Section ----------\n");
		System.out.println("1  - Add new card details");
		System.out.println("2  - Get all card details");
		System.out.println("3  - Get card details by Id ");
		System.out.println("4  - Update Card by the Id");
		System.out.println("5  - Delete Card by the Id");
		System.out.println("0  - Exit");
		System.out.println("\n--------------------------------------------------");
		System.out.println("\n--------------------------------------------------");
		
		System.out.print("\n\nChoose an option : \n\n");
		
		option = Integer.parseInt(scan.nextLine().trim());
		
		switch(option) {
			case 1:
				payment.addPayment();
				
				while(subOption.equals("y")) {
					System.out.println("\n\nDo you want to Add Another card (y/n)");
					subOption = scan.nextLine().trim();
		
					if(subOption.equals("y")||subOption.equals("Y")) {
						payment.addPayment();
					}
				}
				displayPayment(payment);
				break;
			case 2:
				payment.viewAllPayments();	
				displayPayment(payment);
				break;
			case 3:
				payment.viewPayment();
				displayPayment(payment);
				break;
			case 4:
				payment.updatePayment();
				displayPayment(payment);
				break;
			case 5:
				payment.deletePayment();
				displayPayment(payment);
				break;
			case 0:
				System.out.println("Thank you for using Lanka Mount Castle. Have a pleasant Day!");
				return;
			
			default:
				System.out.println("Incorrect Input. Try Again...");
				displayPayment(payment);
		}
		
		
	
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("stop subsriber!");
		context.ungetService(paymentServiceReference);
	}

}
