package com.comp5348.banktransaction.controller;

import com.comp5348.banktransaction.dto.TransactionCategoryDTO;
import com.comp5348.banktransaction.dto.TransactionRecordDTO;
import com.comp5348.banktransaction.service.TransactionCategoryService;
import com.comp5348.banktransaction.service.TransactionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer/{fromCustomerId}/account/{accountId}/transaction_record")
public class TransactionCategoryController {
    private final TransactionCategoryService transactionCategoryService;

    @Autowired
    public TransactionCategoryController(TransactionCategoryService transactionCategoryService) {
        this.transactionCategoryService = transactionCategoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTransactionCategory(@PathVariable Long accountId,
                                                      @RequestBody TransactionCategoryController.CreateRequest request) {
        TransactionCategoryDTO transactionCategory = transactionCategoryService
                .createTransactionCategory(request.categoryName, accountId);
        return ResponseEntity.ok(transactionCategory);
    }

    public static class CreateRequest {
        public String categoryName;
    }

}
