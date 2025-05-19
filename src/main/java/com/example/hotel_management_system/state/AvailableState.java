package com.example.hotel_management_system.state;

import com.example.hotel_management_system.room.RoomContext;

public class AvailableState implements RoomState{

    @Override
    public void book(RoomContext roomContext) {
        System.out.println("Room booked successfully.");
        roomContext.setState(new BookedState());
    }

    @Override
    public void cancelBooking(RoomContext roomContext) {
        System.out.println("No booking to cancel.");
    }

    @Override
    public void checkIn(RoomContext roomContext) {
        System.out.println("Room is available for check-in.");
    }

    @Override
    public void checkOut(RoomContext roomContext) {
        System.out.println("Room needs to be check-in before checkout.");
    }

    @Override
    public void clean(RoomContext roomContext) {
        System.out.println("Room is already clean.");
        roomContext.setState(new MaintainenceState());
    }
}
