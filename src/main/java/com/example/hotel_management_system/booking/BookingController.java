package com.example.hotel_management_system.booking;

import com.example.hotel_management_system.Customer.Customer;
import com.example.hotel_management_system.booking.factory.Room;
import com.example.hotel_management_system.booking.factory.RoomFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private RoomFactory roomFactory;

    @PostMapping("/create")
    public String createBooking(@RequestBody BookingDTO bookingDTO) {
        try {
            Room room = roomFactory.createRoom(bookingDTO.getRoomType(), bookingDTO.getRoomNumber(), bookingDTO.getPricePerNight());

            Customer customer = new Customer(bookingDTO.getCustomerName(), bookingDTO.getCustomerEmail(), bookingDTO.getCustomerPhoneNumber(), bookingDTO.getHeadCount());

            Booking booking = new Booking.Builder()
                    .setRoom(room)
                    .setCustomer(customer)
                    .setWifiIncluded(bookingDTO.isWifiIncluded())
                    .setBreakfastIncluded(bookingDTO.isBreakfastIncluded())
                    .setParkingIncluded(bookingDTO.isParkingIncluded())
                    .build();

            room.book();

            return booking.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
