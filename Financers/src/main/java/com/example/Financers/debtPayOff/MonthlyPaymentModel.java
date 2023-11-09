package com.example.Financers.debtPayOff;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MonthlyPaymentModel {
    @NotNull
    @NotBlank
    private Double principal;
    @NotNull
    @NotBlank
    private Double interest;

    @NotNull
    @NotBlank
    private Integer totalMonths;

    public MonthlyPaymentModel() {
    }

    public MonthlyPaymentModel(Double principal, Double interest, Integer totalMonths) {
        this.principal = principal;
        this.interest = interest;
        this.totalMonths = totalMonths;
    }

    public double getPrincipal() {
        return principal;
    }

    public void setPrincipal(Double principal) {
        this.principal = principal;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public int getTotalMonths() {
        return totalMonths;
    }

    public void setTotalMonths(Integer totalMonths) {
        this.totalMonths = totalMonths;
    }

    @Override
    public String toString() {
        return "MonthlyPaymentModel{" +
                "principal=" + principal +
                ", interest=" + interest +
                ", totalMonths=" + totalMonths +
                '}';
    }
}
