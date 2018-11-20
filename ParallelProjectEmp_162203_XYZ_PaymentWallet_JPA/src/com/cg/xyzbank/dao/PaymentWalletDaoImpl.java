package com.cg.xyzbank.dao;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.dialect.TeradataDialect;

import com.cg.xyzbank.dto.AccountHolder;
import com.cg.xyzbank.dto.TransType;
import com.cg.xyzbank.dto.Transaction;
import com.cg.xyzbank.exception.PaymentWalletException;


public class PaymentWalletDaoImpl implements PaymentWalletDao {

	
	private EntityManager entityManager;

	
	public PaymentWalletDaoImpl() {
		entityManager= JPAUtil.getEntityManager();
	}

	@Override
	public String addNewAccount(AccountHolder accNew)
	{
		entityManager.persist(accNew);
		return accNew.getAccount().getAcNo();
	}

	@Override
	public void commitTransaction()
	{
		entityManager.getTransaction().commit();		
	}

	@Override
	public void beginTransaction() 
	{
		entityManager.getTransaction().begin();
	}

	@Override
	public List<AccountHolder> fetchAccountDetails() throws PaymentWalletException
	{
		String strquery="from AccountHolder";
		TypedQuery<AccountHolder> acc=entityManager.createQuery(strquery,AccountHolder.class);
		List<AccountHolder> listAcc=acc.getResultList();
		
		return listAcc;
	}

	@Override
	public double showBalance(String userName, String pass) throws PaymentWalletException {
	
		String strquery="from AccountHolder";
		TypedQuery<AccountHolder> acc=entityManager.createQuery(strquery,AccountHolder.class);
		List<AccountHolder> listAcc=acc.getResultList();
		Iterator<AccountHolder> it=listAcc.iterator();
		while(it.hasNext())
		{
			AccountHolder account = it.next();
			if(account.getUserName().equals(userName) && 
					account.getUserPass().equals(pass))
			{
				AccountHolder balAcc =  entityManager.find(AccountHolder.class, userName);
				return balAcc.getAccount().getBalance();
			}
		}
		commitTransaction();
		throw new PaymentWalletException("Account not found!");
	}

	@Override
	public double deposit(String userName, String pass, double amount) throws PaymentWalletException
	{
		
		entityManager.getTransaction().begin();
		String strquery="from AccountHolder";
		TypedQuery<AccountHolder> acc=entityManager.createQuery(strquery,AccountHolder.class);
		List<AccountHolder> listAcc=acc.getResultList();
		Iterator<AccountHolder> it=listAcc.iterator();
		while(it.hasNext())
		{
			AccountHolder account = it.next();
			if(account.getUserName().equals(userName) && 
			
			account.getUserPass().equals(pass))
			{
				String strquery2 = "from Transaction";
				account.getAccount().setBalance(account.getAccount().getBalance()+amount);
				entityManager.merge(account);
				System.out.println("Values Updated");
				int count=0;
				System.out.println("0");
				TypedQuery<Transaction> transaction=entityManager.createQuery(strquery2,Transaction.class);
				System.out.println("1");
				System.out.println(transaction.getResultList());
				List<Transaction> trans=transaction.getResultList();
				System.out.println("2");
				Iterator<Transaction> i=trans.iterator();
				while(i.hasNext())
				{
					i.next();
					count++;
				}
				Transaction tran = new Transaction
						(count+1,
								String.valueOf((int)(Math.random()*1000000000)),
								TransType.Credit,amount , account.getAccount().getBalance());
				
				account.getAccount().setTransactions(tran);
			
				AccountHolder balance =  entityManager.find(AccountHolder.class, userName);
				double bal= balance.getAccount().getBalance();
				entityManager.getTransaction().commit();
				return bal;
	
			}
		}
		commitTransaction();
		throw new PaymentWalletException("Account not found!");
		
		
	}

	@Override
	public double withdraw(String userName, String pass, double amount) throws PaymentWalletException {
		entityManager.getTransaction().begin();
		String strquery="from AccountHolder";
		TypedQuery<AccountHolder> acc=entityManager.createQuery(strquery,AccountHolder.class);
		List<AccountHolder> listAcc=acc.getResultList();
		Iterator<AccountHolder> it=listAcc.iterator();
		while(it.hasNext())
		{
			AccountHolder account = it.next();
			if(account.getUserName().equals(userName) && 
					account.getUserPass().equals(pass))
			{
				account.getAccount().setBalance(account.getAccount().getBalance()-amount);
				entityManager.merge(account);
				System.out.println("Values Updated");
				int count=0;
				String strquery3 = "from Transaction";
				TypedQuery<Transaction> transaction=entityManager.createQuery(strquery3,Transaction.class);
				List<Transaction> trans=transaction.getResultList();
				Iterator<Transaction> i=trans.iterator();
				while(i.hasNext())
				{
					i.next();
					count++;
				}
				Transaction tran = new Transaction
						(count+1,
								String.valueOf((int)(Math.random()*1000000000)),
								TransType.Debit,amount , account.getAccount().getBalance());
						account.getAccount().setTransactions(tran);

						
						AccountHolder balance =  entityManager.find(AccountHolder.class, userName);
						double bal= balance.getAccount().getBalance();
						entityManager.getTransaction().commit();
						return bal;
	
			}
		}
		commitTransaction();
		throw new PaymentWalletException("Account not found!");
	}

	@Override
	public double fundTrans(String userName, String pass, double amount,
			String accNo) throws PaymentWalletException {
		boolean flag=false;
		
		entityManager.getTransaction().begin();
		String strquery="from AccountHolder";
		TypedQuery<AccountHolder> acc=entityManager.createQuery(strquery,AccountHolder.class);
		List<AccountHolder> listAcc=acc.getResultList();
		Iterator<AccountHolder> it1=listAcc.iterator();
		while(it1.hasNext())
		{
			AccountHolder source = it1.next();
			if(source.getUserName().equals(userName) && 
					source.getUserPass().equals(pass))
			{
				if(source.getAccount().getBalance()>=amount)
				{
					Iterator<AccountHolder> it2=listAcc.iterator();
					while(it2.hasNext())
					{
						AccountHolder target=it2.next();
						if(target.getAccount().getAcNo().equals(accNo))
						{
							source.getAccount().setBalance(source.getAccount().getBalance()-amount);
							target.getAccount().setBalance(target.getAccount().getBalance()+amount);
							int count=0;
							String strquery4="from Transaction";
							TypedQuery<Transaction> transaction=entityManager.createQuery(strquery4,Transaction.class);
							List<Transaction> trans=transaction.getResultList();
							Iterator<Transaction> i=trans.iterator();
							while(i.hasNext())
							{
								i.next();
								count++;
							}
							String transId=String.valueOf((int)(Math.random()*1000000000));
							Transaction transaction1 = new Transaction(count+1,transId	,
											TransType.Debit, amount,source.getAccount().getBalance());
							source.getAccount().setTransactions(transaction1);
							
							Transaction transaction2 = new Transaction(count+2, transId, TransType.Credit, 
														amount, target.getAccount().getBalance());
							target.getAccount().setTransactions(transaction2);
						
							AccountHolder balance =  entityManager.find(AccountHolder.class, userName);
							double bal= balance.getAccount().getBalance();
							entityManager.getTransaction().commit();
							flag=true;
							return bal;
							
						}
					}
				
				
					if(flag==false)
					{
						commitTransaction();
						throw new PaymentWalletException("Target Account not found!");
					}
				}
				else
				{
					commitTransaction();
					throw new PaymentWalletException("Source Insuffient balance!");
				}
			}
		}
		commitTransaction();
		throw new PaymentWalletException("Source Account not found!");
	}

	@Override
	public List<Transaction> printTrans(String userName, String pass) throws PaymentWalletException {
		String strquery="from AccountHolder";
		TypedQuery<AccountHolder> acc=entityManager.createQuery(strquery,AccountHolder.class);
		List<AccountHolder> listAcc=acc.getResultList();
		Iterator<AccountHolder> it=listAcc.iterator();
		while(it.hasNext())
		{
			AccountHolder account = it.next();
			if(account.getUserName().equals(userName) && 
					account.getUserPass().equals(pass))
			{
				
				return account.getAccount().getTransactions();
			}
		}
		commitTransaction();
		throw new PaymentWalletException("Account not found!");
	}
	

}
