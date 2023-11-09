package com.example.Financers.userExpenses;

import com.example.Financers.user.UserRepository;
import com.example.Financers.user.UserService;
import com.example.Financers.user.UserSignUpModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserExpenseService {
    private double totalExpenses;

    private final UserExpenseRepository userExpenseRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserExpenseService(UserExpenseRepository userExpenseRepository, UserRepository userRepository){
        this.userExpenseRepository = userExpenseRepository;
        this.userRepository = userRepository;
    }

    public boolean addUserExpense(UserExpenseModel userExpenseModel){
        Optional<UserSignUpModel> optional = userRepository.findUserByEmail(UserService.getLoginEmail());
        if(optional.isPresent()){
            UserSignUpModel userSignUpModel = optional.get();
            userExpenseModel.setUserSignUpModel(userSignUpModel);
            userExpenseRepository.save(userExpenseModel);
            return true;
        }
        return false;
    }

    public boolean getUserExpense(){
        List<Double> expenses =userExpenseRepository.selectAllExpenseAmountFromUserExpenseModelEmailEquals(UserService.getLoginEmail());
        if(!expenses.isEmpty()) {
            totalExpenses = expenses.stream().mapToDouble(x -> x).sum();
            return true;
        }
        return false;
    }

    public double getTotalExpenses(){
        return this.totalExpenses;
    }
}
