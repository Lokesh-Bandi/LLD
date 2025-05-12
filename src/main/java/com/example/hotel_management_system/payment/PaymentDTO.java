package com.example.hotel_management_system.payment;

import lombok.Data;

@Data
public class PaymentDTO {
    private String paymentType;
    private double amount;
    private String cardNumber;
    private String cardHolderName;
    private String bankAccountNumber;
    private String bankName;
    private String upiId;
}
