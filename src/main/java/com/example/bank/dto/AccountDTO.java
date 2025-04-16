package com.example.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDTO {
	
	private long id;
	private String accountHoldername;
	private double balance;
	

}
