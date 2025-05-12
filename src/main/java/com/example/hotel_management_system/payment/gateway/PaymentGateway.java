package com.example.hotel_management_system.payment.gateway;

public interface PaymentGateway{
    boolean processPayment(double amount);
}
