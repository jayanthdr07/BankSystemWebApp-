package com.bank;

public class BankAccount {
    private int accNo;
    private String name;
    private String password;
    private double balance;

    public BankAccount(int accNo, String name, String password, double balance) {
        this.accNo = accNo;
        this.name = name;
        this.password = password;
        this.balance = balance;
    }

    public int getAccNo() {
        return accNo;
    }

    public boolean checkPassword(String pass) {
        return password.equals(pass);
    }

    public void deposit(double amt) {
        balance += amt;
    }

    public boolean withdraw(double amt) {
        if (amt <= balance) {
            balance -= amt;
            return true;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }
}
