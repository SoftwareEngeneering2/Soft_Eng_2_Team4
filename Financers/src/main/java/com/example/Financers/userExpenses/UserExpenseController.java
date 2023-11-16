package com.example.Financers.userExpenses;

import com.example.Financers.userIncome.UserIncomeModel;
import com.example.Financers.userIncome.UserIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/expense")
@CrossOrigin(origins = "*")
public class UserExpenseController {
    private final UserExpenseService userExpenseService;

    @Autowired
    public UserExpenseController(UserExpenseService userExpenseService){
        this.userExpenseService = userExpenseService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUserIncome(@RequestBody UserExpenseModel userExpenseModel) {
        if (!userExpenseService.addUserExpense(userExpenseModel)) {
            return ResponseEntity.badRequest().body("Wrong user");
        }

        return ResponseEntity.ok("User expenses added successfully");
    }


    @GetMapping("/get")
    public ResponseEntity<Object> getUserIncomes(){
        if(!userExpenseService.getUserExpense()){
            return ResponseEntity.badRequest().body("User has no expenses");
        }
        return ResponseEntity.ok(userExpenseService.getTotalExpenses());
    }
}