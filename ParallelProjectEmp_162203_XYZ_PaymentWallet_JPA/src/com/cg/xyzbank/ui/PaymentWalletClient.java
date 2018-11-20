package com.cg.xyzbank.ui;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.cg.xyzbank.dto.AccountHolder;
import com.cg.xyzbank.dto.Transaction;
import com.cg.xyzbank.exception.PaymentWalletException;
import com.cg.xyzbank.service.PaymentWalletService;
import com.cg.xyzbank.service.PaymentWalletServiceImpl;

public class PaymentWalletClient 
{
	static PaymentWalletService payServ = new PaymentWalletServiceImpl();
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) 
	{
		int choice=0;
		while(true)
		{try{
			System.out.println("\n***************  Welcome to XYZ Bank  ******************");
			System.out.println("________________________________________________________");
			System.out.println("1:Create New Account\t\t2:Show Account Balance");
			System.out.println("3:Deposit \t\t\t4:Withdraw");
			System.out.println("5:Fund Transfer \t\t6:Print Transaction");
			System.out.println("7:Exit    \t \t\t8:Fetch all Accounts  ");
			System.out.println("\nEnter your choice...");
			choice= sc.nextInt();
			switch(choice)
			{
			case 1:createNewAcc();
					break;
			case 2:showBalance();
					break;
			case 3:deposit();
					break;
			case 4:withdraw();
					break;
			case 5:fundTransfer();
					break;
			case 6:printTrans();
					break;
			case 7:System.exit(0);
					break;
			case 8:showAllAccounts();
					break;
			default : System.out.println("Enter the wrong choice."
							+ "\n To Continue, enter existing choice");		
			}
		}catch(InputMismatchException e)
		{
			sc.nextLine();
			continue;
		}
		}
	}
	@SuppressWarnings("unused")
	private static void showAllAccounts() {
		List<AccountHolder> allAcc=null;
		try {
			allAcc = payServ.fetchAccountDetails();
		} catch (PaymentWalletException e) {
			System.out.println(e.getMessage());
		}
		for(AccountHolder listAccount:allAcc)
			System.out.println(listAccount);
		
		
	}
	private static void createNewAcc() 
	{
		boolean flag1=true,
				flag2=true,
				flag3=true,
				flag4=true,
				flag5=true,
				flag6=true,
				flag7=true,
				flag8=true,
				flag9=true;
		String fName=null,
				lName=null,
				userName=null,
				dob=null,
				pass=null,
				email=null,
				phNo=null,
				accType=null;
		double bal=0;
		while(flag1)
		{			
			System.out.println("Enter First name: ");
			fName = sc.next();
			try {
					if(payServ.validateName(fName))
					{
						flag1=false;
					}
				} 
				catch (PaymentWalletException e)
				{	
					System.out.println(e.getMessage());
				}
		}
		while(flag2)
		{
			System.out.println("Enter Last name: ");
			lName = sc.next();
			try {
				if(payServ.validateName(lName))
				{
					flag2=false;
				}
			}
			catch (PaymentWalletException e) 
			{
				System.out.println(e.getMessage());
			}
		}
		while(flag3)
		{
			System.out.println("Enter DOB [dd/MM/yyyy]: ");
			dob = sc.next();
			try {
				if(payServ.validateDOB(dob))
				{
					flag3=false;
				}
			}
			catch (PaymentWalletException e)
			{
				System.out.println(e.getMessage());
			}
		}
		while(flag4)
		{
			System.out.println("Enter Email: ");
			email = sc.next();
			try {
				if(payServ.validateEmail(email))
				{
					flag4=false;
				}
			}
			catch (PaymentWalletException e)
			{
				System.out.println(e.getMessage());
			}
		}
		while(flag5)
		{
			System.out.println("Enter phNo: ");
			phNo = sc.next();
			try {
				if(payServ.validatePhno(phNo))
				{
					flag5=false;
				}
			} 
			catch (PaymentWalletException e) 
			{
				System.out.println(e.getMessage());
			}
		}
		while(flag6)
		{
			System.out.println("Enter Account Type [saving/current]: ");
			accType = sc.next();
			try {
				if(payServ.validateAccType(accType))
				{
					flag6=false;
				}
			}
			catch (PaymentWalletException e)
			{
				System.out.println(e.getMessage());
			}
		}
		while(flag7)
		{
			System.out.println("Enter Opening Balance: ");
			bal = sc.nextDouble();
			try {
				if(payServ.validateBal(bal))
				{
					flag7=false;
				}
			}
			catch (PaymentWalletException e)
			{
				System.out.println(e.getMessage());
			}
		}
		while(flag8)
		{
			
			System.out.println("Enter UserName");
			userName = sc.next();
			try {
				if(payServ.validateUname(userName))
				{
					flag8=false;
				}
			} 
			catch (PaymentWalletException e)
			{
				System.out.println(e.getMessage());
			}
			
		}
		while(flag9)
		{
			
			System.out.println("Enter PIN");
			pass = sc.next();
			try {
				if(payServ.validatePass(pass))
				{
					flag9=false;
				}
			}
			catch (PaymentWalletException e)
			{
				System.out.println(e.getMessage());
			}
		}
		
		AccountHolder accHolder = new AccountHolder
				(fName, lName, dob, email, phNo, userName, pass, accType, bal);
		
		String acno = payServ.addNewAccount(accHolder);
		System.out.println("Account successfully created");
		System.out.println("Your Account No. is "+acno);
		System.out.println("Kindly note for future reference");
	}
	
	//************  Method to Print Transactions  **************
	
	private static void printTrans()
	{
		boolean flag1=true,flag2=true;
		String userName=null,pass=null;
		while(flag1)
		{
			flag1=false;
			System.out.println("Enter UserName");
			userName = sc.next();
		}
		while(flag2)
		{
			flag2=false;
			System.out.println("Enter PIN");
			pass = sc.next();
		}
	 List<Transaction> transList=null;
	try {
		transList = payServ.printTrans(userName, pass);
	} catch (PaymentWalletException e) {
		System.out.println(e.getMessage());
	}
		System.out.println(transList);
	}
	
	//************** Method to Fund Transfer  ****************

	private static void fundTransfer()
	{
		boolean flag1=true,flag2=true,flag3=true,flag4=true;
		String userName=null,pass=null,targetAcc=null;
		double amount=0;
		while(flag1)
		{
			flag1=false;
			System.out.println("Enter UserName: ");
			userName = sc.next();
		}
		while(flag2)
		{
			flag2=false;
			System.out.println("Enter PIN: ");
			pass = sc.next();
		}
		while(flag3)
		{
			flag3=false;
			System.out.println("Enter Amount: ");
			amount = sc.nextDouble();
		}
		while(flag4)
		{
			flag4=false;
			System.out.println("Enter Target Account No: ");
			targetAcc = sc.next();
		}
	 double bal=0;
	try {
		bal = payServ.fundTrans(userName, pass, amount, targetAcc);
		System.out.println("You Account Balance is :"+bal);
	} catch (PaymentWalletException e) {
		System.out.println(e.getMessage());
	}
		
	}
	
	//****************  Method to withdraw  *****************
	
	private static void withdraw() 
	{

		boolean flag1=true,flag2=true,flag3=true;
		String userName=null,pass=null;
		double amount=0;
		while(flag1)
		{
			flag1=false;
			System.out.println("Enter UserName: ");
			userName = sc.next();
		}
		while(flag2)
		{
			flag2=false;
			System.out.println("Enter PIN: ");
			pass = sc.next();
		}
		while(flag3)
		{
			
			System.out.println("Enter Amount: ");
			try{
			amount = sc.nextDouble();
			flag3=false;
			}catch(InputMismatchException e)
			{
				sc.next();
			}
		}
	 double bal=0;
	try {
		bal = payServ.withdraw(userName, pass, amount);
		System.out.println("You Account Balance is :"+bal);
	} catch (PaymentWalletException e) {
		System.out.println(e.getMessage());
	}
		
		
	}
	
	//*******************  Method to deposit  ******************
	
	private static void deposit()
	{

		boolean flag1=true,flag2=true,flag3=true;
		String userName=null,pass=null;
		double amount=0;
		while(flag1)
		{
			flag1=false;
			System.out.println("Enter UserName: ");
			userName = sc.next();
		}
		while(flag2)
		{
			flag2=false;
			System.out.println("Enter PIN: ");
			pass = sc.next();
		}
		while(flag3)
		{
			flag3=false;
			System.out.println("Enter Amount: ");
			amount = sc.nextDouble();
		}
	 double bal=0;
	try {
		bal = payServ.deposit(userName, pass, amount);
		System.out.println("You Account Balance is :"+bal);
	} catch (PaymentWalletException e) {
		System.out.println(e.getMessage());
	}
		
		
	}
	
	//************  Method to Show Balance **************	  
	
	private static void showBalance()
	{
		boolean flag1=true,flag2=true;
		String userName=null,pass=null;
		while(flag1)
		{
			flag1=false;
			System.out.println("Enter UserName");
			userName = sc.next();
		}
		while(flag2)
		{
			flag2=false;
			System.out.println("Enter PIN");
			pass = sc.next();
		}
	 double bal=0;
	try {
		bal = payServ.showBalance(userName, pass);
		System.out.println("You Account Balance is :"+bal);
	} catch (PaymentWalletException e) {
		System.out.println(e.getMessage());
	}
		
	}


}
