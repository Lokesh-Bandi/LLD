package com.example.hotel_management_system.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {
    private String name;
    private String email;
    private String phoneNumber;
    private int headCount;
}
