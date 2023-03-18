package itempublisher;

import java.util.ArrayList;

public interface ItemPublish {

	void addItem();
	public ArrayList<ItemModel> getAllItems();
	void getByID();
	void deleteItem();
	void updateItem();
	
}
