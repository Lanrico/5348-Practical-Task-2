package com.comp5348.banktransaction.dto;

import com.comp5348.banktransaction.model.Account;
import com.comp5348.banktransaction.model.TransactionCategory;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object for TransactionCategory.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionCategoryDTO {
    private long id;
    private String categoryName;

    public TransactionCategoryDTO(TransactionCategory transactionCategory) {
        this.id = transactionCategory.getId();
        this.categoryName = transactionCategory.getCategoryName();
    }
}
