package itempublisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import ecommercedb.EcommerceDb;
import ecommercedb.IEcommerceDb;



public class ItemPublishImpl implements ItemPublish {
	
	Scanner scan = new Scanner(System.in);
	
	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet; 
	private IEcommerceDb ecommerceDb;
	
	
	

	public ItemPublishImpl() {
		super();
		ecommerceDb = new EcommerceDb();
		connection = ecommerceDb.connection();
	
	}

	//Adding an item to the database

	@Override
	public void addItem() {
		
		
		
	
		ItemModel itemModel = new ItemModel();
		String subOption = "y";
		
		
		System.out.println("\n=============Item Details==========\n");
		
		System.out.println("Enter Item  ID : ");
		itemModel.setItemId(scan.nextLine().trim());
		
		
		while(itemModel.getItemId().isEmpty()) {
			System.out.print("Item ID Cannot be empty!\nEnter Item ID: ");
			itemModel.setItemId(scan.nextLine().trim());
		}
		
		
		System.out.println("Enter Item  Type : ");
		itemModel.setItemType(scan.nextLine().trim());
		
		System.out.println("Enter Item  Name : ");
		itemModel.setItemName(scan.nextLine().trim());
		
		while(itemModel.getItemName().isEmpty()) {
			System.out.print("Item name cannot be empty!\n\nPlease Enter Item Name: ");
			itemModel.setItemName(scan.nextLine().trim());
		}
		
		System.out.println("Enter Description about the item : ");
		itemModel.setDescription(scan.nextLine().trim());
		
		System.out.println("Enter the Item price : ");
		itemModel.setPrice(Double.parseDouble(scan.nextLine()));
		
		
		System.out.println("Any discount for the item (y/n) ?");
		subOption = scan.nextLine().trim();

			if(subOption.equals("y")||subOption.equals("Y")) {
				System.out.println("Enter the discount : ");
				itemModel.setDiscount(Integer.parseInt(scan.nextLine()));
				
			}
			else {
				System.out.println("No change in the price");
			}
			
	
		
		System.out.println("\nItem details added successfully!!!\n");
		
		System.out.println("\n================================\n");
		
		try{
		
			String insertItem = "INSERT INTO itemdets(ItemId, ItemType, ItemName, description, discount, price)VALUES(?, ?, ?, ?, ?, ?)  " ;
			
			preparedStatement = connection.prepareStatement(insertItem);
			
			preparedStatement.setString(1, itemModel.getItemId());
			preparedStatement.setString(2, itemModel.getItemType());
			preparedStatement.setString(3, itemModel.getItemName());
			preparedStatement.setString(4, itemModel.getDescription());
			preparedStatement.setDouble(5, itemModel.getPrice());
			preparedStatement.setInt(6, itemModel.getDiscount());
			
			int isSuccess = preparedStatement.executeUpdate();
			
			if(isSuccess > 0) {
				
				System.out.println("Item details are succesfully saved!!!");
			}else {
				
				System.out.println("There is an error occured while adding the item. Try Again!!");
			}
			
			
		
		}catch(Exception e){
			
			System.out.println("CustomerSaveError : " + e.getMessage());
			
		}
			
	}

	//Getting all item details from the database
	@Override
	public ArrayList<ItemModel> getAllItems() {
		// TODO Auto-generated method stub
		ArrayList<ItemModel> itemList = new ArrayList<ItemModel>();
		try {
			statement = connection.createStatement();
			String selectAll = "SELECT * FROM itemdets";
			resultSet = statement.executeQuery(selectAll);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		try {	
			while(resultSet.next()) {
				ItemModel item = new ItemModel();
				
				item.setItemId(resultSet.getString("ItemId"));
				item.setItemType(resultSet.getString("ItemType"));
				item.setItemName(resultSet.getString("ItemName"));
				item.setDescription(resultSet.getString("description"));
				item.setPrice(resultSet.getDouble("price"));
				item.setDiscount(resultSet.getInt("discount"));
				itemList.add(item);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("\n    Item ID             Item Type            Item Name                Description         Price(LKR)   Discount(%)    New Price(LKR) ");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------\n");
		for(ItemModel x: itemList) {
			System.out.printf("%10s %20s %20s %30s %15d      %.2f         %.2f\n" ,x.getItemId(),x.getItemType(), x.getItemName(), x.getDescription(),  x.getDiscount() ,x.getPrice(), (x.getDiscount() -( x.getPrice()* x.getDiscount() *0.01)));
			//System.out.println(x.getItemId() + "\t" + x.getItemType() + "\t" + x.getItemName() + "\t" + x.getDescription() + "\t" + x.getPrice() + "\t" + x.getDiscount() + "\t" + ( x.getPrice() -( x.getPrice()* x.getDiscount() *0.01)) );
			
		}
		System.out.println("\n");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------\n");
		return itemList;
	}
	
	//Getting one item detail from the database

	@Override
	public void getByID() {
		// TODO Auto-generated method stub
		System.out.print("\nPlease enter the Item ID of the Item you wish to view:\n");
		ArrayList<ItemModel> itemList =this.getAllItems();
		
		String ItemId = scan.nextLine().trim();
		boolean status = false;
		while(!status) {
			for(ItemModel x :itemList) {
				if(ItemId.equalsIgnoreCase(x.getItemId())) {
					status = true;
					
					String view = "SELECT * FROM itemdets WHERE ItemId = ?";
					
					try {
						preparedStatement = connection.prepareStatement(view);
						preparedStatement.setString(1, ItemId);
						
						preparedStatement.executeQuery();
						System.out.println("\nItem Details\n=============================");
						
						System.out.println("Item ID:\t" + x.getItemId());
						System.out.println("Item Type:\t"+ x.getItemType());
						System.out.println("Item Name:\t"+ x.getItemName());
						System.out.println("Item Description:" + x.getDescription());
						System.out.println("Price(LKR):\t" + x.getPrice());
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
				System.out.println("Item number mentioned does not exist. Please try again:\n");
				break;
			}
		}
		
	}
	
	//Deleting an item from the database

	@Override
	public void deleteItem() {
		
		
		
		
		System.out.print("\nEnter the Item ID of the Item You want to Delete : \n");
		ArrayList<ItemModel> itemList =this.getAllItems();
		String ItemId = scan.nextLine().trim();
		
		boolean status = false;
		while(!status) {
			for(ItemModel x :itemList) {
				if(ItemId.equalsIgnoreCase(x.getItemId())) {
					status = true;
					String delete = "DELETE FROM itemdets WHERE ItemId = '" +ItemId+"'";
					
					try {
						statement = connection.createStatement();
						statement.executeUpdate(delete);
						System.out.println("Item Successfully Deleted");
						
					}catch(SQLException e) {
						System.out.println("Error in deleting with the item");
						System.out.println(e.getMessage());
					}
					break;
				}else {
					status = false;
				}
			}
			if(status == false) {
				System.out.println("Item ID mentioned does not exist. Please try again.\n");
				break;
			}
		}
	}


	

	@Override
	public void updateItem() {
		
		String subOption = "y";
		
		System.out.print("\nPlease enter the Item you wish to Update:\n");
		ArrayList<ItemModel> itemlist =this.getAllItems();
		String ItemId = scan.nextLine().trim();
		
		boolean status = false;
		while(!status) {
			for(ItemModel x :itemlist) {
				if(ItemId.equalsIgnoreCase(x.getItemId())) {
					status = true;
					
					System.out.print("Please note that only the price can be changed.");
					System.out.println("Enter the Item price : ");
					
					double price= scan.nextDouble();
					
					String update = "UPDATE itemdets SET price = ? where ItemId = ?";
					
					try {
						preparedStatement = connection.prepareStatement(update);
						preparedStatement.setDouble(1, price);
						preparedStatement.setString(2, ItemId);
						preparedStatement.executeUpdate();
						System.out.println("Item Successfully Updated\n\n=============================");
						
						System.out.println("\nItem Details\n=============================");
						
						System.out.println("Item ID:\t" + x.getItemId());
						System.out.println("Item Type:\t"+ x.getItemType());
						System.out.println("Item Name:\t"+ x.getItemName());
						System.out.println("Item Description:" + x.getDescription());
						System.out.println("Discount(%):\t" + x.getPrice());
						System.out.println("Price(LKR):   \t"+ x.getDiscount());
						System.out.println("=============================\n\n");
						
					}catch(SQLException e) {
						System.out.println("Error with the Item");
						System.out.println(e.getMessage());
					}
					break;
				}else {
					status = false;
				}
			}
			if(status == false) {
				System.out.println("Item ID mentioned does not exist. Please try again.\n");
				break;
			}
		}
		
	}
		
	}


	