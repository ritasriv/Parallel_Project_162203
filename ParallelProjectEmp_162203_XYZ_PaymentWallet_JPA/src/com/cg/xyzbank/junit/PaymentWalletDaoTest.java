package com.cg.xyzbank.junit;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.cg.xyzbank.dao.PaymentWalletDao;
import com.cg.xyzbank.dao.PaymentWalletDaoImpl;
import com.cg.xyzbank.dto.Account;
import com.cg.xyzbank.dto.AccountHolder;
import com.cg.xyzbank.dto.AccountType;
import com.cg.xyzbank.exception.PaymentWalletException;

public class PaymentWalletDaoTest {

	PaymentWalletDao dao=new PaymentWalletDaoImpl();
	@Test
	public void testCreateAccount()
	{
		AccountHolder a=new AccountHolder
				("Shiv","Pal" ,"18/09/1995","shiv.pal@gmail.com","9876543210", 
						"shiv11", "1111","saving",10000);
		Assert.assertNotNull(dao.addNewAccount(a));
	}
	@Test
	public void testShowBalance()
	{	
		try {
			Assert.assertNotNull(dao.showBalance("shiv11", "1111"));
		} catch (PaymentWalletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
}
