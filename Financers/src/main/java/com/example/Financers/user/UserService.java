package com.example.Financers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(UserLoginModel userLoginModel) {
        Optional<UserSignUpModel> model = userRepository.findUserByEmail(userLoginModel.getEmail());
        if(model.isEmpty())
            return false;
        else{
            UserSignUpModel userLogin = model.get();
            return userLoginModel.getPassword().equals(userLogin.getPassword());
        }

    }


    public boolean signup(UserSignUpModel userSignUpModel) {
        Optional<UserSignUpModel> optionalUserSignUpModel = userRepository.findUserByEmail(userSignUpModel.getEmail());
        if (optionalUserSignUpModel.isPresent())
            return false;

        userRepository.save(userSignUpModel);
        return true;
    }

}
