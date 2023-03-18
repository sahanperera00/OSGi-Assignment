package paymentpublisher;

public class PaymentModel {
	
	private String id;
	private String cardName;
	private String cardNo;
	private String cardExpiryDate;
	private int cvv;
	
	public PaymentModel() {
		this.cardNo = null;
		this.cardName = null;
		this.cardExpiryDate = null;
		this.cvv = 0;
	}
	
	public PaymentModel(String id, String cardName, String cardNo, String cardExpiryDate, int cvv) {
	
		this.id = id;
		this.cardName = cardName;
		this.cardNo = cardNo;
		this.cardExpiryDate = cardExpiryDate;
		this.cvv = cvv;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCardExpiryDate() {
		return cardExpiryDate;
	}
	public void setCardExpiryDate(String cardExpiryDate) {
		this.cardExpiryDate = cardExpiryDate;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	
	
	
}
