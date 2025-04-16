package com.example.bank.service;

import java.util.List;

import com.example.bank.dto.AccountDTO;
import com.example.bank.entity.Account;


public interface AccountService  {
	
	AccountDTO createAccount(AccountDTO accountDTO); 
	Account createAccount(Account account);
	AccountDTO getAccountById(Long Id);
	AccountDTO deposit(Long Id, double amount);
	AccountDTO withdraw(Long Id,double amount);
	List<AccountDTO> allAccounts();
	void deleteAccount(Long Id);

}
 