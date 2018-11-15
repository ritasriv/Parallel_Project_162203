package com.cg.xyzbank.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cg.xyzbank.dto.AccountHolder;
import com.cg.xyzbank.dto.TransType;
import com.cg.xyzbank.dto.Transaction;
import com.cg.xyzbank.exception.PaymentWalletException;
import com.cg.xyzbank.util.AccountRepository;

public class PaymentWalletDaoImpl implements PaymentWalletDao {

	@Override
	public String addNewAccount(AccountHolder accNew)
	{
		AccountRepository.add(accNew);
		return accNew.getAccount().getAcNo();
	}

	@Override
	public Map<String, AccountHolder> fetchAccountDetails() throws PaymentWalletException {
		Map<String, AccountHolder> allAcc = AccountRepository.fetchAll();
		if(allAcc!=null){
			return AccountRepository.fetchAll();
		}
		else
		{
			throw new PaymentWalletException("No Account data exists yet!");
		}
	}

	@Override
	public double showBalance(String userName, String pass) throws PaymentWalletException {
		Map<String, AccountHolder> accounts = AccountRepository.fetchAll();
		Iterator<Map.Entry<String, AccountHolder>> it = accounts.entrySet().iterator();
		while(it.hasNext())
		{
			AccountHolder account = it.next().getValue();
			if(account.getUserName().equals(userName) && 
					account.getUserPass().equals(pass))
			{
				return account.getAccount().getBalance();
			}
		}
		throw new PaymentWalletException("Account not found!");
	}

	@Override
	public double deposit(String userName, String pass, double amount) throws PaymentWalletException {
		Map<String, AccountHolder> accounts = AccountRepository.fetchAll();
		Iterator<Map.Entry<String, AccountHolder>> it = accounts.entrySet().iterator();
		while(it.hasNext())
		{
			AccountHolder account = it.next().getValue();
			if(account.getUserName().equals(userName) && 
					account.getUserPass().equals(pass))
			{
				account.getAccount().setBalance
				(account.getAccount().getBalance()+amount);
				Transaction transaction = new Transaction
				(String.valueOf((int)(Math.random()*1000000000)),
				TransType.Credit, account.getAccount().getBalance());
				account.getAccount().setTransactions(transaction);
				return account.getAccount().getBalance();
			}
		}
		throw new PaymentWalletException("Account not found!");
	}

	@Override
	public double withdraw(String userName, String pass, double amount) throws PaymentWalletException {
		Map<String, AccountHolder> accounts = AccountRepository.fetchAll();
		Iterator<Map.Entry<String, AccountHolder>> it = accounts.entrySet().iterator();
		while(it.hasNext())
		{
			AccountHolder account = it.next().getValue();
			if(account.getUserName().equals(userName) && 
					account.getUserPass().equals(pass))
			{
				if(account.getAccount().getBalance()>= amount)
				{
					account.getAccount().setBalance
					(account.getAccount().getBalance()-amount);
					Transaction transaction = new Transaction
					(String.valueOf((int)(Math.random()*1000000000)),
							TransType.Debit, account.getAccount().getBalance());
					account.getAccount().setTransactions(transaction);
					return account.getAccount().getBalance();
				}
				else
				{
					throw new PaymentWalletException("Insufficient balance in account!");
				}
			}
		}
		throw new PaymentWalletException("Account not found!");
	}

	@Override
	public double fundTrans(String userName, String pass, double amount,
			String accNo) throws PaymentWalletException {
		boolean flag=false;
		Map<String, AccountHolder> accounts = AccountRepository.fetchAll();
		Iterator<Map.Entry<String, AccountHolder>> it1 = accounts.entrySet().iterator();
		while(it1.hasNext())
		{
			AccountHolder source = it1.next().getValue();
			if(source.getUserName().equals(userName) && 
					source.getUserPass().equals(pass))
			{
				if(source.getAccount().getBalance()>amount)
				{
					Iterator<Map.Entry<String, AccountHolder>> it2 = accounts.entrySet().iterator();
					while(it2.hasNext())
					{
						Map.Entry<String, AccountHolder> target = it2.next();
						if(target.getKey().equals(accNo))
						{
							source.getAccount().setBalance
							(source.getAccount().getBalance()-amount);
							target.getValue().getAccount().setBalance(
								(target.getValue().getAccount().getBalance()+amount));
							String transId = String.valueOf((int)(Math.random()*1000000000));
							Transaction transaction1 = new Transaction
									(transId, TransType.Debit, source.getAccount().getBalance());
									source.getAccount().setTransactions(transaction1);
							Transaction transaction2 = new Transaction
									(transId, TransType.Credit, target.getValue().getAccount().getBalance());
									target.getValue().getAccount().setTransactions(transaction2);									
							flag = true;
							return source.getAccount().getBalance();
						}
					}
					if(flag==false)
					{
						throw new PaymentWalletException("Target Account not found!");
					}
				}
				else
				{
					throw new PaymentWalletException("Source Insuffient balance!");
				}
			}
		}
		throw new PaymentWalletException("Source Account not found!");
	}

	@Override
	public List<Transaction> printTrans(String userName, String pass) throws PaymentWalletException {
		Map<String, AccountHolder> accounts = AccountRepository.fetchAll();
		Iterator<Map.Entry<String, AccountHolder>> it = accounts.entrySet().iterator();
		while(it.hasNext())
		{
			AccountHolder account = it.next().getValue();
			if(account.getUserName().equals(userName) && 
					account.getUserPass().equals(pass))
			{
				return account.getAccount().getTransactions();
			}
		}
		throw new PaymentWalletException("Account not found!");
	}

}
