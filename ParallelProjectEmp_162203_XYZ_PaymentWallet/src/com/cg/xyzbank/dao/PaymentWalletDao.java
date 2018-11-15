package com.cg.xyzbank.dao;

import java.util.List;
import java.util.Map;

import com.cg.xyzbank.dto.AccountHolder;
import com.cg.xyzbank.dto.Transaction;
import com.cg.xyzbank.exception.PaymentWalletException;

public interface PaymentWalletDao 
{
	public String addNewAccount(AccountHolder accNew);
	public Map<String,AccountHolder> fetchAccountDetails() throws PaymentWalletException;
	public double showBalance(String userName,String pass) throws PaymentWalletException;
	public double deposit(String userName,String pass,double amount) throws PaymentWalletException;
	public double withdraw(String userName,String pass,double amount) throws PaymentWalletException;
	public double fundTrans(String userName,String pass,
			double amount,String accNo) throws PaymentWalletException;
	public List<Transaction> printTrans(String userName,String pass) throws PaymentWalletException;
	
}
