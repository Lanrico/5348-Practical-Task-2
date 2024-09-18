package com.comp5348.banktransaction.dto;

import com.comp5348.banktransaction.model.SavingsGoal;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SavingsGoalDTO {
    String goalName;
    Double targetAmount;
    Double distanceToTarget;
    String targetDate;
    Long accountId;

    public SavingsGoalDTO(SavingsGoal savingsGoal) {
        this.goalName = savingsGoal.getGoalName();
        this.targetAmount = savingsGoal.getTargetAmount();
        this.distanceToTarget = savingsGoal.getDistanceToTarget();
        this.targetDate = savingsGoal.getTargetDate().toString();
        this.accountId = savingsGoal.getAccount().getId();
    }
}
