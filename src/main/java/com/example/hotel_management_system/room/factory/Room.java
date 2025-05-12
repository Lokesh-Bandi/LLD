package com.example.hotel_management_system.room.factory;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public abstract class Room {
    protected final String roomNumber;
    protected final String roomType;
    protected final double pricePerNight;
    protected boolean isAvailable = true;

    public abstract void book();
    public abstract void cancelBooking();
    public abstract String getDescription();
}
