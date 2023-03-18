package paymentpublisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import ecommercedb.EcommerceDb;
import ecommercedb.IEcommerceDb;


public class PaymentServiceImpl implements IPaymentService{
	
	private Connection connection = null;
	private Statement statement = null;
//	private IPaymentService paymentService = null;
	private ResultSet resultSet;
	private IEcommerceDb ecommerceDb;
	private PreparedStatement preparedStatement;

	
	
	public PaymentServiceImpl() {
		super();
		ecommerceDb =  new EcommerceDb();
		connection = ecommerceDb.connection();
	}
		Scanner sc = new Scanner(System.in);
		
		//add card details
		
		public void addPayment() {
		
		
		PaymentModel pm1 = new PaymentModel();
		
		System.out.println("Enter Name on card:");
		pm1.setCardName(sc.nextLine());
		
		System.out.println("Enter card number:");
		pm1.setCardNo(sc.nextLine());
		
		System.out.println("Enter card expiry date:");
		pm1.setCardExpiryDate(sc.nextLine());
		
		System.out.println("Enter cvv on card:");
		pm1.setCvv(sc.nextInt());
		sc.nextLine();
		
		
		System.out.println("Card details successfully added");
		
		try {
			
			String query = "INSERT INTO paymentmodels(cardName,cardNo,expiryDate,cvv) VALUES(?, ?, ?, ?)";
			
			preparedStatement = connection.prepareStatement(query); 
			
			preparedStatement.setString(1, pm1.getCardName());
			preparedStatement.setString(2, pm1.getCardNo());
			preparedStatement.setString(3, pm1.getCardExpiryDate());
			preparedStatement.setInt(4, pm1.getCvv());
					
			int isSuccess = preparedStatement.executeUpdate();
			
			if(isSuccess > 0) {
				
				System.out.println("Card is successfully saved");
				
			}else {
				
				System.out.println("Error has occurred please try again");
				
			}
			
		}catch(Exception ex) {
			
			System.out.println(ex.getMessage());
		}
		
	}
		
	//VIEW PAYMENT BY NUMBER

	@Override
	public void viewPayment() {
		
		System.out.print("\nPlease enter the Card number you wish  to view:\n");
		int cardNo = sc.nextInt();
		sc.nextLine();
		
		String query = "SELECT * FROM paymentmodels WHERE id = '"+cardNo+"'";
		
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery(query);
			
			while(resultSet.next()) {
				
				System.out.println("============================================Card Details===========================================\n\n"
						+ "===========================================================================================================================");
				
				System.out.printf
				(
					"%20d %20s %20d %30s %20s \n", 
						
					resultSet.getInt("id"),
					resultSet.getString("cardName"),
					resultSet.getInt("cardNo"),
					resultSet.getString("expiryDate"),
					resultSet.getInt("cvv")
				);
			
				System.out.println("===========================================================================================================================\n\n");
				
			}
		}catch(Exception e) {
			System.out.println("Error has been occured please try again");
			System.out.println(e.getMessage());
		}
		
	}
	
//VIEW ALL CARD DETAILS
	public ArrayList<PaymentModel> viewAllPayments() {
		// TODO Auto-generated method stub
		ArrayList<PaymentModel> paymentList = new ArrayList<PaymentModel>();
		try {
			statement = connection.createStatement();
			String selectAll = "SELECT * FROM paymentmodels";
			resultSet = statement.executeQuery(selectAll);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		try {	
			while(resultSet.next()) {
				PaymentModel payment = new PaymentModel();
				
				payment.setId(resultSet.getString("id"));
				payment.setCardName(resultSet.getString("cardName"));
				payment.setCardNo(resultSet.getString("cardNo"));
				payment.setCardExpiryDate(resultSet.getString("expiryDate"));
				payment.setCvv(resultSet.getInt("cvv"));
				
				paymentList.add(payment);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("CardID\tCardName\tCardNo\tExpiryDate\tCVV");
		for(PaymentModel x: paymentList) {
			System.out.println(x.getId() + "\t" + x.getCardName() + "\t" + x.getCardNo() + "\t" + x.getCardExpiryDate() + "\t" + x.getCvv());
			
		}
		System.out.println("\n");
		return paymentList;
	}


	@Override
	public void updatePayment() {
		
		System.out.print("\nPlease enter the Card No you wish to Update:\n");
		ArrayList<PaymentModel> paymentList =this.viewAllPayments();
		
		String cardNo = sc.nextLine();
		boolean status = false;
		while(!status) {
			for(PaymentModel x :paymentList) {
				if(cardNo.equalsIgnoreCase(x.getId())) {
					status = true;
					
					System.out.print("Please enter the new expiry date: ");
					String cardExpiryDate = sc.nextLine();
					String update = "UPDATE paymentmodels SET expiryDate = ? where id = ?";
					
					try {
						preparedStatement = connection.prepareStatement(update);
						preparedStatement.setString(1, cardExpiryDate);
						preparedStatement.setString(2, cardNo);
						preparedStatement.executeUpdate();
						System.out.println("Card Successfully Updated\n\n=============================");
						
						System.out.println("PaymentID:\t" + x.getId());
						System.out.println("Card Name:\t"+ x.getCardName());
						System.out.println("Card No:\t" + x.getCardNo());
						System.out.println("ExpiryDate:\t" + x.getCardExpiryDate());
						System.out.println("CVV:\t\t"+ x.getCvv());
						
						System.out.println("=============================\n\n");
						
					}catch(SQLException e) {
						System.out.println("Error with Order");
						System.out.println(e.getMessage());
					}
					break;
				}else {
					status = false;
				}
			}
			if(status == false) {
				System.out.println("Order number mentioned does not exist. Please try again.\n");
				break;
			}
		}
		

	}


	@Override
	public void deletePayment() {
		
		int paymentId;
		
		System.out.println("please enter payment ID:");
		paymentId = sc.nextInt();
		sc.nextLine();
		
		String query = "DELETE from paymentmodels where id=?";
		try {
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, paymentId);
		int isSuccess = preparedStatement.executeUpdate();
		
		if(isSuccess>0) {
			System.out.println("Card successfully deleted");
		}else {
			System.out.println("Cannot find payment id");
		}
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

}

		
	}



	
	

