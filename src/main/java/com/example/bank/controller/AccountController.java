package com.example.bank.controller;

 
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.dto.AccountDTO;
import com.example.bank.mapper.AccountMapper;
import com.example.bank.service.AccountService;

@RestController
@RequestMapping("/api/account")
public class AccountController {
	
	public AccountService accountService;

	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	// create Account 
	@PostMapping
	public ResponseEntity<AccountDTO> addAccount(@RequestBody AccountDTO accountDTO) {
		return new ResponseEntity<>(accountService.createAccount(accountDTO), HttpStatus.CREATED);
	}
	//Get Account
	@GetMapping("/{id}")
	public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long Id){
		AccountDTO accountDTO=accountService.getAccountById(Id);
		return ResponseEntity.ok(accountDTO);
		
	}
	
	//deposit
	@PutMapping("{id}/deposit")
	public ResponseEntity<AccountDTO> deposit(@PathVariable Long Id,@RequestBody Map<String, Double> request){
		
		double amount=request.get("amount");
		AccountDTO accountDto= accountService.deposit(Id, amount);
		return ResponseEntity.ok(accountDto);
		
	}
	//withdraw
	@PutMapping("{id}/withdraw")
	public ResponseEntity<AccountDTO> withdraw(@PathVariable Long Id,@RequestBody  Map<String ,Double> request){
		
		double amount=request.get("amount");
		AccountDTO accountDTO= accountService.withdraw(Id,amount);
		
		return ResponseEntity.ok(accountDTO);
	}
	
	//Get all accounts
	@GetMapping
	public ResponseEntity<List<AccountDTO>> allAccounts(){
		
		List<AccountDTO> accounts= accountService.allAccounts();
		return ResponseEntity.ok(accounts);
		
	}
	
	//delete account
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long Id){
		
		accountService.deleteAccount(Id);
		
		return ResponseEntity.ok("Account deleted Successfully");
		
	}
	
	
	

}
