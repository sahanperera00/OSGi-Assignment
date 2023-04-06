package userpublisher;

import java.util.HashMap;

public interface IUserPublisher {
	public void registerUser(String role);
	public HashMap<String, String> loginUser();
	public void viewUser();
	public void updateUser();
	public void deleteUser();
	public boolean isUserRegistered(String email);
	public boolean validPassword(String email, String password);
}
