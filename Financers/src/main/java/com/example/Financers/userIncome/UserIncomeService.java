package com.example.Financers.userIncome;

import com.example.Financers.user.UserRepository;
import com.example.Financers.user.UserService;
import com.example.Financers.user.UserSignUpModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserIncomeService {
    private double totalIncome;

    private final UserIncomeRepository userIncomeRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserIncomeService(UserIncomeRepository userIncomeRepository, UserRepository userRepository){
        this.userIncomeRepository = userIncomeRepository;
        this.userRepository = userRepository;
    }

    public boolean addUserIncome(UserIncomeModel userIncomeModel){
        Optional<UserSignUpModel> optional = userRepository.findUserByEmail(UserService.getLoginEmail());
        if(optional.isPresent()){
            UserSignUpModel userSignUpModel = optional.get();
            userIncomeModel.setUserSignUpModel(userSignUpModel);
            userIncomeRepository.save(userIncomeModel);
            return true;
        }
        return false;
    }

    public boolean getUserIncome(){
        double total = 0.0;
        List<Double> income =userIncomeRepository.selectAllIncomeAmountFromUserIncomeModelEmailEquals(UserService.getLoginEmail());
        if(!income.isEmpty()) {
            totalIncome = income.stream().mapToDouble(x -> x).sum();
            return true;
        }
        return false;
    }

    public double getTotalIncome(){
        return this.totalIncome;
    }
}
