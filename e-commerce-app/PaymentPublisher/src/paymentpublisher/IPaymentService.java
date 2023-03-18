package paymentpublisher;

import java.util.ArrayList;

public interface IPaymentService {
	void addPayment();
	void viewPayment();
	public ArrayList<PaymentModel> viewAllPayments();
	void updatePayment();
	void deletePayment();
}
