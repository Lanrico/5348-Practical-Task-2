package com.comp5348.banktransaction.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class SavingsGoal {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String goalName;

    @Column(nullable = false)
    private Double targetAmount;

    @Column(nullable = false)
    private Double distanceToTarget;

    @Column(nullable = false)
    private LocalDate targetDate;

    @ManyToOne
    @JoinColumn
    private Account account;

    @Version
    private int version;

    public SavingsGoal(String goalName, Double targetAmount, String targetDate, Account account) {
        this.goalName = goalName;
        this.targetAmount = targetAmount;
        this.distanceToTarget = targetAmount;
        this.targetDate = LocalDate.parse(targetDate);
        this.account = account;
    }

}
