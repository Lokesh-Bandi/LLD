package com.example.hotel_management_system.state;

import com.example.hotel_management_system.room.RoomContext;

public class BookedState implements RoomState{
    @Override
    public void book(RoomContext roomContext) {
        System.out.println("Room is already booked.");
    }

    @Override
    public void cancelBooking(RoomContext roomContext) {
        System.out.println("Booking cancelled.....");
        roomContext.setState(new AvailableState());
    }

    @Override
    public void checkIn(RoomContext roomContext) {
        System.out.println("Checked in to the room.");
        roomContext.setState(new CheckedInState());
    }

    @Override
    public void checkOut(RoomContext roomContext) {
        System.out.println("Checked out of the room.");
    }

    @Override
    public void clean(RoomContext roomContext) {
        System.out.println("Room is being cleaned.");
    }
}
