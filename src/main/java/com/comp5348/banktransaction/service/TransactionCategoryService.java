package com.comp5348.banktransaction.service;

import com.comp5348.banktransaction.dto.TransactionCategoryDTO;
import com.comp5348.banktransaction.model.Account;
import com.comp5348.banktransaction.model.TransactionCategory;
import com.comp5348.banktransaction.repository.AccountRepository;
import com.comp5348.banktransaction.repository.CustomerRepository;
import com.comp5348.banktransaction.repository.TransactionCategoryRepository;
import com.comp5348.banktransaction.repository.TransactionRecordRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Business logic for creating and managing transaction categories.
 */
@Service
public class TransactionCategoryService {
    private final TransactionCategoryRepository transactionCategoryRepository;
    private final AccountRepository accountRepository;
    private final TransactionRecordRepository transactionRecordRepository;
    private final CustomerRepository customerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public TransactionCategoryService(TransactionCategoryRepository transactionCategoryRepository, AccountRepository accountRepository, TransactionRecordRepository transactionRecordRepository, CustomerRepository customerRepository) {
        this.transactionCategoryRepository = transactionCategoryRepository;
        this.accountRepository = accountRepository;
        this.transactionRecordRepository = transactionRecordRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public TransactionCategoryDTO createTransactionCategory(String categoryName, Long accountId) {
        Account account = accountRepository.getReferenceById(accountId);
        TransactionCategory transactionCategory = new TransactionCategory(categoryName, account);
        transactionCategory = transactionCategoryRepository.save(transactionCategory);
        return new TransactionCategoryDTO(transactionCategory);
    }

}
