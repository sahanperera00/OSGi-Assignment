package orderpublisher;

import java.util.ArrayList;
import java.util.List;

public class ItemModel {
	
	private int itemNo;
	private String itemName;
	private double price;
//	private List<ItemModel> list = new ArrayList<ItemModel>();
	
	
	//Constructor
	public ItemModel(int itemNo, String itemName, double price) {
		this.itemNo = itemNo;
		this.itemName = itemName;
		this.price = price;
	}
	
	//Getters
	public int getItemNo() {
		return itemNo;
	}

	public String getItemName() {
		return itemName;
	}

	public double getPrice() {
		return price;
	}

	//Setters
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public void displayItem() {
		System.out.println("ItemNo: " +this.itemNo + "\nItem name: " + this.itemName + 
				"\nPrice: " + this.price);
	}
	
//	public static void showItems() {
//		for(ItemModel x : list) {
//			System.out.println(x.getItemName() + "\t" + x.getItemNo() + "\t" + x.getPrice());
//		}
//	}

	
}