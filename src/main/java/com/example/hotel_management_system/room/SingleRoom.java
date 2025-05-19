package com.example.hotel_management_system.room;

public class SingleRoom extends Room{

    public SingleRoom(String roomNumber, String roomType, double pricePerNight) {
        super(roomNumber, roomType, pricePerNight);
        this.isAvailable = true;
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
        // Logic to get description of a single room
        return "Single Room: " + roomNumber + ", Type: " + roomType + ", Price: " + pricePerNight;
    }
}
