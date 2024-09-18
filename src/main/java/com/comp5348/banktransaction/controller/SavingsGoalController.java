package com.comp5348.banktransaction.controller;

import com.comp5348.banktransaction.dto.SavingsGoalDTO;
import com.comp5348.banktransaction.service.SavingsGoalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer/{customerId}/account/{accountId}/savings_goal")
public class SavingsGoalController {
    private final SavingsGoalService savingsGoalService;

    public SavingsGoalController(SavingsGoalService savingsGoalService) {
        this.savingsGoalService = savingsGoalService;
    }

    // Get all savings goals for an account
    @GetMapping("/all")
    public ResponseEntity<?> getAllSavingsGoals(@PathVariable Long accountId) {
        List<SavingsGoalDTO> resultList = savingsGoalService
                .getAllSavingsGoals(accountId);
        return ResponseEntity.ok(resultList);
    }

    // Create a new savings goal for an account
    @PostMapping("/create")
    public ResponseEntity<?> createSavingsGoal(@PathVariable Long accountId,
                                                  @RequestBody SavingsGoalController.CreateRequest request) {
        SavingsGoalDTO savingsGoal = savingsGoalService
                .createSavingsGoal(request.goalName, request.goalAmount, request.targetDate, accountId);
        return ResponseEntity.ok(savingsGoal);
    }

    // Delete a savings goal for an account
    @PostMapping("/delete")
    public ResponseEntity<?> deleteSavingsGoal(@PathVariable Long accountId,
                                                  @RequestBody SavingsGoalController.DeleteRequest request) {
        savingsGoalService.deleteSavingsGoal(request.goalId, accountId);
        return ResponseEntity.ok().build();
    }

    // Update a savings goal for an account
    @PostMapping("/update")
    public ResponseEntity<?> updateSavingsGoal(@PathVariable Long accountId,
                                                  @RequestBody SavingsGoalController.UpdateRequest request) {
        SavingsGoalDTO savingsGoalDTO = savingsGoalService.updateSavingsGoal(request.goalId, request.goalName, request.targetAmount, request.targetDate, accountId);
        return ResponseEntity.ok(savingsGoalDTO);
    }

    // Update distance of a savings goal for an account
    @PostMapping("/update_distance")
    public ResponseEntity<?> updateSavingsGoalDistance(@PathVariable Long accountId,
                                                  @RequestBody SavingsGoalController.UpdateDistanceRequest request) {
        SavingsGoalDTO savingsGoalDTO = savingsGoalService.updateSavingsGoalDistance(request.goalId, request.updateAmount, accountId);
        return ResponseEntity.ok(savingsGoalDTO);
    }


    public static class CreateRequest {
        public String goalName;
        public double goalAmount;
        public String targetDate;
    }

    public static class DeleteRequest {
        public Long goalId;
    }

    public static class UpdateRequest {
        public Long goalId;
        public String goalName;
        public double targetAmount;
        public String targetDate;
    }

    public static class UpdateDistanceRequest {
        public Long goalId;
        public double updateAmount;
    }
}
