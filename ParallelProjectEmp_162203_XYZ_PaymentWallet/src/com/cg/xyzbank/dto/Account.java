package com.cg.xyzbank.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Account
{
	
	private String  acNo;
	private AccountType accType;
	private LocalDate accOpenDate;
	private double openBalance;
	private List<Transaction>  transactions;
	private double balance;
	
	

	public Account() {
		super();
		this.acNo = String.valueOf((int)(Math.random()*1000000000));
		this.accType = AccountType.saving;
		this.accOpenDate = LocalDate.now();
		this.openBalance = 0;
		this.transactions = new ArrayList<Transaction>();
	}
	
	public Account( AccountType accType,double openBalance) {
		super();
		this.acNo = String.valueOf((int)(Math.random()*1000000000));
		this.accType = accType;
		this.accOpenDate =  LocalDate.now();
		this.openBalance = openBalance;
		this.balance = openBalance;
		this.transactions = new ArrayList<Transaction>();
	}
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Transaction transaction) {
		this.transactions.add(transaction);
	}

	public String getAcNo() {
		return acNo;
	}
	public void setAcNo(String acNo) {
		this.acNo = acNo;
	}
	
	public AccountType getAccType() {
		return accType;
	}
	public void setAccType(AccountType accType) {
		this.accType = accType;
	}
	public LocalDate getAccOpenDate() {
		return accOpenDate;
	}
	public void setAccOpenDate(LocalDate accOpenDate) {
		this.accOpenDate = accOpenDate;
	}
	public double getOpenBalance() {
		return openBalance;
	}
	public void setOpenBalance(double openBalance) {
		this.openBalance = openBalance;
	}
	
	@Override
	public String toString() {
		return "\n\n Account No=" + acNo + "\n accType=" + accType
				+ "\n accOpenDate=" + accOpenDate + "\n Balance= "+balance +"";
	}  
	
	
	
}
