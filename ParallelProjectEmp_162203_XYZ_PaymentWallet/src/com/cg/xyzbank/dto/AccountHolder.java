package com.cg.xyzbank.dto;

public class AccountHolder 
{
	private String FirstName;
	private String LastName;
	private String DOB;
	private String email;
	private String phoneNo;
	private String userName;
	private String userPass;
	private Account account;
	
	public AccountHolder(String firstName, String lastName, String dOB,
			String email, String phoneNo, String userName, String userPass,
			String accType , double openbal) {
		super();
		FirstName = firstName;
		LastName = lastName;
		DOB = dOB;
		this.email = email;
		this.phoneNo = phoneNo;
		this.userName = userName;
		this.userPass = userPass;
		this.account = new Account(AccountType.valueOf(accType) , openbal);
		
	}
	
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	@Override
	public String toString() {
		return "AccountHolder \n[FirstName=" + FirstName + "\n LastName="
				+ LastName + "\n DOB=" + DOB + "\n email=" + email + "\n phoneNo="
				+ phoneNo + "\n userName=" + userName + "\n userPass=" + userPass
				+ "\n account=" + account + "]";
	}
	

}
