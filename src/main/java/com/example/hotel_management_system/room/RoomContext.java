package com.example.hotel_management_system.room;

import com.example.hotel_management_system.state.AvailableState;
import com.example.hotel_management_system.state.RoomState;

public class RoomContext {
    private RoomState state;
    public RoomContext() {
        this.state = new AvailableState();
    }
    public void setState(RoomState state) {
        this.state = state;
    }

    public void book() {
        state.book(this);
    }

    public void cancelBooking() {
        state.cancelBooking(this);
    }

    public void checkIn() {
        state.checkIn(this);
    }

    public void checkOut() {
        state.checkOut(this);
    }

    public void clean() {
        state.clean(this);
    }

}
