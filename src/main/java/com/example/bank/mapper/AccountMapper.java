package com.example.bank.mapper;

import com.example.bank.dto.AccountDTO;
import com.example.bank.entity.Account;

public class AccountMapper {
	
	public static Account maptoAccount(AccountDTO accountDTO) {
		
		Account account=new Account(
				accountDTO.getId(),
				accountDTO.getAccountHoldername(),
				accountDTO.getBalance()
				);
		return account;
	}
	
	public static AccountDTO mapToAccountDTO(Account account) {
		AccountDTO accountDTO=new AccountDTO(
				account.getId(),
				account.getAccountHolderName(),
				account.getBalance()
				);
		return accountDTO;
		
	}

}
