package com.driver;

public class BankAccount {

    private String name;
    private double balance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }

    private double minBalance;

    public BankAccount(String name, double balance, double minBalance) {
        this.name = name;
        this.balance = balance;
        this.minBalance = minBalance;

    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
        if(digits*9<sum)
        {
            throw new Exception("Account Number can not be generated");
        }
        String acc_no="";
        while(sum>=9)
        {
            acc_no+='9';
            sum-=9;
        }
        acc_no+=sum+"";
        while(acc_no.length()<digits)
        {
            acc_no+='0';
        }

        return acc_no;
    }

    public void deposit(double amount) {
        //add amount to balance
        if(amount>0)
        {
            balance+=amount;
        }

    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        if(balance-amount>=minBalance)
        {
            balance = balance-amount;
        }
        else {
            throw new Exception("Insufficient Balance");
        }
    }

}