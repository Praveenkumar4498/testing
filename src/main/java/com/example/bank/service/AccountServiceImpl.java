package com.example.bank.service;

import java.util.Collections;
import java.util.List;import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.dto.AccountDTO;
import com.example.bank.entity.Account;
import com.example.bank.mapper.AccountMapper;
import com.example.bank.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{
	
	private AccountRepository accountRepository;
	
	@Autowired
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}

	@Override
	public AccountDTO createAccount(AccountDTO accountDTO) {
		Account account = AccountMapper.maptoAccount(accountDTO);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDTO(savedAccount);
	}


	@Override
	public AccountDTO getAccountById(Long Id) {
		Account account = accountRepository
				          .findById(Id)
				          .orElseThrow(() -> new RuntimeException("Account not exist"));
		return AccountMapper.mapToAccountDTO(account);

	}

	@Override
	public AccountDTO deposit(Long Id, double amount) {
		Account account=accountRepository
				.findById(Id)
				.orElseThrow(() -> new RuntimeException("Account not exist"));
		double total=account.getBalance()+amount;
		account.setBalance(total);
		Account savedAccount=accountRepository.save(account);
		
		return AccountMapper.mapToAccountDTO(savedAccount);
		
	}

	@Override
	public AccountDTO withdraw(Long Id, double amount) {
		Account account=accountRepository
				.findById(Id)
				.orElseThrow(() -> new RuntimeException("Account not exist"));
		
		if(account.getBalance()<amount) {
			throw new RuntimeException("Insufficient Balance");
			}
		
		double total=account.getBalance() - amount;
		account.setBalance(total);
		Account savedaccount=accountRepository.save(account);
		return AccountMapper.mapToAccountDTO(savedaccount);
	}

	@Override
	public List<AccountDTO> allAccounts() {
		
		List<Account> accounts =accountRepository.findAll();
		
		return accounts.stream().map((account) -> AccountMapper.mapToAccountDTO(account)).collect(Collectors.toList());
	}

	@Override
	public void deleteAccount(Long Id) {
		 
		Account account=accountRepository
				.findById(Id)
				.orElseThrow(() -> new RuntimeException("Account not exist"));
		
		accountRepository.deleteById(Id);
		
	}

	@Override
	public Account createAccount(Account account) {
		return accountRepository.save(account);
	}
	
	
}
