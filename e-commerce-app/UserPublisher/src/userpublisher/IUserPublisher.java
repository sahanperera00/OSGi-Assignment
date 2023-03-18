package userpublisher;

public interface IUserPublisher {
	public void registerUser(String role);
	public UserModel loginUser();
	public void viewUser(UserModel instance);
	public void updateUser(UserModel instance);
	public void deleteUser(UserModel instance);
	public boolean isUserRegistered(String email);
	public boolean validPassword(String email, String password);
}
