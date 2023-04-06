package paymentpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

private ServiceRegistration serviceRegistration;
	
	public void start(BundleContext context) throws Exception {
		System.out.println("Publisher service started");
		IPaymentService payment = new PaymentServiceImpl();
		serviceRegistration = context.registerService(IPaymentService.class.getName(), payment, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Publisher service stopped");
		serviceRegistration.unregister();
	}


}
