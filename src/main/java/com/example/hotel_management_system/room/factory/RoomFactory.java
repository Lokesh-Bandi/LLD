package com.example.hotel_management_system.room.factory;

import com.example.hotel_management_system.room.Room;

public interface RoomFactory {
    Room createRoom(String roomType, String roomNumber, double pricePerNight);
}
