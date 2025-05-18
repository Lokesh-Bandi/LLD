package com.example.hotel_management_system.room;

public class DoubleRoom extends Room{

    public DoubleRoom(String roomNumber, String roomType, double pricePerNight) {
        super(roomNumber, roomType, pricePerNight);
    }

    @Override
    public void book() {
        // Logic to book a double room
        if(!isAvailable){
            System.out.println("Double room is not available.");
        } else {
            System.out.println("Booking double room..." + roomNumber);
            isAvailable = false;
        }
    }

    @Override
    public void cancelBooking() {
        // Logic to cancel booking of a double room
        System.out.println("Cancelling booking for double room..." + roomNumber);
        isAvailable = true;
    }

    @Override
    public String getDescription() {
        // Logic to get description of a double room
        return "Double Room: " + roomNumber + ", Type: " + roomType + ", Price: " + pricePerNight;
    }
}
