package com.comp5348.banktransaction.controller;

import com.comp5348.banktransaction.dto.AccountDTO;
import com.comp5348.banktransaction.dto.TransactionCategoryDTO;
import com.comp5348.banktransaction.dto.TransactionRecordDTO;
import com.comp5348.banktransaction.service.TransactionCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer/{customerId}/account/{accountId}/transaction_record")
public class TransactionCategoryController {
    private final TransactionCategoryService transactionCategoryService;

    @Autowired
    public TransactionCategoryController(TransactionCategoryService transactionCategoryService) {
        this.transactionCategoryService = transactionCategoryService;
    }

    // Create a new transaction category for an account
    @PostMapping("/create")
    public ResponseEntity<?> createTransactionCategory(@PathVariable Long accountId,
                                                      @RequestBody TransactionCategoryController.CreateRequest request) {
        TransactionCategoryDTO transactionCategory = transactionCategoryService
                .createTransactionCategory(request.categoryName, accountId);
        return ResponseEntity.ok(transactionCategory);
    }

    // Assign a transaction category to a transaction record
    // If the transaction record is already assigned to a category, the category will be updated
    @PostMapping("/assign")
    public ResponseEntity<?> assignTransactionCategory(@PathVariable Long accountId,
                                                      @RequestBody TransactionCategoryController.AssignRequest request) {
        TransactionRecordDTO transactionRecord = transactionCategoryService
                .assignTransactionCategory(request.recordId, request.categoryId);
        return ResponseEntity.ok(transactionRecord);
    }

    // Remove the category from all transaction records and then delete the category
    @PostMapping("/delete")
    public ResponseEntity<?> deleteTransactionCategory(@PathVariable Long accountId,
                                                      @RequestBody TransactionCategoryController.DeleteRequest request) {
        transactionCategoryService
                .deleteTransactionCategory(request.categoryId, accountId);
        return ResponseEntity.ok().build();
    }

    public static class CreateRequest {
        public String categoryName;
    }

    public static class AssignRequest {
        public Long recordId;
        public Long categoryId;
    }

    public static class DeleteRequest {
        public Long categoryId;
    }

}
