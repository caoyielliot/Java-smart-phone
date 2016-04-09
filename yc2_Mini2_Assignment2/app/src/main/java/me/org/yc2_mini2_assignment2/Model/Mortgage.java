package me.org.yc2_mini2_assignment2.Model;

import java.io.Serializable;

/**
 * Created by caoyi on 16/3/14.
 * This class is used to encapsulate of input information about mortage
 */
public class Mortgage implements Serializable {
    private int purchasePrice;
    private int mortgageTerm;
    private double interestRate;
    private int firstPaymentYear;
    private int firstPaymentMonth;

    private double monthlyPayment;
    private double totalPayment;
    private int payoffYear;
    //Getters and Setters of those attributes

    public Mortgage(int purchasePrice, int mortgageTerm, double interestRate, int firstPaymentYear, int firstPaymentMonth) {
        this.purchasePrice = purchasePrice;
        this.mortgageTerm = mortgageTerm;
        this.interestRate = interestRate;
        this.firstPaymentYear = firstPaymentYear;
        this.firstPaymentMonth = firstPaymentMonth;
        this.calculate();
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getMortgageTerm() {
        return mortgageTerm;
    }

    public void setMortgageTerm(int mortgageTerm) {
        this.mortgageTerm = mortgageTerm;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getFirstPaymentYear() {
        return firstPaymentYear;
    }

    public void setFirstPaymentYear(int firstPaymentYear) {
        this.firstPaymentYear = firstPaymentYear;
    }

    public int getFirstPaymentMonth() {
        return firstPaymentMonth;
    }

    public void setFirstPaymentMonth(int firstPaymentMonth) {
        this.firstPaymentMonth = firstPaymentMonth;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public int getPayoffYear() {
        return payoffYear;
    }

    public void setPayoffYear(int payoffYear) {
        this.payoffYear = payoffYear;
    }

    //Getter of the first payment date
    public String getFirstPayDate() {
        return this.firstPaymentYear + " " + this.firstPaymentMonth;
    }

    //Getter of the first payoff date
    public String getPayoffDate() {
        return this.firstPaymentMonth + "/" + this.payoffYear;
    }

    //calculate the mortgage details
    private void calculate() {
        interestRate /= 100.0;
        double monthlyRate = interestRate / 12.0;
        int termInMonths = mortgageTerm * 12;
        this.monthlyPayment = (purchasePrice * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termInMonths));
        this.monthlyPayment = Math.round(monthlyPayment * 10) / 10;
        this.totalPayment = this.monthlyPayment * 12 * mortgageTerm;
        this.totalPayment = Math.round(totalPayment * 10) / 10;
        this.payoffYear = this.firstPaymentYear + this.mortgageTerm;
    }
}
