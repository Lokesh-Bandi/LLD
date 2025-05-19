package com.example.hotel_management_system.actions;

public abstract class CheckInTemplate {
    public final void checkIn(){
        verifyBookingConfirmation();
        verifyCustomerIdProof();
        allocateRoom();
        additionalServices();
        finalConfirmation();
    }

    protected void verifyBookingConfirmation(){
        System.out.println("Verifying booking confirmation...");
    }

    protected void verifyCustomerIdProof(){
        System.out.println("Verifying customer ID proof...");
    }

    protected void allocateRoom(){
        System.out.println("Allocating room...");
    }

    protected abstract void additionalServices();

    protected void finalConfirmation(){
        System.out.println("Room allocated successfully!!!!!.");
    }
}
