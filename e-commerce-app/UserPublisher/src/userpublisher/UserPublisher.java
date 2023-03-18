package userpublisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import ecommercedb.EcommerceDb;
import ecommercedb.IEcommerceDb;

public class UserPublisher implements IUserPublisher {
	private Connection connection = null;
	private PreparedStatement stmt = null;
	private ResultSet rs;
	private IEcommerceDb database;
	Scanner input = new Scanner(System.in);
	
	public UserPublisher() {
		database = (IEcommerceDb) new EcommerceDb();
		connection = database.connection();
	}
	
	@Override
	public void registerUser(String role) {
		System.out.println("\nRegister " + (role.equalsIgnoreCase("admin") ? "Admin" : "Client") + " (Press -1 to exit)");
		System.out.println("===============================");

		UserModel newUser = UserModel.getInstance();

		System.out.print("Enter your first name: ");
		newUser.setFirstName(input.nextLine());
		
		if(newUser.getFirstName().equalsIgnoreCase("-1"))
			return;
		
		while(newUser.getFirstName().isEmpty()) {
			System.out.print("First name shuold not be empty!\nEnter your first name: ");
			newUser.setFirstName(input.nextLine());

			if(newUser.getFirstName().equalsIgnoreCase("-1"))
				return;
		}

		System.out.print("Enter your last name: ");
		newUser.setLastName(input.nextLine());

		if(newUser.getLastName().equalsIgnoreCase("-1"))
			return;
		
		while(newUser.getLastName().isEmpty()) {
			System.out.print("Last name shuold not be empty!\nEnter your last name: ");
			newUser.setLastName(input.nextLine());

			if(newUser.getLastName().equalsIgnoreCase("-1"))
				return;
		}
		
		System.out.print("Enter your email: ");
		newUser.setEmail(input.nextLine());

		if(newUser.getEmail().equalsIgnoreCase("-1"))
			return;
		
		while(newUser.getEmail().isEmpty()) {
			System.out.print("Email shuold not be empty!\nEnter your email: ");
			newUser.setEmail(input.nextLine());

			if(newUser.getEmail().equalsIgnoreCase("-1"))
				return;
		}
		
		System.out.print("Enter your password: ");
		newUser.setPassword(input.nextLine());

		if(newUser.getPassword().equalsIgnoreCase("-1"))
			return;
		
		while(newUser.getPassword().isEmpty()) {
			System.out.print("Password shuold not be empty!\nEnter your password: ");
			newUser.setPassword(input.nextLine());

			if(newUser.getPassword().equalsIgnoreCase("-1"))
				return;
		}
		
		String query = "INSERT INTO userdetails(firstName, lastName, email, password, role) VALUES (?, ?, ?, ?, ?)";
		
		try {
			stmt = connection.prepareStatement(query);
			stmt.setString(1, newUser.getFirstName());
			stmt.setString(2, newUser.getLastName());
			stmt.setString(3, newUser.getEmail());
			stmt.setString(4, newUser.getPassword());

			if(role.equalsIgnoreCase("admin"))
				stmt.setString(5, "admin");
			else
				stmt.setString(5, "client");
			
			stmt.execute();
			
			System.out.println("\nUser registered successfully!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public UserModel loginUser() {
		System.out.println("\nLogin User (Press -1 to exit)");
		System.out.println("===============================");

		UserModel loginUser = UserModel.getInstance();

		System.out.print("Enter your email: ");
		loginUser.setEmail(input.nextLine());
		
		if(loginUser.getEmail().equalsIgnoreCase("-1"))
			return null;

		while(!isUserRegistered(loginUser.getEmail())) {
			System.out.print("User not found!\nEnter your email: ");
			loginUser.setEmail(input.nextLine());
			
			if(loginUser.getEmail().equalsIgnoreCase("-1"))
				return null;
		}

		System.out.print("Enter your password: ");
		loginUser.setPassword(input.nextLine());
		
		if(loginUser.getPassword().equalsIgnoreCase("-1"))
			return null;

		while(!validPassword(loginUser.getEmail(), loginUser.getPassword())) {
			System.out.print("Wrong password!\nEnter your password: ");
			loginUser.setPassword(input.nextLine());
			
			if(loginUser.getPassword().equalsIgnoreCase("-1"))
				return null;
		}

		System.out.println("\nUser Validating...");
		System.out.println("User logged in successfully!");
		
		String query = "SELECT * FROM userdetails WHERE email = ?";
		
		try {
			stmt = connection.prepareStatement(query);
			stmt.setString(1, loginUser.getEmail());
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				loginUser.setFirstName(rs.getString("firstName"));
				loginUser.setLastName(rs.getString("lastName"));
				loginUser.setEmail(rs.getString("email"));
				loginUser.setPassword(rs.getString("password"));
				loginUser.setRole(rs.getString("role"));
				loginUser.setBillingAddress(rs.getString("address"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return loginUser;
	}

	@Override
	public void viewUser(UserModel instance) {
		System.out.println("\nUser Details:");
		System.out.println("===============================");
		System.out.println("Name: " + instance.getFirstName() + " " + instance.getLastName() + "\nEmail: " + instance.getEmail() + "\nPassword: " + instance.getPassword() + "\nAddress: " + instance.getBillingAddress());
	}

	@Override
	public void updateUser(UserModel instance) {
		System.out.println("\nUpdate User Details:");
		System.out.println("===============================");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int check = 1;
		String query = "";

		while(check == 1) {
			System.out.println("1  - Update user firstname");
			System.out.println("2  - Update user lastname");
			System.out.println("3  - Update user password");
			System.out.println("4  - Update user address");
			System.out.println("-1 - Back\n");
			System.out.print("Choose your option: ");
			
			int option = input.nextInt();
			input.nextLine();
			
			switch(option) {
				case 1: 
					System.out.print("\nEnter your first name(" + instance.getFirstName() + "): ");
					instance.setFirstName(input.nextLine());
					
					while(instance.getFirstName().isEmpty()) {
						System.out.print("First name shuold not be empty!\nEnter your first name(" + instance.getFirstName() + "): ");
						instance.setFirstName(input.nextLine());
					}
					
					query = "UPDATE userdetails SET firstName = ? WHERE email = ?";
					
					try {
						stmt = connection.prepareStatement(query);
						stmt.setString(1, instance.getFirstName());
						stmt.setString(2, instance.getEmail());
						
						if(stmt.execute())
							System.out.println("Updated successfully!\n");
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
					
				case 2: 
					System.out.print("\nEnter your last name(" + instance.getLastName() + "): ");
					instance.setLastName(input.nextLine());
					
					while(instance.getLastName().isEmpty()) {
						System.out.print("Last name shuold not be empty!\nEnter your last name(" + instance.getLastName() + "): ");
						instance.setLastName(input.nextLine());
					}
					
					query = "UPDATE userdetails SET lastName = ? WHERE email = ?";
					
					try {
						stmt = connection.prepareStatement(query);
						stmt.setString(1, instance.getLastName());
						stmt.setString(2, instance.getEmail());
						
						if(stmt.execute())
							System.out.println("Updated successfully!\n");

					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
					
				case 3: 
					System.out.print("\nEnter your password(" + instance.getPassword() + "): ");
					instance.setPassword(input.nextLine());
					
					while(instance.getPassword().isEmpty()) {
						System.out.print("Password shuold not be empty!\nEnter your password(" + instance.getPassword() + "): ");
						instance.setPassword(input.nextLine());
					}
	
					query = "UPDATE userdetails SET password = ? WHERE email = ?";
					
					try {
						stmt = connection.prepareStatement(query);
						stmt.setString(1, instance.getPassword());
						stmt.setString(2, instance.getEmail());
						
						if(stmt.execute())
							System.out.println("Updated successfully!\n");

					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
					
				case 4: 
					System.out.print("\nEnter your address(" + instance.getBillingAddress() + "): ");
					instance.setBillingAddress(input.nextLine());;
					
					while(instance.getBillingAddress().isEmpty()) {
						System.out.print("Address shuold not be empty!\nEnter your address(" + instance.getBillingAddress() + "): ");
						instance.setBillingAddress(input.nextLine());
					}
	
					query = "UPDATE userdetails SET address = ? WHERE email = ?";
					
					try {
						stmt = connection.prepareStatement(query);
						stmt.setString(1, instance.getBillingAddress());
						stmt.setString(2, instance.getEmail());
						
						if(stmt.execute())
							System.out.println("Updated successfully!\n");

					} catch (SQLException e) {
						e.printStackTrace();
					}
					break;
					
				case -1: 
					check = 0;
					break;
					
				default: System.out.println("Invalid Input!\n");
			}
		}
	}

	@Override
	public void deleteUser(UserModel instance) {
		String query = "DELETE FROM userdetails WHERE email = ?";
		
        try {
			stmt = connection.prepareStatement(query);
			stmt.setString(1, instance.getEmail());
			
			if (stmt.execute())
				System.out.println("User deleted successfully!\n");
			else
				System.out.println("Try again!\n");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isUserRegistered(String email) {
		String query = "SELECT * FROM userdetails WHERE email = ?";
		
		try {
			stmt = connection.prepareStatement(query);
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			
			if(rs.next())
				return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean validPassword(String email, String password) {
		String sql = "SELECT password FROM userdetails WHERE email = ?";
		
        try {
        	stmt = connection.prepareStatement(sql);
			stmt.setString(1, email);
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            String savedPassword = rs.getString("password");
	            return password.equals(savedPassword);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
		return false;
	}

}
