package com.example.hotel_management_system.room.factory;

public class SuiteRoom extends Room{

    public SuiteRoom(String roomNumber, String roomType, double pricePerNight) {
        super(roomNumber, roomType, pricePerNight);
    }

    @Override
    public void book() {
        // Logic to book a suite room
        if(!isAvailable){
            System.out.println("Suite room is not available.");
        } else {
            System.out.println("Booking suite room..." + roomNumber);
            isAvailable = false;
        }
    }

    @Override
    public void cancelBooking() {
        // Logic to cancel booking of a suite room
        System.out.println("Cancelling booking for suite room..." + roomNumber);
        isAvailable = true;
    }

    @Override
    public String getDescription() {
        // Logic to get description of a suite room
        return "Suite Room: " + roomNumber + ", Type: " + roomType + ", Price: " + pricePerNight;
    }
}
