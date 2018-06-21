package com.example.demo.tables;

import org.springframework.data.repository.CrudRepository;


public interface TransactionRepo extends CrudRepository <Transaction , Long>{
	
	/*@Query("SELECT TRANSACTION FROM TRANSACTION TRAN WHERE TRAN.ClientName = :Client_Name OR TRAN.ComponentName = :Client_Name")
	ArrayList<Coupon> getTransactionByName (@Param ("Client_Name")String Client_Name);
*/
}

