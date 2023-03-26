package orderpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

	//Registers the publish Service functions 
	private ServiceRegistration orderRegistration;

	public void start(BundleContext bundleContext) throws Exception {
		IOrderServices orderServices = (IOrderServices) new OrderServices();
		orderRegistration = bundleContext.registerService(IOrderServices.class.getName(),orderServices,null);
		System.out.println("Order Publisher Started!");
	}

	public void stop(BundleContext bundleContext) throws Exception {
		orderRegistration.unregister();
		System.out.println("Order Publisher Stoped!");
	}

}
