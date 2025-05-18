package com.example.hotel_management_system.decorators;

import com.example.hotel_management_system.room.Room;

public class WithParkingDecorator extends RoomDecorator{

    public WithParkingDecorator(Room room) {
        super(room);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", with parking ";
    }

}
