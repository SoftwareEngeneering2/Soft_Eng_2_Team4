package com.example.Financers.userIncome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/v1/income")
public class UserIncomeController {
    private final UserIncomeService userIncomeService;

    @Autowired
    public UserIncomeController(UserIncomeService userIncomeService){
        this.userIncomeService = userIncomeService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUserIncome(@RequestBody UserIncomeModel userIncomeModel) {
        if (!userIncomeService.addUserIncome(userIncomeModel)) {
            return ResponseEntity.badRequest().body("Wrong user");
        }

        return ResponseEntity.ok("User income added successfully");
    }


    @GetMapping("/get")
    public ResponseEntity<Object> getUserIncomes(){
        if(!userIncomeService.getUserIncome()){
            return ResponseEntity.badRequest().body("User has no Income");
        }
        return ResponseEntity.ok(userIncomeService.getTotalIncome());
    }
}
