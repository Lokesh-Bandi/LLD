package com.example.hotel_management_system.payment;

public class BankTransferPayment  implements Payment{
    private String bankAccountNumber;
    private String bankName;

    public BankTransferPayment(String bankAccountNumber, String bankName) {
        this.bankAccountNumber = bankAccountNumber;
        this.bankName = bankName;
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("Processing bank transfer payment of $" + amount + " from account " + bankAccountNumber + " at " + bankName);
        return true;
    }
}
