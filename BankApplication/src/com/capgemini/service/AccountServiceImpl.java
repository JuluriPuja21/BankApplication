package com.capgemini.service;

import com.capgemini.beans.Account;
import com.capgemini.exceptions.*;
import com.capgemini.repo.AccountRepo;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

public class AccountServiceImpl implements AccountService{
	AccountRepo accountRepo;

	public AccountServiceImpl(AccountRepo accountRepo) {
		super();
		this.accountRepo = accountRepo;
	}
	
    @Override
	public Account createAccount(int accountNumber, int amount) throws InsufficientOpeningBalanceException {
		
		if(amount < 500){
			throw new InsufficientOpeningBalanceException();
		}
		
		Account account = new Account();
		account.setAccountNumber(accountNumber); 
		 account.setAmount(amount); 
		 if(accountRepo.save(account))
		 {
			 return account;
		 }
		
		
		return null;
	}
    
    

    public boolean isaccountExist(int accountNumber) throws InvalidAccountNumberException {
        Account accountFound=accountRepo.searchAccount(accountNumber);
		return accountFound == null ? false : true;
		
    }
    
    
    
    	

    @Override
    public int depositAmount(int accountNumber, int amount) throws InvalidAccountNumberException{
    	int updatedAmount = 0;
    		if(isaccountExist(accountNumber)){
    			updatedAmount = accountRepo.updateBalance(amount);
    			updatedAmount = amount + accountRepo.searchAccount(accountNumber).getAmount();
    			
    			
    		}
    		else{
    			throw new InvalidAccountNumberException();
    		}
    
    	   return updatedAmount;
    }	
      
   
    
      
    @Override
    public int  withDrawAmount(int accountNumber, int amount) throws InvalidAccountNumberException,InsufficientBalanceException{
    	int updatedAmount = 0;
	
			if(isaccountExist(accountNumber)){
			
			updatedAmount =  accountRepo.searchAccount(accountNumber).getAmount()- amount;
			if(updatedAmount < 0){
				throw new InsufficientBalanceException();
			}
			
		}
		else{
			throw new InvalidAccountNumberException();
		}
	
		return updatedAmount;
    }
    
    
    
    }
