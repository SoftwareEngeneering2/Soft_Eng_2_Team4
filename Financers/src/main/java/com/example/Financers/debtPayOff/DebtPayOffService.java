package com.example.Financers.debtPayOff;

import com.example.Financers.user.UserRepository;
import com.example.Financers.user.UserService;
import com.example.Financers.user.UserSignUpModel;
import com.example.Financers.userExpenses.UserExpenseRepository;
import com.example.Financers.userIncome.UserIncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DebtPayOffService {
    private static double monthlyPayment;
    private final UserRepository userRepository;
    private final UserIncomeRepository userIncomeRepository;
    private final UserExpenseRepository userExpenseRepository;

    @Autowired
    public DebtPayOffService(UserRepository userRepository, UserExpenseRepository userExpenseRepository, UserIncomeRepository userIncomeRepository){
        this.userRepository = userRepository;
        this.userExpenseRepository = userExpenseRepository;
        this.userIncomeRepository = userIncomeRepository;
    }

    public int calculateMonthsToPayOff(MonthToPayOffModel monthToPayOffModel){
        monthlyPayment = monthToPayOffModel.getMonthlyPayment();
        double principal = monthToPayOffModel.getPrincipal();
        double interest = monthToPayOffModel.getInterest();
        double monthlyPayment = monthToPayOffModel.getMonthlyPayment();
        int months =0;
        double monthlyInterest = interest/12;

        while(principal >0){
        interest = principal*(monthlyInterest/100);
        principal +=interest;
        principal -=monthlyPayment;
        months++;
        }
    return  months;
    }

    public double calculateMonthlyPayment(MonthlyPaymentModel monthlyPaymentModel) {
        double principal = monthlyPaymentModel.getPrincipal();
        double interest = monthlyPaymentModel.getInterest();
        int totalMonths = monthlyPaymentModel.getTotalMonths();

        double monthlyInterestRate = interest / 100 / 12;
        double numerator = Math.pow(1 + monthlyInterestRate, totalMonths) * monthlyInterestRate;
        double denominator = Math.pow(1 + monthlyInterestRate, totalMonths) - 1;
        return principal * (numerator / denominator);
    }

    public Double getDisposalIncome(){
        double monthlyDisposal = 0.0;
        Optional<UserSignUpModel> optional = userRepository.findUserByEmail(UserService.getLoginEmail());
        List<Double> expenses =userExpenseRepository.selectAllExpenseAmountFromUserExpenseModelEmailEquals(UserService.getLoginEmail());
        List<Double> income =userIncomeRepository.selectAllIncomeAmountFromUserIncomeModelEmailEquals(UserService.getLoginEmail());
        if(optional.isPresent()) {
            monthlyDisposal = income.stream().mapToDouble(x->x).sum()-expenses.stream().mapToDouble(x->x).sum();
            return monthlyDisposal;
        }else
            return null;
    }

    public static double getMonthlyPayment() {
        return monthlyPayment;
    }
}
