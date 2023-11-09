package com.example.Financers.userIncome;


import com.example.Financers.user.UserSignUpModel;
import jakarta.persistence.*;

@Entity(name = "UserIncomeModel")
@Table(name ="userIncomeModel")
public class UserIncomeModel {
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

    private String incomeType;

    private double incomeAmount;
    @ManyToOne(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "userIncome",
            referencedColumnName = "email"
    )
    private UserSignUpModel userSignUpModel;

    public UserIncomeModel() {
    }

    public UserIncomeModel(String incomeType, double incomeAmount) {
        this.incomeType = incomeType;
        this.incomeAmount = incomeAmount;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    public double getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(double incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public UserSignUpModel getUserSignUpModel() {
        return userSignUpModel;
    }

    public void setUserSignUpModel(UserSignUpModel userSignUpModel) {
        this.userSignUpModel = userSignUpModel;
    }

    @Override
    public String toString() {
        return "UserIncomeModel{" +
                "id=" + id +
                ", incomeType='" + incomeType + '\'' +
                ", incomeAmount=" + incomeAmount +
                ", userSignUpModel=" + userSignUpModel +
                '}';
    }
}
