package userpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class UserActivator implements BundleActivator {

	@SuppressWarnings("rawtypes")
	ServiceRegistration publishServiceRegistration;
	
	public void start(BundleContext context) throws Exception {
		IUserPublisher publishService = new UserPublisher();
		publishServiceRegistration = context.registerService(IUserPublisher.class.getName(), publishService, null);
		System.out.println("User Publisher Started!");
	}

	public void stop(BundleContext context) throws Exception {
		publishServiceRegistration.unregister();
		System.out.println("User Publisher Stoped!");
	}
}
