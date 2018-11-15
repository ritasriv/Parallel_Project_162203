package com.cg.xyzbank.dto;

import java.time.LocalDateTime;

public class Transaction 
{
	
	private String transId;
	private LocalDateTime transDate;
	TransType transType;
	private double balance;
	
	public Transaction(	String transId, TransType transType, double balance) {
		super();
		this.transId = transId;
		this.transDate =LocalDateTime.now();
		this.transType = transType;
		this.balance = balance;
	}
	
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public LocalDateTime getTransDate() {
		return transDate;
	}
	public void setTransDate(LocalDateTime transDate) {
		this.transDate = transDate;
	}
	public TransType getTransType() {
		return transType;
	}
	public void setTransType(TransType transType) {
		this.transType = transType;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "\nTransaction [transId=" + transId + ", transDate=" + transDate
				+ ", transType=" + transType + ", balance=" + balance + "]";
	}
	
	
	
	
	
}
