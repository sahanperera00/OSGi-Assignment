package orderpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	//Registers the publish Service functions 
	private ServiceRegistration orderRegistration;

	public void start(BundleContext bundleContext) throws Exception {
		
		System.out.println("Order Service Publisher Started!");
		IOrderServices orderServices = (IOrderServices) new OrderServices();
		
		//Registers the orderService Implementation 
		orderRegistration = bundleContext.registerService(IOrderServices.class.getName(),orderServices,null);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Order Services Publisher Stopped!");
		orderRegistration.unregister();
	}

}
