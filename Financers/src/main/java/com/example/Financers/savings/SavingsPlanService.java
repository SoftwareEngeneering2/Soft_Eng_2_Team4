package com.example.Financers.savings;

import org.springframework.stereotype.Service;

import static java.lang.Math.log;

@Service
public class SavingsPlanService {

    public double getMonthsToSave(MonthsToSaveModel monthsToSaveModel) {
        double target = monthsToSaveModel.getTarget();
        double saved = monthsToSaveModel.getMonthlyAmountSaved();
        double rate = monthsToSaveModel.getApy() / 1200 + 1;
        double num_months = (log(1 - target * (1 - rate) / saved) / log(rate));
        return num_months;
    }

    public double getMonthlyPayment(AmountToSaveModel amountToSaveModel) {
        double target = amountToSaveModel.getTarget();
        double rate = amountToSaveModel.getApy() / 1200 + 1;
        int months = amountToSaveModel.getMonths();
        double monthlyPayment = target * (1 - rate) / (1 - Math.pow(rate, months + 1));
        return monthlyPayment;
    }
}
