package com.comp5348.banktransaction.repository;

import com.comp5348.banktransaction.model.Account;
import com.comp5348.banktransaction.model.SavingsGoal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface SavingsGoalRepository extends JpaRepository<SavingsGoal, Long> {
    Collection<SavingsGoal> findAllByAccount(Account account);
}
