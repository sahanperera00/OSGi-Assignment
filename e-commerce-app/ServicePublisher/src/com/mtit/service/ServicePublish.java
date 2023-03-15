package com.mtit.service;

public interface ServicePublish {
	public void registerUser();
	public ServiceModel loginUser();
	public void viewUser(ServiceModel instance);
	public void updateUser(ServiceModel instance);
	public void deleteUser();
}
