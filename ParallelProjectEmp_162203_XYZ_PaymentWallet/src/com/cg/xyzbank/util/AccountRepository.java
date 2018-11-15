package com.cg.xyzbank.util;

import java.util.HashMap;
import java.util.Map;

import com.cg.xyzbank.dto.AccountHolder;

public class AccountRepository
{
	static Map<String,AccountHolder> accounts=new HashMap<String, AccountHolder>();
	
	static
	{
		AccountHolder acc1 = new AccountHolder
		("Ritambhara", "Srivastava", "16/04/1997", "rita@gmail.com",
		"70007656789", "rita", "1234","saving",10000);
		
		AccountHolder acc2 = new AccountHolder
				("Saurabh", "Pandey", "10-10-1996", "ghad", "2317946912",
						"saurabh", "9999", "current", 20000);
		
		accounts.put(acc1.getAccount().getAcNo(),acc1);
		
		accounts.put(acc2.getAccount().getAcNo(),acc2);
	}
	
	
	public static String add(AccountHolder newAcc)	{
		accounts.put(newAcc.getAccount().getAcNo(),newAcc);
		return newAcc.getAccount().getAcNo();
	}
	
	public static Map<String,AccountHolder> fetchAll()
	{
		return accounts;
	}
}
