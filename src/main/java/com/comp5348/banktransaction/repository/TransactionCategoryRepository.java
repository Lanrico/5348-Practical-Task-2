package com.comp5348.banktransaction.repository;

import com.comp5348.banktransaction.model.TransactionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Data Access Object for transaction_category database table.
 */
public interface TransactionCategoryRepository extends JpaRepository<TransactionCategory, Long> {
}
