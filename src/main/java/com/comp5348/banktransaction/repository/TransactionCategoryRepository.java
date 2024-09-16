package com.comp5348.banktransaction.repository;

import com.comp5348.banktransaction.model.Account;
import com.comp5348.banktransaction.model.TransactionCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

/**
 * Data Access Object for transaction_category database table.
 */
public interface TransactionCategoryRepository extends JpaRepository<TransactionCategory, Long> {
    Optional<TransactionCategory> findByIdAndAccount(long id, Account account);

    Collection<TransactionCategory> findAllByAccount(Account account);
}
