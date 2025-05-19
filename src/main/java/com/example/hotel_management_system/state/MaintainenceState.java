package com.example.hotel_management_system.state;

import com.example.hotel_management_system.room.RoomContext;

public class MaintainenceState implements RoomState{
    @Override
    public void book(RoomContext roomContext) {
        System.out.println("Room is under maintenance. Cannot book.");
    }

    @Override
    public void cancelBooking(RoomContext roomContext) {
        System.out.println("Room is under maintenance. Cannot cancel booking.");
    }

    @Override
    public void checkIn(RoomContext roomContext) {
        System.out.println("Room is under maintenance. Cannot check in.");
    }

    @Override
    public void checkOut(RoomContext roomContext) {
        System.out.println("Room is under maintenance. Cannot check out.");
    }

    @Override
    public void clean(RoomContext roomContext) {
        System.out.println("Room is under maintenance. Cannot clean.");
    }
}
