package com.example.hotel_management_system.room;

public class DoubleRoom extends Room{

    public DoubleRoom(String roomNumber, String roomType, double pricePerNight) {
        super(roomNumber, roomType, pricePerNight);
    }

    @Override
    public void book() {
        this.roomContext.book();
    }

    @Override
    public void cancelBooking() {
        this.roomContext.cancelBooking();
    }

    @Override
    public String getDescription() {
        // Logic to get description of a double room
        return "Double Room: " + roomNumber + ", Type: " + roomType + ", Price: " + pricePerNight;
    }
}
