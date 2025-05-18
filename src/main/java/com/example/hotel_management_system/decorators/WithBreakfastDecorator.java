package com.example.hotel_management_system.decorators;

import com.example.hotel_management_system.room.Room;

public class WithBreakfastDecorator extends RoomDecorator {
    public WithBreakfastDecorator(Room room) {
        super(room);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", with breakfast";
    }
}
