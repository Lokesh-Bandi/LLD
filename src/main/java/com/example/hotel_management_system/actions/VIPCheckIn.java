package com.example.hotel_management_system.actions;

public class VIPCheckIn extends CheckInTemplate{
    @Override
    protected void additionalServices() {
        System.out.println("Offering complimentary breakfast and spa services.");
    }

    @Override
    protected void finalConfirmation() {
        System.out.println("Room allocated successfully with VIP check-in.");
    }
}
