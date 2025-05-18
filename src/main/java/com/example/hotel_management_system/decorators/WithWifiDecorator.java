package com.example.hotel_management_system.decorators;

import com.example.hotel_management_system.room.Room;

public class WithWifiDecorator extends RoomDecorator {
    public WithWifiDecorator(Room room) {
        super(room);
    }
    @Override
    public String getDescription(){
        return super.getDescription() + ", with WIFI";
    }
}
