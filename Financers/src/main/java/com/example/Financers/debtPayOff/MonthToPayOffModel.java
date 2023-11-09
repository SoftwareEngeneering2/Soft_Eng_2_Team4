package com.example.Financers.debtPayOff;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MonthToPayOffModel {
    @NotNull
    @NotBlank
    private Double principal;
    @NotNull
    @NotBlank
    private Double interest;

    @NotNull
    @NotBlank
    private Double monthlyPayment;

    public MonthToPayOffModel() {
    }

    public MonthToPayOffModel(Double principal, Double interest, Double monthlyPayment) {
        this.principal = principal;
        this.interest = interest;
        this.monthlyPayment = monthlyPayment;
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

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(Double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    @Override
    public String toString() {
        return "MonthlyPaymentModel{" +
                "principal=" + principal +
                ", interest=" + interest +
                ", monthlyPayment=" + monthlyPayment +
                '}';
    }
}
