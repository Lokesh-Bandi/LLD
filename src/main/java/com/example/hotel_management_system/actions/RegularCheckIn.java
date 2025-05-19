package com.example.hotel_management_system.actions;

public class RegularCheckIn extends CheckInTemplate{
    @Override
    protected void additionalServices() {
        System.out.println("No additional services selected.");
    }

    @Override
    protected void finalConfirmation() {
        System.out.println("Room allocated successfully with regular check-in.");
    }
}
