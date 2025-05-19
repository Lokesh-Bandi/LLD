package com.example.hotel_management_system.room;

public class SuiteRoom extends Room{

    public SuiteRoom(String roomNumber, String roomType, double pricePerNight) {
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
        // Logic to get description of a suite room
        return "Suite Room: " + roomNumber + ", Type: " + roomType + ", Price: " + pricePerNight;
    }
}
