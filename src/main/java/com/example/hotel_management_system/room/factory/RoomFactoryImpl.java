package com.example.hotel_management_system.room.factory;

import org.springframework.stereotype.Service;

@Service
public class RoomFactoryImpl implements RoomFactory{
    @Override
    public Room createRoom(String roomType, String roomNumber, double pricePerNight) {
        return switch (roomType) {
            case "single" -> new SingleRoom(roomNumber, roomType, pricePerNight);
            case "double" -> new DoubleRoom(roomNumber, roomType, pricePerNight);
            case "suite" -> new SuiteRoom(roomNumber, roomType, pricePerNight);
            default -> throw new IllegalArgumentException("Invalid room type: " + roomType);
        };
    }
}
