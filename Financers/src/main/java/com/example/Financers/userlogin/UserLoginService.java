package com.example.Financers.userlogin;

import com.example.Financers.helpers.EmailService;
import com.example.Financers.helpers.PasswordHashService;
import com.example.Financers.user.User;
import com.example.Financers.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UserLoginService {
    private static final String RESET_PASSWORD_LINK_FORMAT = "http://localhost:3000/reset-password?email=%s&checksum=%s";
    private final UserLoginRepository userLoginRepository;
    private final UserRepository userRepository;
    private final PasswordHashService passwordHashService;
    private final EmailService emailService;

    @Autowired
    public UserLoginService(final UserLoginRepository userLoginRepository, final UserRepository userRepository,
                             final PasswordHashService passwordHashService, final EmailService emailService) {
        this.userLoginRepository = userLoginRepository;
        this.userRepository = userRepository;
        this.passwordHashService = passwordHashService;
        this.emailService = emailService;
    }

    public Optional<User> login(UserLoginModel userLoginModel) {
        String email = userLoginModel.getEmail();
        Optional<String> saltFound = userLoginRepository.getSalt(email);
        if(saltFound.isPresent()) {
            String password = userLoginModel.getPassword();
            String salt = saltFound.get();
            String hash;
            try {
                hash = passwordHashService.hash(password, salt);
            } catch (NoSuchAlgorithmException e) {
                return Optional.empty();
            }
            return userLoginRepository.getUser(email, hash);
        } else {
            return Optional.empty();
        }
    }

    public boolean signup(UserSignUpModel userSignUpModel) {
        String firstname = userSignUpModel.getFirstname();
        String lastname = userSignUpModel.getLastname();
        String email = userSignUpModel.getEmail();
        String password = userSignUpModel.getPassword();
        if(userLoginRepository.existsByEmail(email)) {
            return false;
        }
        String salt = passwordHashService.generateSalt();
        String hash;
        try {
            hash = passwordHashService.hash(password, salt);
        } catch (NoSuchAlgorithmException e) {
            return false;
        }
        User user = new User();
        user.setFirstName(firstname);
        user.setLastName(lastname);
        userRepository.save(user);
        UserLoginTable userLoginTable = new UserLoginTable();
        userLoginTable.setUser(user);
        userLoginTable.setEmail(email);
        userLoginTable.setSalt(salt);
        userLoginTable.setHash(hash);
        userLoginRepository.save(userLoginTable);
        return true;
    }

    public boolean removeUser(UserLoginModel userLoginModel) {
        Optional<User> userFound = login(userLoginModel);
        if(userFound.isPresent()) {
            User user = userFound.get();
            userRepository.delete(user);
            return true;
        }
        return false;
    }

    public boolean resetPassword(UserLoginModel userLoginModel) {
        String email = userLoginModel.getEmail();
        String newPassword = userLoginModel.getPassword();
        if(!userLoginRepository.existsByEmail(email)) {
            return false;
        }
        Optional<String> saltFound = userLoginRepository.getSalt(email);
        if(saltFound.isEmpty()) {
            return false;
        }
        String salt = saltFound.get();
        String newHash;
        try {
            newHash = passwordHashService.hash(newPassword, salt);
        } catch (NoSuchAlgorithmException e) {
            return false;
        }
        userLoginRepository.resetHash(email, newHash);
        return true;
    }

    public Optional<String> generateChecksum(String email) {
        Optional<String> saltFound = userLoginRepository.getSalt(email);
        if(saltFound.isEmpty()) {
            return Optional.empty();
        }
        String salt = saltFound.get();
        String emailHash;
        try {
            emailHash = passwordHashService.hash(email, salt);
        } catch (NoSuchAlgorithmException e) {
            return Optional.empty();
        }
        return Optional.of(emailHash);
    }

    public boolean sendResetPasswordLink(String email, String checksum) {
        String link = String.format(RESET_PASSWORD_LINK_FORMAT, email, checksum);
        boolean success = emailService.sendResetPasswordLink(email, link);
        return success;
    }
}
