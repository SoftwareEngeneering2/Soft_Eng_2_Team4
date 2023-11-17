package com.example.Financers.userlogin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@AllArgsConstructor
public class UserLoginModel {
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    @NotBlank
    private String password;
}
