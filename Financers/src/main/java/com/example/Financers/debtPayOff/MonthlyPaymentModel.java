package com.example.Financers.debtPayOff;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MonthlyPaymentModel {
    @NotNull
    @NotBlank
    private Double principal;
    @NotNull
    @NotBlank
    private Double interest; // 5.5% interest rate per year -> interest = 5.5
    @NotNull
    @NotBlank
    private Integer totalMonths;
}
