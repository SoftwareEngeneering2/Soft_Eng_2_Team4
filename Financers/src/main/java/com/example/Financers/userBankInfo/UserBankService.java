package com.example.Financers.userBankInfo;

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
public class UserBankService {
    private final static double initialDeposit = 50;
    private double netSaving;
    private final UserRepository userRepository;
    private final UserIncomeRepository userIncomeRepository;
    private final UserExpenseRepository userExpenseRepository;

    @Autowired
    public UserBankService(UserRepository userRepository, UserExpenseRepository userExpenseRepository, UserIncomeRepository userIncomeRepository){
        this.userRepository = userRepository;
        this.userExpenseRepository = userExpenseRepository;
        this.userIncomeRepository = userIncomeRepository;
    }

    public boolean getBankBalance(){
        Optional<UserSignUpModel> optional = userRepository.findUserByEmail(UserService.getLoginEmail());
        List<Double> expenses =userExpenseRepository.selectAllExpenseAmountFromUserExpenseModelEmailEquals(UserService.getLoginEmail());
        List<Double> income =userIncomeRepository.selectAllIncomeAmountFromUserIncomeModelEmailEquals(UserService.getLoginEmail());
        if(optional.isPresent()){
            netSaving =  initialDeposit + income.stream().mapToDouble(x->x).sum() - expenses.stream().mapToDouble(y->y).sum();
            return true;
        }
        return false;
    }
    public double getNetSaving() {
        return this.netSaving;
    }
}
