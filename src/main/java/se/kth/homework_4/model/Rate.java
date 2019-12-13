package se.kth.homework_4.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "rate")
public class Rate {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @Column(name = "number_of_uses")
    @NotNull
    @PositiveOrZero()
    private int numberOfUses;

    @ManyToOne
    @NotNull
    private Currency fromCurrency;

    @ManyToOne
    @NotNull
    private Currency toCurrency;

    @Column(name = "rate")
    @NotNull
    @PositiveOrZero()
    private double rate;

    public Rate(){

    }

    public Rate(Currency fromCurrency, Currency toCurrency, double rate){
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.rate = rate;
        this.numberOfUses = 0;
    }

    public int getId(){ return id;}

    public Currency getFromCurrency() {
        return fromCurrency;
    }

    public Currency getToCurrency() {
        return toCurrency;
    }

    public double getRate() {
        return rate;
    }

    public int getNumberOfUses(){return numberOfUses;}

    public void incrementNumberOfUses(){numberOfUses++;}

    public void setRate(double rate){ this.rate = rate ;}

    @Override
    public String toString() {
        return "Id: " + id + ", Rate: " + rate + ", From currency: " + fromCurrency + ", To currency: " + toCurrency + ", Uses: " + numberOfUses;
    }
}
