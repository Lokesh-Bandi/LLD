package com.example.hotel_management_system;

import com.example.hotel_management_system.Customer.Customer;
import com.example.hotel_management_system.booking.Booking;
import com.example.hotel_management_system.booking.BookingDTO;
import com.example.hotel_management_system.booking.factory.Room;
import com.example.hotel_management_system.booking.factory.RoomFactory;
import com.example.hotel_management_system.payment.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hotel")
public class MainController {
    @Autowired
    private RoomFactory roomFactory;

    @PostMapping("/booking/create")
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

    @PostMapping("/payment/process")
    public String processPayment(@RequestBody PaymentDTO paymentDTO) {
        Payment payment = switch (paymentDTO.getPaymentType()) {
            case "CREDIT_CARD" -> new CreditCardPayment(paymentDTO.getCardNumber(), paymentDTO.getCardHolderName());
            case "BANK_TRANSFER" ->
                    new BankTransferPayment(paymentDTO.getBankAccountNumber(), paymentDTO.getBankName());
            case "UPI" -> new UPIPayment(paymentDTO.getUpiId());
            default -> null;
        };

        if(payment == null) {
            return "Invalid payment type";
        }

        boolean isPaymentSuccessful = payment.pay(paymentDTO.getAmount());
        return isPaymentSuccessful ? "Payment is successful" : "Payment failed";
    }

}
