package com.example.hotel_management_system.booking;

import lombok.Data;

import java.util.Date;

@Data
public class BookingDTO {
    private String roomNumber;
    private String roomType;
    private double pricePerNight;

    private String customerName;
    private String customerEmail;
    private String customerPhoneNumber;
    private int headCount;

    private boolean breakfastIncluded;
    private boolean wifiIncluded;
    private boolean parkingIncluded;
}
