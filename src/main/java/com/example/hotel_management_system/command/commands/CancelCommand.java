package com.example.hotel_management_system.command.commands;

import com.example.hotel_management_system.booking.Booking;
import com.example.hotel_management_system.room.Room;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CancelCommand implements Command{
    public Booking booking;

    @Override
    public void execute() {
        Room room = booking.getRoom();
        room.cancelBooking();
        System.out.println("Booking cancelled for room: " + room.getRoomNumber());
    }

    @Override
    public void undo() {
        Room room = booking.getRoom();
        room.cancelBooking();
        System.out.println("Booking restored for room: " + room.getRoomNumber());
    }

}
