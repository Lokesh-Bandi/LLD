package com.example.hotel_management_system.state;

import com.example.hotel_management_system.room.RoomContext;

public interface RoomState {
    void book(RoomContext roomContext);
    void cancelBooking(RoomContext roomContext);
    void checkIn(RoomContext roomContext);
    void checkOut(RoomContext roomContext);
    void clean(RoomContext roomContext);
}
