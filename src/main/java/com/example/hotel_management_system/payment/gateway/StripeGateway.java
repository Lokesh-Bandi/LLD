package com.example.hotel_management_system.payment.gateway;

import org.springframework.stereotype.Component;

@Component
public class StripeGateway implements PaymentGateway{
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " through Stripe.");
        // Here you would integrate with the Stripe API to process the payment
        return true; // Assuming payment is successful
    }
}
