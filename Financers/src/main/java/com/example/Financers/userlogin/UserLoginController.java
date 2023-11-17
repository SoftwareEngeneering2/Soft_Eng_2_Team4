package com.example.Financers.userlogin;

import com.example.Financers.helpers.EmailService;
import com.example.Financers.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/v2/login")
@CrossOrigin("*")
public class UserLoginController {
    private final UserLoginService userLoginService;

    @Autowired
    public UserLoginController(final UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @PostMapping("/existing")
    public ResponseEntity<Object> login(@RequestBody UserLoginModel userLoginModel) {
        Optional<User> userFound = userLoginService.login(userLoginModel);
        if(userFound.isPresent()) {
            User user = userFound.get();
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.badRequest().body("Incorrect Email/Password");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody UserSignUpModel userSignUpModel) {
        boolean signedUp = userLoginService.signup(userSignUpModel);
        if(signedUp) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body("User Account Creation Failed");
        }
    }

    @PostMapping("/remove")
    public ResponseEntity<Object> deleteAccount(@RequestBody UserLoginModel userLoginModel) {
        boolean deleted = userLoginService.removeUser(userLoginModel);
        if(deleted) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body("User Account Deletion Failed");
        }
    }

    @PostMapping("/requestResetPassword")
    public ResponseEntity<Object> requestResetPasswordLink(@RequestBody String email) {
        Optional<String> checksumFound = userLoginService.generateChecksum(email);
        if (checksumFound.isEmpty()) {
            return ResponseEntity.badRequest().body("Email isn't registered");
        }
        String checksum = checksumFound.get();
        userLoginService.sendResetPasswordLink(email, checksum);
        return ResponseEntity.ok("Password Recovery Email Sent");
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<Object> resetPassword(@RequestBody ResetPasswordModel resetPasswordModel) {
        String email = resetPasswordModel.getUserLoginModel().getEmail();
        Optional<String> trueChecksumFound = userLoginService.generateChecksum(email);
        if (trueChecksumFound.isEmpty() || !trueChecksumFound.get().equals(resetPasswordModel.getChecksum())) {
            return ResponseEntity.badRequest().body("Error");
        }
        boolean success = userLoginService.resetPassword(resetPasswordModel.getUserLoginModel());
        if (success) {
            return ResponseEntity.ok("Password Reset");
        } else {
            return ResponseEntity.badRequest().body("Error");
        }
    }
}
