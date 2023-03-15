package com.mtit.service;

public class ServiceModel {
	private static ServiceModel instance;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String billingAddress;
	private String paymentMethod;
	
	private ServiceModel() {}
	
	public static ServiceModel getInstance() {
		if(instance == null) {
			synchronized(ServiceModel.class) {
				if(instance == null) {
					instance = new ServiceModel();					
				}
			}
		}
		return instance;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	

}
