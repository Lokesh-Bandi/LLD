package com.example.hotel_management_system.invoice;

import com.example.hotel_management_system.booking.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TextInvoice implements Invoice{
    private Booking booking;
    @Override
    public String generateInvoice() {
        System.out.println("Generating Text invoice");

        String invoiceDetails = "Invoice Details:\n" +
                "Customer Name: " + booking.getCustomer().getName() + "\n" +
                "Room Type: " + booking.getRoom().getRoomType() + "\n" +
                "Payment Type: " + booking.getPaymentType() + "\n" +
                "Amount: $" + booking.getAmount() + "\n" +
                "Wifi Included: " + (booking.isWifiIncluded() ? "Yes" : "No") + "\n" +
                "Breakfast Included: " + (booking.isBreakfastIncluded() ? "Yes" : "No") + "\n" +
                "Parking Included: " + (booking.isParkingIncluded() ? "Yes" : "No");

        return invoiceDetails;
    }
}
