package itempublisher;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ItemActivator implements BundleActivator {

	private ServiceRegistration publishServiceRegistration;
	
	@Override
	public void start(BundleContext context) throws Exception {
		
		System.out.println("Item Publisher started");
		ItemPublish publisherService = new ItemPublishImpl();
		publishServiceRegistration = context.registerService(ItemPublish.class.getName(), publisherService, null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("publisher stop");
		
		publishServiceRegistration.unregister();
		
	}
	
	

}