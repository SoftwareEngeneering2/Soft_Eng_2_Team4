package com.example.Financers.userExpenses;

import com.example.Financers.user.UserSignUpModel;
import jakarta.persistence.*;

@Entity
@Table
public class UserExpenseModel {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;

    private String expenseType;

    private double expenseAmount;
    @ManyToOne(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "userExpense",
            referencedColumnName = "email"
    )
    private UserSignUpModel userSignUpModel;

    public UserExpenseModel() {
    }

    public UserExpenseModel(String expenseType, double expenseAmount) {
        this.expenseType = expenseType;
        this.expenseAmount = expenseAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public UserSignUpModel getUserSignUpModel() {
        return userSignUpModel;
    }

    public void setUserSignUpModel(UserSignUpModel userSignUpModel) {
        this.userSignUpModel = userSignUpModel;
    }

    @Override
    public String toString() {
        return "UserExpenseModel{" +
                "id=" + id +
                ", expenseType='" + expenseType + '\'' +
                ", expenseAmount=" + expenseAmount +
                ", userSignUpModel=" + userSignUpModel +
                '}';
    }
}
