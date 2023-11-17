package com.example.Financers.savings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/savings_controller")
@CrossOrigin
public class SavingsPlanController {
    private final SavingsPlanService savingsPlanService;

    @Autowired
    public SavingsPlanController(SavingsPlanService savingsPlanService) {
        this.savingsPlanService = savingsPlanService;
    }

    @PostMapping("/months")
    public ResponseEntity<Object> getMonthToPayOff(@RequestBody MonthsToSaveModel monthsToSaveModel){
        int totalMonths = (int)Math.ceil(savingsPlanService.getMonthsToSave(monthsToSaveModel));
        return ResponseEntity.ok(totalMonths);
    }

    @PostMapping("/monthly_payment")
    public ResponseEntity<Object> getMonthlyPayment(@RequestBody AmountToSaveModel amountToSaveModel){
        double amountToSave = savingsPlanService.getMonthlyPayment(amountToSaveModel);
        amountToSave = Math.ceil(amountToSave * 100) / 100;
        return ResponseEntity.ok(amountToSave);
    }
}
