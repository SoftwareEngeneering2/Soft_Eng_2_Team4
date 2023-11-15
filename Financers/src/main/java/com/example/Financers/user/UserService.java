package com.example.Financers.user;

import com.example.Financers.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private static String loginEmail;

    private final UserRepository userRepository;
    private final EmailService emailService;

    @Autowired
    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public boolean login(UserLoginModel userLoginModel) {
        Optional<UserSignUpModel> model = userRepository.findUserByEmail(userLoginModel.getEmail());
        if(model.isEmpty())
            return false;
        else{
            UserSignUpModel userLogin = model.get();
            loginEmail = userLogin.getEmail();
            return userLoginModel.getPassword().equals(userLogin.getPassword());
        }

    }


    public boolean signup(UserSignUpModel userSignUpModel) {
        Optional<UserSignUpModel> optionalUserSignUpModel = userRepository.findUserByEmail(userSignUpModel.getEmail());
        if (optionalUserSignUpModel.isPresent())
            return false;

        String link = "";
        emailService.sendEmail(userSignUpModel.getEmail(), link);
        return true;
    }

    public void confirmSignup(UserSignUpModel userSignUpModel) {
        userRepository.save(userSignUpModel);
    }

    public static String getLoginEmail(){
        return loginEmail;
    }

}
