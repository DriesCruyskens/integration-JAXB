package messages;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "usermessage")
@XmlType(propOrder = { "UUID", "lastname", "firstname", "phonenumber", "GSMnumber", "email", "address", "company", "type", "paymentStatus", "timestamp" })
public class Userstructure {
	private String UUID;
	private String lastname;
	private String firstname;
	private String phonenumber;
	private String GSMnumber;
	private String email;
	private String address;
	private String company;
	private String type;
	private String paymentStatus;
	private String timestamp;
	
	public Userstructure(String uUID, String lastname, String firstname, String phonenumber, String gSMnumber,
			String email, String address, String company, String type, String paymentStatus, String timestamp) {
		super();
		UUID = uUID;
		this.lastname = lastname;
		this.firstname = firstname;
		this.phonenumber = phonenumber;
		GSMnumber = gSMnumber;
		this.email = email;
		this.address = address;
		this.company = company;
		this.type = type;
		this.paymentStatus = paymentStatus;
		this.timestamp = timestamp;
	}
	
	public Userstructure() {
		
	}
	
	public String getUUID() {
		return UUID;
	}
	public void setUUID(String uUID) {
		UUID = uUID;
	}
	@XmlElement(name = "Name")
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	@XmlElement(name = "First-name")
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	@XmlElement(name = "phone-number")
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	@XmlElement(name = "GSM-number")
	public String getGSMnumber() {
		return GSMnumber;
	}
	public void setGSMnumber(String gSMnumber) {
		GSMnumber = gSMnumber;
	}
	@XmlElement(name = "Email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@XmlElement(name = "Address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@XmlElement(name = "Company")
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	@XmlElement(name = "Type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@XmlElement(name = "Payment-status")
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	@XmlElement(name = "Timestamp")
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
