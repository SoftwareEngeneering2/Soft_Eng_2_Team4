package com.example.Financers.debtPayOff;

import org.springframework.stereotype.Service;

@Service
public class DebtPayOffService {

    public int calculateMonthsToPayOff(MonthToPayOffModel monthToPayOffModel) {
        double principal = monthToPayOffModel.getPrincipal();
        double interest = monthToPayOffModel.getInterest();
        double monthlyPayment = monthToPayOffModel.getMonthlyPayment();
        int months =0;
        double monthlyInterest = interest / 1200;

        if (monthlyPayment < principal * monthlyInterest) {
            return -1;
        }

        while (principal > 0) {
            interest = principal * monthlyInterest;
            principal += interest;
            principal -= monthlyPayment;
            months++;
        }
        return months;
    }

    public double calculateMonthlyPayment(MonthlyPaymentModel monthlyPaymentModel) {
        double principal = monthlyPaymentModel.getPrincipal();
        double interest = monthlyPaymentModel.getInterest();
        int totalMonths = monthlyPaymentModel.getTotalMonths();

        double monthlyInterestRate = interest / 1200;
        double numerator = Math.pow(1 + monthlyInterestRate, totalMonths) * monthlyInterestRate;
        double denominator = Math.pow(1 + monthlyInterestRate, totalMonths) - 1;
        return principal * (numerator / denominator);
    }
}
