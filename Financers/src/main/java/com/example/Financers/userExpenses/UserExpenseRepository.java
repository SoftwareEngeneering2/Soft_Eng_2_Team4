package com.example.Financers.userExpenses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserExpenseRepository extends JpaRepository<UserExpenseModel, Long> {
    @Query("SELECT uim.expenseAmount FROM UserExpenseModel uim JOIN uim.userSignUpModel usm WHERE usm.email = ?1")
    List<Double> selectAllExpenseAmountFromUserExpenseModelEmailEquals(String email);
}
