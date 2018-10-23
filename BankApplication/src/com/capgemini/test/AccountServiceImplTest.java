package com.capgemini.test;
import com.capgemini.beans.Account;
import com.capgemini.exceptions.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.assertEquals; 
import static org.mockito.Mockito.when; 
import com.capgemini.repo.AccountRepo;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImpl;

public class AccountServiceImplTest {

	/*@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}*/
	@Mock
	AccountRepo accountRepo;
	
	AccountService accountService;
	
	
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this); 
		accountService = new AccountServiceImpl(accountRepo); 
		
	}	
		
		
		 
		 	/* 
		 	 * create account 
		 	 * 1.when the amount is less than 500 then system should throw exception 
		 	 * 2.when the valid info is passed account should be created successfully 
		 	 */ 


@Test(expected=com.capgemini.exceptions.InsufficientOpeningBalanceException.class)
  public void whenTheAmountIsLessThan500SystemShouldThrowException() throws InsufficientOpeningBalanceException
 	{ 
 		accountService.createAccount(101, 400); 
	} 




 	@Test 
  	public void whenTheValidInfoIsPassedAccountShouldBeCreatedSuccessfully() throws InsufficientOpeningBalanceException 
 	{ 
 		Account account =new Account(); 
 		account.setAccountNumber(101); 
  		account.setAmount(5000); 
  		when(accountRepo.save(account)).thenReturn(true); 
  		assertEquals(account, accountService.createAccount(101, 5000)); 
  	} 
		
           /* Deposit amount
            1.Invalid Account Number Exception
            2.when the valid info is passed amount should be deposited successfully*/
 	
 	
 	
 	

@Test(expected=com.capgemini.exceptions.InvalidAccountNumberException.class)
  public void whenTheAccountIsInvalid() throws InvalidAccountNumberException
 	{ 
 		accountService.depositAmount(105, 400); 
	} 
 	
 
		



	@Test 
	public void whenTheValidInfoIsPassedAmountMustBeDepositedSuccessfully() throws InvalidAccountNumberException 
	{ 
		Account account = new Account();
		
		account.setAccountNumber(102); 
		account.setAmount(5000); 
		when(accountRepo.searchAccount(account.getAccountNumber())).thenReturn(account);
		assertEquals(5500, accountService.depositAmount(102, 500)); 
	} 
	

                               /*Withdraw amount
                               1. Invalid Account Number Exception
                               2. InsufficientBalance Exception
                               3. successful withdraw
	                          */
	@Test 
	public void whenTheValidInfoIsPassedAmountMustBeWithDrawnSuccessfully() throws InvalidAccountNumberException,InsufficientBalanceException 
	{ 
		Account account = new Account();
		
		account.setAccountNumber(101); 
		account.setAmount(5000); 
		
		

	
		
		
	    
		when(accountRepo.searchAccount(account.getAccountNumber())).thenReturn(account);
		assertEquals(4500, accountService.withDrawAmount(101, 500)); 
	} 
	
	
	

@Test(expected=com.capgemini.exceptions.InsufficientBalanceException.class)
  public void whenTheBalanceisInsufficient() throws InvalidAccountNumberException, InsufficientBalanceException
 	{ 

	Account account = new Account();
	
	account.setAccountNumber(101); 
	account.setAmount(5000); 
	when(accountRepo.searchAccount(account.getAccountNumber())).thenReturn(account);
	assertEquals(0, accountService.withDrawAmount(101, 6000)); 
	
	
	
	
	
 		
	} 

@Test(expected=com.capgemini.exceptions.InvalidAccountNumberException.class)
  public void whenTheAccountIsInvalidNumber() throws InvalidAccountNumberException, InsufficientBalanceException
 	{ 
 		accountService.withDrawAmount(105, 400); 
	}
	
	
		
	}
	


