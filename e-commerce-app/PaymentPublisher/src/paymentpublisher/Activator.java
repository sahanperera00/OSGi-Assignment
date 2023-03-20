package paymentpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

private ServiceRegistration serviceRegistration;
	
	public void start(BundleContext context) throws Exception {
		IPaymentService payment = new PaymentServiceImpl();
		serviceRegistration = context.registerService(IPaymentService.class.getName(), payment, null);
		System.out.println("Payment Publisher Started!");
	}

	public void stop(BundleContext context) throws Exception {
		serviceRegistration.unregister();
		System.out.println("Payment Publisher Stoped!");
	}


}
