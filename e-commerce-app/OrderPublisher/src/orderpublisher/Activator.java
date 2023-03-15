package orderpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

		
	ServiceRegistration OrderServiceRegistration;

	public void start(BundleContext context) throws Exception {
		System.out.println("Orders Publisher Service Started");
		//Creating an Order service interface implementation
		IOrderService order = new OrderService();
		
		//Assigning the Service Registration to the Order Service provided.
		OrderServiceRegistration = context.registerService(IOrderService.class.getName(), order, null);
	}

	public void stop(BundleContext context) throws Exception {
		//Stopping the plug in project
		System.out.println("Order Publisher Service Stopped");
		OrderServiceRegistration.unregister();
	}

}
