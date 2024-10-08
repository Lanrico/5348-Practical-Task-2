package com.comp5348.banktransaction.service;

import com.comp5348.banktransaction.dto.AccountDTO;
import com.comp5348.banktransaction.dto.TransactionCategoryDTO;
import com.comp5348.banktransaction.dto.TransactionRecordDTO;
import com.comp5348.banktransaction.model.Account;
import com.comp5348.banktransaction.model.TransactionCategory;
import com.comp5348.banktransaction.model.TransactionRecord;
import com.comp5348.banktransaction.repository.AccountRepository;
import com.comp5348.banktransaction.repository.CustomerRepository;
import com.comp5348.banktransaction.repository.TransactionCategoryRepository;
import com.comp5348.banktransaction.repository.TransactionRecordRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Business logic for creating and managing transaction categories.
 */
@Service
public class TransactionCategoryService {
    private final TransactionCategoryRepository transactionCategoryRepository;
    private final AccountRepository accountRepository;
    private final TransactionRecordRepository transactionRecordRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public TransactionCategoryService(TransactionCategoryRepository transactionCategoryRepository, AccountRepository accountRepository, TransactionRecordRepository transactionRecordRepository, CustomerRepository customerRepository) {
        this.transactionCategoryRepository = transactionCategoryRepository;
        this.accountRepository = accountRepository;
        this.transactionRecordRepository = transactionRecordRepository;
    }

    // Create a new transaction category
    @Transactional
    public TransactionCategoryDTO createTransactionCategory(String categoryName, Long accountId) {
        Account account = accountRepository.getReferenceById(accountId);
        TransactionCategory transactionCategory = new TransactionCategory(categoryName, account);
        transactionCategory = transactionCategoryRepository.save(transactionCategory);
        return new TransactionCategoryDTO(transactionCategory);
    }

    // Assign a transaction category to a transaction record
    @Transactional
    public TransactionRecordDTO assignTransactionCategory(Long transactionRecordId, Long transactionCategoryId) {
        TransactionRecord transactionRecord = transactionRecordRepository.getReferenceById(transactionRecordId);
        TransactionCategory transactionCategory = transactionCategoryRepository.getReferenceById(transactionCategoryId);
        transactionRecord.setTransactionCategory(transactionCategory);
        transactionRecord = transactionRecordRepository.save(transactionRecord);
        return new TransactionRecordDTO(transactionRecord);
    }

    // Delete a transaction category
    @Transactional
    public void deleteTransactionCategory(Long categoryId, Long accountId) {
        Account account = accountRepository.getReferenceById(accountId);
        TransactionCategory transactionCategory = transactionCategoryRepository.findByIdAndAccount(categoryId, account).orElseThrow();
        // Remove all references to this category
        entityManager.createQuery("UPDATE TransactionRecord SET transactionCategory = NULL WHERE transactionCategory = :category")
                .setParameter("category", transactionCategory)
                .executeUpdate();
        // Delete the category from the account
        transactionCategoryRepository.delete(transactionCategory);
        System.out.println("Deleted category: " + transactionCategory.getCategoryName());
    }

    // Edit a transaction category
    @Transactional
    public TransactionCategoryDTO editTransactionCategory(Long categoryId, String categoryName, Long accountId) {
        Account account = accountRepository.getReferenceById(accountId);
        TransactionCategory transactionCategory = transactionCategoryRepository.findByIdAndAccount(categoryId, account).orElseThrow();
        transactionCategory.setCategoryName(categoryName);
        transactionCategory = transactionCategoryRepository.save(transactionCategory);
        return new TransactionCategoryDTO(transactionCategory);
    }

    // Get all transaction categories for an account
    @Transactional
    public List<TransactionCategoryDTO> getAllTransactionCategories(Long accountId) {
        Account account = accountRepository.getReferenceById(accountId);
        return transactionCategoryRepository.findAllByAccount(account).stream()
                .map(TransactionCategoryDTO::new)
                .toList();
    }
}
