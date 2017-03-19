import java.util.ArrayList;
import java.util.List;

public class Transaction {
	
	private static final String intent = "sale";
	
	private String payerFirstName;
	
	private String payerLastName;
	
	private String payerEmail;
	
	private String payerPhone;	
	
	private static final String payerPaymentMethod = "paypal";
	
	private String payerAddressStreet;
	
	private String payerAddressState;
	
	private String payerAddressCountry;
	
	private String payerZipcode;	
	
	private double amount;
	
	private static final String currency = "USD";
	
	private List <Item> items = new ArrayList <Item>();
	
	public String getIntent() {
		return intent;
	}

	public String getPayerFirstName() {
		return payerFirstName;
	}

	public void setPayerFirstName(String payerFirstName) {
		this.payerFirstName = payerFirstName;
	}

	public String getPayerLastName() {
		return payerLastName;
	}

	public void setPayerLastName(String payerLastName) {
		this.payerLastName = payerLastName;
	}

	public String getPayerEmail() {
		return payerEmail;
	}

	public void setPayerEmail(String payerEmail) {
		this.payerEmail = payerEmail;
	}

	public String getPayerPhone() {
		return payerPhone;
	}

	public void setPayerPhone(String payerPhone) {
		this.payerPhone = payerPhone;
	}

	public String getPayerPaymentMethod() {
		return payerPaymentMethod;
	}
	
	public String getPayerAddressStreet() {
		return payerAddressStreet;
	}

	public void setPayerAddressStreet(String payerAddressStreet) {
		this.payerAddressStreet = payerAddressStreet;
	}

	public String getPayerAddressState() {
		return payerAddressState;
	}

	public void setPayerAddressState(String payerAddressState) {
		this.payerAddressState = payerAddressState;
	}

	public String getPayerAddressCountry() {
		return payerAddressCountry;
	}

	public void setPayerAddressCountry(String payerAddressCountry) {
		this.payerAddressCountry = payerAddressCountry;
	}

	public String getPayerZipcode() {
		return payerZipcode;
	}

	public void setPayerZipcode(String payerZipcode) {
		this.payerZipcode = payerZipcode;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}
	
	public Transaction() {
	
	}	
}

class Item {
	private long id = -1;
	
	private String name;
	
	private double total_price;
	
	private String currency;
	
	private int quantity;	
	
	protected Item(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}	
	
}
