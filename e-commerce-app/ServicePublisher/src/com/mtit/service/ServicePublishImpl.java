package com.mtit.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mtit.dbconnection.DbConnection;
import com.mtit.dbconnection.IDb;

public class ServicePublishImpl implements ServicePublish {
	Scanner input = new Scanner(System.in);
	private String fName = "Sahan";
	private String lName = "Perera";
	private String email = "sahan@gmail.com";
	private String password = "sahan123";
	private String address = "No.199/E1, Pubudu Mawatha, Kopiyawatta, Ragama";
	private String payment = "PayPal";

	private Connection connection = null;
	private Statement statement = null;
	private IDb database;

	public ServicePublishImpl() {
		database = (IDb) new DbConnection();
		connection = database.connection();
	}

	@Override
	public void registerUser() {
		System.out.println("\nRegister User");
		System.out.println("===============================");

		ServiceModel newUser = ServiceModel.getInstance();

		System.out.print("Enter your first name: ");
		fName = input.nextLine();
		newUser.setFirstName(fName);
		
		while(newUser.getFirstName().isEmpty()) {
			System.out.print("First name shuold not be empty!\nEnter your first name: ");
			fName = input.nextLine();
			newUser.setFirstName(fName);
		}

		System.out.print("Enter your last name: ");
		lName = input.nextLine();
		newUser.setLastName(lName);

		while(newUser.getLastName().isEmpty()) {
			System.out.print("Last name shuold not be empty!\nEnter your last name: ");
			lName = input.nextLine();
			newUser.setLastName(lName);
		}
		
		System.out.print("Enter your email: ");
		email = input.nextLine();
		newUser.setEmail(email);

		while(newUser.getEmail().isEmpty()) {
			System.out.print("Email shuold not be empty!\nEnter your email: ");
			email = input.nextLine();
			newUser.setEmail(email);
		}
		
		System.out.print("Enter your password: ");
		password = input.nextLine();
		newUser.setPassword(password);

		while(newUser.getPassword().isEmpty()) {
			System.out.print("Password shuold not be empty!\nEnter your password: ");
			password = input.nextLine();
			newUser.setPassword(password);
		}
		
		String query = "INSERT INTO userdetails(firstName, lastName, email, password) VALUES ('" + fName + "', '" + lName + "', '" + email + "', '" + password + "')";
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("\nUser registered successfully!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ServiceModel loginUser() {
		System.out.println("\nLogin User");
		System.out.println("===============================");

		ServiceModel loginUser = ServiceModel.getInstance();

		System.out.print("Enter your email: ");
		loginUser.setEmail(input.nextLine());

		while(!loginUser.getEmail().contentEquals(email)) {
			System.out.print("User not found!\nEnter your email: ");
			loginUser.setEmail(input.nextLine());
		}

		System.out.print("Enter your password: ");
		loginUser.setPassword(input.nextLine());

		while(!loginUser.getPassword().contentEquals(password)) {
			System.out.print("Wrong password!\nEnter your password: ");
			loginUser.setPassword(input.nextLine());
		}

		System.out.println("\nUser Validating...");
		System.out.println("User logged in successfully!");
		
		loginUser.setFirstName(fName);
		loginUser.setLastName(lName);
		loginUser.setEmail(email);
		loginUser.setPassword(password);
		loginUser.setBillingAddress(address);
		loginUser.setPaymentMethod(payment);
		
		return loginUser;
	}

	@Override
	public void viewUser(ServiceModel instance) {
		System.out.println("\nUser Details:");
		System.out.println("===============================");
		System.out.println("Name: " + instance.getFirstName() + " " + instance.getLastName() + "\nEmail: " + instance.getEmail() + "\nPassword: " + instance.getPassword() + "\nAddress: " + instance.getBillingAddress() + "\nPayment method: " + instance.getPaymentMethod());
	}

	@Override
	public void updateUser(ServiceModel instance) {
		System.out.println("\nUpdate User Details:");
		System.out.println("===============================");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int check = 1;

		while(check == 1) {
			System.out.println("1  - Update user firstname");
			System.out.println("2  - Update user lastname");
			System.out.println("3  - Update user email");
			System.out.println("4  - Update user password");
			System.out.println("5  - Update user address");
			System.out.println("6  - Update user payment"
					);
			System.out.println("-1 - Back\n");
			System.out.print("Choose your option: ");
			
			int option = input.nextInt();
			input.nextLine();
			
			switch(option) {
			case 1: 
				System.out.print("Enter your first name(" + instance.getFirstName() + "): ");
				fName = input.nextLine();
				instance.setFirstName(fName);
				
				while(instance.getFirstName().isEmpty()) {
					System.out.print("First name shuold not be empty!\nEnter your first name(" + instance.getFirstName() + "): ");
					fName = input.nextLine();
					instance.setFirstName(fName);
				}
				System.out.println("Updated successfully!\n");
				break;
			case 2: 
				System.out.print("Enter your last name(" + instance.getLastName() + "): ");
				lName = input.nextLine();
				instance.setLastName(lName);
				
				while(instance.getLastName().isEmpty()) {
					System.out.print("Last name shuold not be empty!\nEnter your last name(" + instance.getLastName() + "): ");
					lName = input.nextLine();
					instance.setLastName(lName);
				}
				System.out.println("Updated successfully!\n");
				break;
			case 3: 
				System.out.print("Enter your email(" + instance.getEmail() + "): ");
				email = input.nextLine();
				instance.setEmail(email);
				
				while(instance.getEmail().isEmpty()) {
					System.out.print("Email shuold not be empty!\nEnter your email(" + instance.getEmail() + "): ");
					email = input.nextLine();
					instance.setEmail(email);
				}
				System.out.println("Updated successfully!\n");
				break;
			case 4: 
				System.out.print("Enter your password(" + instance.getPassword() + "): ");
				password = input.nextLine();
				instance.setPassword(password);
				
				while(instance.getPassword().isEmpty()) {
					System.out.print("Password shuold not be empty!\nEnter your password(" + instance.getPassword() + "): ");
					password = input.nextLine();
					instance.setPassword(password);
				}
				System.out.println("Updated successfully!\n");
				break;
			case 5: 
				System.out.print("Enter your address(" + instance.getBillingAddress() + "): ");
				address = input.nextLine();
				instance.setBillingAddress(address);;
				
				while(instance.getBillingAddress().isEmpty()) {
					System.out.print("Address shuold not be empty!\nEnter your address(" + instance.getBillingAddress() + "): ");
					address = input.nextLine();
					instance.setBillingAddress(address);
				}
				System.out.println("Updated successfully!\n");
				break;
			case 6: 
				System.out.print("Enter your payment method(" + instance.getPaymentMethod() + "): ");
				payment = input.nextLine();
				instance.setPaymentMethod(payment);
				
				while(instance.getPaymentMethod().isEmpty()) {
					System.out.print("Payment method shuold not be empty!\nEnter your payment method(" + instance.getPaymentMethod() + "): ");
					payment = input.nextLine();
					instance.setPaymentMethod(payment);
				}
				System.out.println("Updated successfully!\n");
				break;
			case -1: check = 0;
				break;
			default: System.out.println("Invalid Input!\n");
			}
		}
	}

	@Override
	public void deleteUser() {
		// TODO Auto-generated method stub

	}
}
