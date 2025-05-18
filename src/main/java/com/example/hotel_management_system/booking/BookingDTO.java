package com.example.hotel_management_system.booking;

import lombok.Data;

@Data
public class BookingDTO {
    private String roomNumber;
    private String roomType;
    private double pricePerNight;

    private String customerName;
    private String customerEmail;
    private String customerPhoneNumber;
    private int headCount;

    private boolean parkingRequired;
    private boolean breakfastIncluded;
    private boolean wifiIncluded;
}
