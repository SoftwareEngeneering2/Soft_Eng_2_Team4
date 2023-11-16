package com.example.Financers.debtPayOff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/debt_payoff")
@CrossOrigin(origins = "*")
public class DebtPayOffController {

    private final DebtPayOffService debtPayOffService;
    String warningMessage = "Consider lower payments to save for an emergency fund: ";

    @Autowired
    public DebtPayOffController(DebtPayOffService debtPayOffService){
        this.debtPayOffService = debtPayOffService;
    }

    @PostMapping("/months")
    public ResponseEntity<Object> getMonthToPayOff(@RequestBody MonthToPayOffModel monthToPayOffModel){
        int totalMonths = debtPayOffService.calculateMonthsToPayOff(monthToPayOffModel);
        if(debtPayOffService.getDisposalIncome()==null)
            return ResponseEntity.badRequest().body("Unauthorized User");
        else if(debtPayOffService.getDisposalIncome()<=DebtPayOffService.getMonthlyPayment())
            return ResponseEntity.status(HttpStatus.SEE_OTHER)
                .body(warningMessage + "\n" + "Current Disposal Income: $" + debtPayOffService.getDisposalIncome() + "\n" +
                        "current proposed monthly payment: $" + DebtPayOffService.getMonthlyPayment());
        return ResponseEntity.ok(totalMonths);
    }

    @PostMapping("monthly_payment")
    public ResponseEntity<Object> getMonthlyPayment(@RequestBody MonthlyPaymentModel monthlyPaymentModel){
        if(debtPayOffService.getDisposalIncome()==null)
            return ResponseEntity.badRequest().body("Unauthorized User");
        else if(debtPayOffService.getDisposalIncome()<=debtPayOffService.calculateMonthlyPayment(monthlyPaymentModel))
            return ResponseEntity.status(HttpStatus.SEE_OTHER)
                    .body(warningMessage + "\n" + "Current Disposal Income: $" + debtPayOffService.getDisposalIncome() + "\n" +
                             String.format("Current proposed monthly payment: $%.2f", debtPayOffService.calculateMonthlyPayment(monthlyPaymentModel)));
        return ResponseEntity.ok(String.format("$%.2f",debtPayOffService.calculateMonthlyPayment(monthlyPaymentModel)));
    }

}
