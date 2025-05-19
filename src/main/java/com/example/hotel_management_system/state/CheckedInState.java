package com.example.hotel_management_system.state;

import com.example.hotel_management_system.room.RoomContext;

public class CheckedInState implements RoomState{
    @Override
    public void book(RoomContext roomContext) {
        System.out.println("Room is already booked.");
    }

    @Override
    public void cancelBooking(RoomContext roomContext) {
        System.out.println("Booking Cannot be cancelled.....");
    }

    @Override
    public void checkIn(RoomContext roomContext) {
        System.out.println("Room already checked in.");
    }

    @Override
    public void checkOut(RoomContext roomContext) {
        System.out.println("Checked out of the room.");
        roomContext.setState(new AvailableState());
    }

    @Override
    public void clean(RoomContext roomContext) {
        System.out.println("Room is being cleaned.");
    }
}
