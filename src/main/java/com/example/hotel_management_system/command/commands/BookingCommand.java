package com.example.hotel_management_system.command.commands;

import com.example.hotel_management_system.booking.Booking;
import com.example.hotel_management_system.room.Room;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BookingCommand implements Command {
    public Booking booking;

    @Override
    public void execute() {
        Room room = this.booking.getRoom();
        room.book();
        System.out.println("Booking command executed.");
    }

    @Override
    public void undo() {
        Room room = this.booking.getRoom();
        room.cancelBooking();
        System.out.println("Booking command undone.");
    }
}
