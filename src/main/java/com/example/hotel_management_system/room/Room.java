package com.example.hotel_management_system.room;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public abstract class Room {
    protected String roomNumber;
    protected String roomType;
    protected double pricePerNight;
    protected boolean isAvailable = true;

    public Room(String roomNumber, String roomType, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
    }
    public abstract void book();
    public abstract void cancelBooking();
    public abstract String getDescription();
}
