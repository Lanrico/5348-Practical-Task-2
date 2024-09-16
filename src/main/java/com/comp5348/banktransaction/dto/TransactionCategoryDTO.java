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
    private AccountDTO account;

    public TransactionCategoryDTO(TransactionCategory entity) {
        this.id = entity.getId();
        this.categoryName = entity.getCategoryName();
        Account account = entity.getAccount();

        if (account != null) {
            this.account = new AccountDTO(account);
        }
    }
}
