package com.example.hotel_management_system.payment.gateway;

import org.springframework.stereotype.Component;

@Component
public class PaypalGateway implements PaymentGateway{
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " through PayPal.");
        // Here you would integrate with the PayPal API to process the payment
        return true; // Assuming payment is successful
    }
}
