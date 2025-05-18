package com.example.hotel_management_system.invoice;

import com.example.hotel_management_system.booking.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HTMLInvoice implements Invoice{
    private Booking booking;

    @Override
    public String generateInvoice() {
        System.out.println("Generating HTML invoice for booking: " + booking);

        String invoiceDetails = "<html><body>" +
                "<h1>Invoice Details</h1>" +
                "<p>Customer Name: " + booking.getCustomer().getName() + "</p>" +
                "<p>Room Type: " + booking.getRoom().getRoomType() + "</p>" +
                "<p>Payment Type: " + booking.getPaymentType() + "</p>" +
                "<p>Amount: $" + booking.getAmount() + "</p>" +
                "<p>Description: " + booking.getRoom().getDescription() + "</p>" +
                "</body></html>";

        return invoiceDetails;
    }
}
