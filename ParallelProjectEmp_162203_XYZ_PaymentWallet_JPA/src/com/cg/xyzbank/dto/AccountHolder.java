package com.cg.xyzbank.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class AccountHolder 
{	@Column(length=20)
	private String FirstName;
	@Column(length=20)
	private String LastName;
	@Column(length=11)
	private String DOB;
	@Column(length=35)
	private String email;
	@Column(length=10)
	private String phoneNo;
	@Id
	@Column(length=10)
	private String userName;
	@Column(length=5)
	private String userPass;
	@OneToOne(cascade=CascadeType.ALL)
	private Account account;
	
	
	
	public AccountHolder() {
		super();
		// TODO Auto-generated constructor stub
	}

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
		return "\n\nFirstName : " + FirstName + "\n LastName : "
				+ LastName + "\n DOB : " + DOB + "\n Email : " + email + "\n PhoneNo : "
				+ phoneNo + "\n userName : " + userName + "\n userPass :" + userPass
				+  account + "";
	}
	

}
