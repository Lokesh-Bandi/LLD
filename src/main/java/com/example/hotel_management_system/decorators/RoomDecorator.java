package com.example.hotel_management_system.decorators;

import com.example.hotel_management_system.room.Room;

public class RoomDecorator extends Room {
    private final Room room;
    public RoomDecorator(Room room){
        this.room = room;
    }
    @Override
    public void book() {
        room.book();
    }

    @Override
    public void cancelBooking(){
        room.cancelBooking();
    }
    @Override
    public String getDescription() {
        return room.getDescription();
    }
}
