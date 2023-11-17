package com.example.Financers.debtPayOff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/debt_payoff")
@CrossOrigin
public class DebtPayOffController {

    private final DebtPayOffService debtPayOffService;

    @Autowired
    public DebtPayOffController(DebtPayOffService debtPayOffService){
        this.debtPayOffService = debtPayOffService;
    }

    @PostMapping("/months")
    public ResponseEntity<Object> getMonthToPayOff(@RequestBody MonthToPayOffModel monthToPayOffModel){
        int totalMonths = debtPayOffService.calculateMonthsToPayOff(monthToPayOffModel);
        return ResponseEntity.ok(totalMonths);
    }

    @PostMapping("/monthly_payment")
    public ResponseEntity<Object> getMonthlyPayment(@RequestBody MonthlyPaymentModel monthlyPaymentModel){
        double monthlyPayment = debtPayOffService.calculateMonthlyPayment(monthlyPaymentModel);
        monthlyPayment = Math.ceil(100 * monthlyPayment) / 100;
        return ResponseEntity.ok(monthlyPayment);
    }
}
