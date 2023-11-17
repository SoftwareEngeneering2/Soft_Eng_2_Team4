package com.example.Financers.userlogin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class ResetPasswordModel {
    @NotNull
    @NotBlank
    private UserLoginModel userLoginModel;
    @NotNull
    @NotBlank
    private String checksum;
}
