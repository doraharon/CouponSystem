package com.example.demo.dbdao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.demo.tables.Transaction;
import com.example.demo.tables.TransactionRepo;
@Component
@Scope ("singleton")
public class TransactionDBDAO {
	@Autowired
	TransactionRepo transactionsRepo;

	public void createTransaction(Transaction transaction)
	{
		transactionsRepo.save(transaction);
	}
	
/*	public void getTransactionByName (String clientName)
	{
		transactionsRepo.getTransactionByName(clientName);
	}*/
}
