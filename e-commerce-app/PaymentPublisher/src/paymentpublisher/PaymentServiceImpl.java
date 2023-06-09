package paymentpublisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;

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
		
		System.out.println("\n====================Add a new card===================\n");
		
		System.out.println("Enter Name on card:");
		pm1.setCardName(sc.nextLine());
		
		System.out.println("Enter card number:");
		pm1.setCardNo(sc.nextLine());
		
		System.out.println("Enter card expiry date:");
		pm1.setCardExpiryDate(sc.nextLine());
		
		System.out.println("Enter cvv on card:");
		pm1.setCvv(sc.nextInt());
		sc.nextLine();
		
		
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
		
		System.out.print("\nPlease enter the card number you wish  to view:\n");
		String cardNo = sc.nextLine();
//		sc.nextLine();
		
		String query = "SELECT * FROM paymentmodels WHERE cardNo = '"+cardNo+"'";
		
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery(query);
			
			while(resultSet.next()) {
				
				System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("\n    Payment ID          Card Name          Card No             Expiry Date         CVV");
				System.out.println("----------------------------------------------------------------------------------------------------------------------------------------\n");
				
				System.out.printf
				(
					"%10s %20s %25s %15s %13d \n", 
						
					resultSet.getString("id"),
					resultSet.getString("cardName"),
					resultSet.getString("cardNo"),
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
	
			
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("\n    Payment ID            Card Name           Card No              Expiry Date            CVV");
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------\n");
			for(PaymentModel x: paymentList) {
				 System.out.printf("%10s %23s %23s %18s %15d\n" ,x.getId(),x.getCardName(), x.getCardNo(), x.getCardExpiryDate(),  x.getCvv());
				 //System.out.println(x.getItemId() + "\t" + x.getItemType() + "\t" + x.getItemName() + "\t" + x.getDescription() + "\t" + x.getPrice() + "\t" + x.getDiscount() + "\t" + ( x.getPrice() -( x.getPrice()* x.getDiscount() *0.01)) );
				 
			}
			System.out.println("\n");
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------------\n");
			
		
			
		return paymentList;
	}


	@Override
	public void updatePayment() {
		
		System.out.print("\nPlease enter the Card No you wish to Update:\n");
		ArrayList<PaymentModel> paymentList =this.viewAllPayments();
		
		
		String cardNo = sc.nextLine().trim();
		boolean status = false;
		while(!status) {
			for(PaymentModel x :paymentList) {
				if(cardNo.equalsIgnoreCase(x.getCardNo())) {
					status = true;
					System.out.print("Please note that only expiry date can be updated!\n");
					System.out.print("Please enter the new card expiry date: ");
					String cardExpiryDate = sc.nextLine();
					String update = "UPDATE paymentmodels SET expiryDate = ? where cardNo = ?";
					
					try {
						preparedStatement = connection.prepareStatement(update);
						preparedStatement.setString(1, cardExpiryDate);
						preparedStatement.setString(2, cardNo);
						preparedStatement.executeUpdate();
						System.out.println("\n\nCard Successfully Updated\n\n==============Updated card details===============");
						
						System.out.println("PaymentID:\t" + x.getId());
						System.out.println("Card Name:\t"+ x.getCardName());
						System.out.println("Card No:\t" + x.getCardNo());
						System.out.println("ExpiryDate:\t" + x.getCardExpiryDate());
						System.out.println("CVV:\t\t"+ x.getCvv());
						
						System.out.println("=============================\n\n");
						
					}catch(SQLException e) {
						System.out.println("Error with Card");
						System.out.println(e.getMessage());
					}
					break;
				}else {
					status = false;
				}
			}
			if(status == false) {
				System.out.println("Card number mentioned does not exist. Please try again.\n");
				break;
			}
		}
		

	}


	@Override
	public void deletePayment() {
		
		int paymentId;
		System.out.println("==============Delete Card=================");
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



	
	

