package com.example.hotel_management_system.payment;

import com.example.hotel_management_system.payment.gateway.PaymentGateway;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UPIPayment implements Payment{
    private String upiId;
    private PaymentGateway paymentGateway;

    @Override
    public boolean pay(double amount) {
        System.out.println("Processing UPI payment of $" + amount);
        return paymentGateway.processPayment(amount);
    }
}
