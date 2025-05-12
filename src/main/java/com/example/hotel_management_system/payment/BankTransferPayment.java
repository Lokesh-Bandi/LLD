package com.example.hotel_management_system.payment;

import com.example.hotel_management_system.payment.gateway.PaymentGateway;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankTransferPayment  implements Payment{
    private String bankAccountNumber;
    private String bankName;
    private PaymentGateway paymentGateway;

    @Override
    public boolean pay(double amount) {
        System.out.println("Processing bank transfer payment of $" + amount + " from account " + bankAccountNumber + " at " + bankName);
        return paymentGateway.processPayment(amount);
    }
}
