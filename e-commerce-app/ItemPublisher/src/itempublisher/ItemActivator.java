package itempublisher;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ItemActivator implements BundleActivator {

	private ServiceRegistration publishServiceRegistration;
	
	@Override
	public void start(BundleContext context) throws Exception {
		ItemPublish publisherService = new ItemPublishImpl();
		publishServiceRegistration = context.registerService(ItemPublish.class.getName(), publisherService, null);
		System.out.println("Item Publisher Started!");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		publishServiceRegistration.unregister();
		System.out.println("Item Publisher Stoped!");
	}
	
	

}