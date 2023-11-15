package com.example.Financers.user;

import com.example.Financers.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public RedirectView login(@RequestBody UserLoginModel userLoginModel) {
        if (!userService.login(userLoginModel)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email/password is incorrect");
        }

        return new RedirectView("https://www.google.com/"); //change the endpoint, this is just a test
    }

    @PostMapping("/signup")
    public RedirectView signUp(@RequestBody UserSignUpModel userSignUpModel){
        if (!userService.signup(userSignUpModel)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email is already taken");
        }

        return new RedirectView("enter api endpoint here");
    }

    @RequestMapping("/signup_fname={fname}_lname={lname}_email={email}_password={password}")
    public RedirectView signUp(String fname, String lname, String email, String password){
        UserSignUpModel userSignUpModel = new UserSignUpModel(fname, email, password);
        userService.confirmSignup(userSignUpModel);

        return new RedirectView("enter api endpoint here");
    }}
