package com.example.Financers.userIncome;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserIncomeRepository extends JpaRepository<UserIncomeModel, Long> {

    @Query("SELECT uim.incomeAmount FROM UserIncomeModel uim JOIN uim.userSignUpModel usm WHERE usm.email = ?1")
    List<Double> selectAllIncomeAmountFromUserIncomeModelEmailEquals(String email);

}




