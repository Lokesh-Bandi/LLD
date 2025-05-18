package com.example.hotel_management_system.room;

public class SingleRoom extends Room{

    public SingleRoom(String roomNumber, String roomType, double pricePerNight) {
        super(roomNumber, roomType, pricePerNight);
        this.isAvailable = true;
    }

    @Override
    public void book() {
        // Logic to book a single room
        if(!isAvailable){
            System.out.println("Single room is not available.");
        } else {
            System.out.println("Booking single room..." + roomNumber);
            isAvailable = false;
        }
    }

    @Override
    public void cancelBooking() {
        // Logic to cancel booking of a single room
        System.out.println("Cancelling booking for single room..." + roomNumber);
        isAvailable = true;
    }

    @Override
    public String getDescription() {
        // Logic to get description of a single room
        return "Single Room: " + roomNumber + ", Type: " + roomType + ", Price: " + pricePerNight;
    }
}
