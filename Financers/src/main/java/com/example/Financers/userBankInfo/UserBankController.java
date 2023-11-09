package com.example.Financers.userBankInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/bank_balance")
public class UserBankController {

    private final UserBankService userBankService;
    @Autowired
    public UserBankController(UserBankService userBankService){
        this.userBankService = userBankService;
    }
    @GetMapping
    public ResponseEntity<Object> getBalance(){
        if(!userBankService.getBankBalance()){
            return ResponseEntity.badRequest().body("Unauthorized User");
        }
        return ResponseEntity.ok(userBankService.getNetSaving());
        }

}
