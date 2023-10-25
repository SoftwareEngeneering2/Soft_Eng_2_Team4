package com.example.Financers.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table
public class UserSignUpModel {

    @NotNull
    @NotBlank
    private String name;

    @Id
    private String email;

    @NotNull
    @NotBlank
    private String password;

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
