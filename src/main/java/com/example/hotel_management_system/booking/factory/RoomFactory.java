package com.example.hotel_management_system.booking.factory;

public interface RoomFactory {
    Room createRoom(String roomType, String roomNumber, double pricePerNight);
}
