package com.comp5348.banktransaction.service;

import com.comp5348.banktransaction.dto.SavingsGoalDTO;
import com.comp5348.banktransaction.dto.TransactionCategoryDTO;
import com.comp5348.banktransaction.model.Account;
import com.comp5348.banktransaction.model.SavingsGoal;
import com.comp5348.banktransaction.repository.AccountRepository;
import com.comp5348.banktransaction.repository.SavingsGoalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SavingsGoalService {
    private final AccountRepository accountRepository;
    private final SavingsGoalRepository savingsGoalRepository;

    public SavingsGoalService(AccountRepository accountRepository, SavingsGoalRepository savingsGoalRepository) {
        this.accountRepository = accountRepository;
        this.savingsGoalRepository = savingsGoalRepository;
    }

    public List<SavingsGoalDTO> getAllSavingsGoals(Long accountId) {
        Account account = accountRepository.getReferenceById(accountId);
        return savingsGoalRepository.findAllByAccount(account).stream()
                .map(SavingsGoalDTO::new)
                .toList();
    }

    public SavingsGoalDTO createSavingsGoal(String goalName, double goalAmount, String targetDate, Long accountId) {
        Account account = accountRepository.getReferenceById(accountId);
        SavingsGoal savingsGoal = new SavingsGoal(goalName, goalAmount, targetDate, account);
        savingsGoal = savingsGoalRepository.save(savingsGoal);
        return new SavingsGoalDTO(savingsGoal);
    }

    public void deleteSavingsGoal(Long goalId, Long accountId) {
        Account account = accountRepository.getReferenceById(accountId);
        SavingsGoal savingsGoal = savingsGoalRepository.getReferenceById(goalId);
        if (savingsGoal.getAccount().equals(account)) {
            savingsGoalRepository.delete(savingsGoal);
        }
    }

    public SavingsGoalDTO updateSavingsGoal(Long goalId, String goalName, double targetAmount, String targetDate, Long accountId) {
        Account account = accountRepository.getReferenceById(accountId);
        SavingsGoal savingsGoal = savingsGoalRepository.getReferenceById(goalId);
        if (savingsGoal.getAccount().equals(account)) {
            double newDistanceToTarget = savingsGoal.getDistanceToTarget() + targetAmount - savingsGoal.getTargetAmount() > 0 ?
                    savingsGoal.getDistanceToTarget() + targetAmount - savingsGoal.getTargetAmount() : 0;
            savingsGoal.setGoalName(goalName);
            savingsGoal.setTargetAmount(targetAmount);
            savingsGoal.setDistanceToTarget(newDistanceToTarget);
            savingsGoal.setTargetDate(LocalDate.parse(targetDate));
            savingsGoalRepository.save(savingsGoal);
        };
        return new SavingsGoalDTO(savingsGoal);
    }

    public SavingsGoalDTO updateSavingsGoalDistance(Long goalId, double updateAmount, Long accountId) {
        Account account = accountRepository.getReferenceById(accountId);
        SavingsGoal savingsGoal = savingsGoalRepository.getReferenceById(goalId);
        if (savingsGoal.getAccount().equals(account)) {
            double newDistanceToTarget = savingsGoal.getDistanceToTarget() - updateAmount > 0 ?
                    savingsGoal.getDistanceToTarget() - updateAmount : 0;
            savingsGoal.setDistanceToTarget(newDistanceToTarget);
            savingsGoalRepository.save(savingsGoal);
        }
        return new SavingsGoalDTO(savingsGoal);
    }
}
