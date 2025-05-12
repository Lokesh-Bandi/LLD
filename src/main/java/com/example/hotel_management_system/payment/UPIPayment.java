package com.example.hotel_management_system.payment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UPIPayment implements Payment{
    private String upiId;

    @Override
    public boolean pay(double amount) {
        System.out.println("Processing UPI payment of $" + amount);
        return true;
    }
}
