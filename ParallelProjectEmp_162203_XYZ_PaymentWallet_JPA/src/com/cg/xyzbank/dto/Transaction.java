package com.cg.xyzbank.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Transaction 
{	@Id
	@Column(length=4)
	private int sequenc;
	@Column(length=15)
	private String transId;
	@Column(length=30)
	private String transDate;
	@Column(length=10)
	TransType transType;
	@Column(length=15)
	private double balance;
	private double amount;
	
	

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction( int sequenc, String transId, TransType transType,double amount, double balance) {
		super();
		this.sequenc = sequenc;
		this.transId = transId;
		this.transDate =String.valueOf(LocalDateTime.now());
		this.transType = transType;
		this.amount= amount;
		this.balance = balance;
	}
	
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
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
	public int getSequenc() {
		return sequenc;
		}
	public void setSequenc(int sequenc) {
		this.sequenc = sequenc;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "\nTransaction [sequenc=" + sequenc + ", transId=" + transId
				+ ", transDate=" + transDate + ", transType=" + transType
				+ ", balance=" + balance + ", amount=" + amount + "]";
	}

	
	
	
	
	
}
