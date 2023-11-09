package com.example.Financers.user;

import com.example.Financers.userExpenses.UserExpenseModel;
import com.example.Financers.userIncome.UserIncomeModel;
import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Table
public class UserSignUpModel {

    @NotNull
    @NotBlank
    @Column(
            name = "name"
    )
    private String name;

    @Id
    @Column(
            name = "email"
    )
    private String email;

    @NotNull
    @NotBlank
    @Column(
            name = "password"
    )
    private String password;

    @OneToMany(mappedBy = "userSignUpModel", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<UserIncomeModel> userIncomeModels;

    @OneToMany(mappedBy = "userSignUpModel", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<UserExpenseModel> userExpenseModels;


    public UserSignUpModel() {
    }

    public UserSignUpModel(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
