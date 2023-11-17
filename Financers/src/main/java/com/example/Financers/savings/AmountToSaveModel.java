package com.example.Financers.savings;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AmountToSaveModel {
    @NotNull
    @NotBlank
    private Double target;
    @NotNull
    @NotBlank
    private Double apy; // 5.5% interest rate per year -> interest = 5.5
    @NotNull
    @NotBlank
    private Integer months;
}
