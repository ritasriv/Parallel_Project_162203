package com.cg.xyzbank.service;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.cg.xyzbank.dao.PaymentWalletDao;
import com.cg.xyzbank.dao.PaymentWalletDaoImpl;
import com.cg.xyzbank.dto.AccountHolder;
import com.cg.xyzbank.dto.Transaction;
import com.cg.xyzbank.exception.PaymentWalletException;

public class PaymentWalletServiceImpl implements PaymentWalletService
{
	PaymentWalletDao payDao=new PaymentWalletDaoImpl();
	@Override
	public String addNewAccount(AccountHolder accNew) 
	{	
		payDao.beginTransaction();
		payDao.addNewAccount(accNew);
		payDao.commitTransaction();
		return payDao.addNewAccount(accNew);
	}	

	@Override
	public List<AccountHolder> fetchAccountDetails() throws PaymentWalletException {
		payDao.beginTransaction();
		List<AccountHolder> listAcc = payDao.fetchAccountDetails();
		payDao.commitTransaction();
		return listAcc;
	}

	@Override
	public double showBalance(String userName, String pass) throws PaymentWalletException
	{
		return payDao.showBalance(userName, pass);
	}

	@Override
	public double deposit(String userName, String pass, double amount) throws PaymentWalletException  {	
		
		return payDao.deposit(userName, pass, amount);
	}

	@Override
	public double withdraw(String userName, String pass, double amount) throws PaymentWalletException  {
		return payDao.withdraw(userName, pass, amount);
	}
	
	@Override
	public double fundTrans(String userName, String pass, double amount,
			String accNo) throws PaymentWalletException  {
		return payDao.fundTrans(userName, pass, amount, accNo);
	}

	@Override
	public List<Transaction> printTrans(String userName, String pass) throws PaymentWalletException  {
		return payDao.printTrans(userName, pass);
	}

	@Override
	public boolean validateName(String name) throws PaymentWalletException
	{
		String namePattern="[A-Z][a-z]+";
		if(Pattern.matches(namePattern, name))
		{
			return true;
		}
		else
		{
			throw new PaymentWalletException("Enter valid name ,"
					+ " starting with capital letter"
					+ " and followed by small letters");
		}
		
	}

	@Override
	public boolean validateDOB(String dob) throws PaymentWalletException 
	{
		
		String datePattern=
				"^(0[1-9]|[12][0-9]|3[0-1])/(0[1-9]|1[0-2])/[0-9]{4}$";
		if(Pattern.matches(datePattern, dob))
		{
			return true;
		}
		else
		{
			throw new PaymentWalletException("Enter valid"
					+ " Date in [dd/mm/yyyy] format.");
		}
	}

	@Override
	public boolean validateEmail(String email) throws PaymentWalletException 
	{
		
		String emailPattern="^[A-Za-z0-9+._]+@[a-z]+.(com)$";
		if(Pattern.matches(emailPattern, email))
		{
			return true;
		}
		else
		{
			throw new PaymentWalletException("Enter valid Email, "
					+ "\n(lower or uppercase letter along with . or _ are allowed"
					+ " followed by @ domain Name in lower case . com ");
		}
		
	}

	@Override
	public boolean validatePhno(String phno) throws PaymentWalletException
	{
		
		String patternPhNo= "^([6-9]{1})+([0-9]{9})$";
		if(Pattern.matches(patternPhNo, phno))
		{
			return true;
		}
		else
		{
			throw new PaymentWalletException("Enter valid "
					+ "Phone Number, starting with (6/7/8/9)"
					+ "\nPhone Number must be 10 digits");
		}
	}

	@Override
	public boolean validateUname(String uname) throws PaymentWalletException
	{
		String patternUname="[a-z]+[0-9]{2}+";
		if(Pattern.matches(patternUname, uname))
		{
			return true;
		}
		else
		{
			throw new PaymentWalletException("Enter valid user name, having any no."
					+ " \nof lowercare letters follwed by a two digit no.)");
		}
		
	}

	@Override
	public boolean validateBal(double bal) throws PaymentWalletException 
	{
	
		String patternBal="[0-9]+"+"."+"[0-9]+";
		if(Pattern.matches(patternBal, String.valueOf(bal)))
		{
			return true;
		}
		else
		{
			throw new PaymentWalletException("Enter balance in digits.");
		}
	}

	@Override
	public boolean validatePass(String pass) throws PaymentWalletException 
	{
		String patternPass="[0-9]{4}+";
		if(Pattern.matches(patternPass, pass))
		{
			return true;
		}
		else
		{
			throw new PaymentWalletException("Enter valid Pin of FOUR digits");
		}
	}

	@Override
	public boolean validateAccType(String accType) throws PaymentWalletException 
	{
		if(accType.equals("saving")||accType.equals("current"))
		{
			return true;
		}
		else
		{
			throw new PaymentWalletException("Account type does not exist !!");
		}
	}

}
