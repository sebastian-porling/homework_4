package se.kth.homework_4.model;

import javax.validation.constraints.*;

public class Conversion {

    @NotBlank
    @NotNull
    @Size(min = 3, max = 3)
    private String fromCurrency;

    @NotBlank
    @NotNull
    @Size(min = 3, max = 3)
    private String toCurrency;

    @NotNull
    @PositiveOrZero
    private double amount;

    @NotNull
    @PositiveOrZero
    private double result;

    public Conversion(String fromCurrency, String toCurrency, double amount, double result){
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.amount = amount;
        this.result = result;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public void convert(Rate rate){
        result = Math.round(amount * rate.getRate());
    }

    @Override
    public String toString() {
        return "From currency: " + fromCurrency + ", To currency: " + toCurrency + ", Amount to convert: " + amount + ", Result: " + result;
    }
}
