package com.mtit.service;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ServiceActivator implements BundleActivator {

	@SuppressWarnings("rawtypes")
	ServiceRegistration publishServiceRegistration;
	

	public void start(BundleContext bundleContext) throws Exception {
		ServicePublish publishService = new ServicePublishImpl();
		publishServiceRegistration = bundleContext.registerService(
				ServicePublish.class.getName(), publishService, null);
			}
	

	public void stop(BundleContext bundleContext) throws Exception {
		publishServiceRegistration.unregister();
	}

}
