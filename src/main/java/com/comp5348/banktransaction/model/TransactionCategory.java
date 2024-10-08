package com.comp5348.banktransaction.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity object for customer database table.
 */
@Getter
@NoArgsConstructor
@Entity
public class TransactionCategory {
    @Id
    @GeneratedValue
    private long id;

    @Setter
    @Column(nullable = false)
    private String categoryName;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Account account;

    @Version
    private int version;

    public TransactionCategory(String categoryName, Account account) {
        this.categoryName = categoryName;
        this.account = account;
    }
}
