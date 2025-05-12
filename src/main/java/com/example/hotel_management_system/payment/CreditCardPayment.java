package com.example.hotel_management_system.payment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreditCardPayment implements Payment{
    private String cardNumber;
    private String cardHolderName;

    @Override
    public boolean pay(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
        return true;
    }
}
