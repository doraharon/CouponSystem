package com.dor.coupons.system.tables;

import org.springframework.data.repository.CrudRepository;
/**
 * <p>This TransactionRepo is the basic way to communicate with the Transaction table in the database.</p>
 * @author Dor aharon
 * @version 1.0
 * @category REPO interface
 */
public interface TransactionRepo extends CrudRepository <Transaction , Long>{

}

