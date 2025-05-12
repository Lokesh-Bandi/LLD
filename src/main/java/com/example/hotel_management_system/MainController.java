package com.example.hotel_management_system;

import com.example.hotel_management_system.Customer.Customer;
import com.example.hotel_management_system.booking.Booking;
import com.example.hotel_management_system.booking.BookingDTO;
import com.example.hotel_management_system.booking.factory.Room;
import com.example.hotel_management_system.booking.factory.RoomFactory;
import com.example.hotel_management_system.invoice.HTMLInvoice;
import com.example.hotel_management_system.invoice.Invoice;
import com.example.hotel_management_system.invoice.PDFInvoice;
import com.example.hotel_management_system.invoice.TextInvoice;
import com.example.hotel_management_system.notification.dto.NotificationDTO;
import com.example.hotel_management_system.notification.factory.Notification;
import com.example.hotel_management_system.notification.factory.NotificationFactory;
import com.example.hotel_management_system.notification.factory.abstractFactory.EmailNotificationFactory;
import com.example.hotel_management_system.notification.factory.abstractFactory.PushNotificationFactory;
import com.example.hotel_management_system.notification.factory.abstractFactory.SMSNotificationFactory;
import com.example.hotel_management_system.payment.*;
import com.example.hotel_management_system.payment.gateway.PaypalGateway;
import com.example.hotel_management_system.payment.gateway.RazorpayGateway;
import com.example.hotel_management_system.payment.gateway.StripeGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotel")
public class MainController {
    @Autowired
    private RoomFactory roomFactory;

    @Autowired
    private RazorpayGateway razorpayGateway;

    @Autowired
    private StripeGateway stripeGateway;

    @Autowired
    private PaypalGateway paypalGateway;

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
            case "CREDIT_CARD" -> new CreditCardPayment(paymentDTO.getCardNumber(), paymentDTO.getCardHolderName(), razorpayGateway);
            case "BANK_TRANSFER" ->
                    new BankTransferPayment(paymentDTO.getBankAccountNumber(), paymentDTO.getBankName(), stripeGateway);
            case "UPI" -> new UPIPayment(paymentDTO.getUpiId(), paypalGateway);
            default -> null;
        };

        if(payment == null) {
            return "Invalid payment type";
        }

        boolean isPaymentSuccessful = payment.pay(paymentDTO.getAmount());
        return isPaymentSuccessful ? "Payment is successful" : "Payment failed";
    }

    @GetMapping("/invoice/generate/{invoiceType}")
    public ResponseEntity<String> generateInvoice(@PathVariable String invoiceType){
        try {
            Room room = roomFactory.createRoom("single", "244", 3455);
            Customer customer = new Customer("Lokesh", "Lokesh@gmail.com", "1234567890", 2);

            Booking booking = new Booking.Builder()
                    .setRoom(room)
                    .setCustomer(customer)
                    .setPaymentType("UPI")
                    .setAmount(3455)
                    .setBreakfastIncluded(true)
                    .setParkingIncluded(true)
                    .build();


            Invoice invoice = switch (invoiceType) {
                case "text" -> new TextInvoice(booking);
                case "pdf" -> new PDFInvoice(booking);
                case "html" -> new HTMLInvoice(booking);
                default -> throw new IllegalStateException("Unexpected value: " + invoiceType);
            };

            String invoiceDetails = invoice.generateInvoice();

            return ResponseEntity.ok(invoiceDetails);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/send/notification/{type}")
    public ResponseEntity<String> sendNotification(@PathVariable String type, @RequestBody NotificationDTO notificationDTO) {

        NotificationFactory notificationFactory = switch (type) {
            case "email" ->
                    new EmailNotificationFactory(notificationDTO.getRecipient(), notificationDTO.getSubject(), notificationDTO.getMessage());
            case "sms" ->
                    new SMSNotificationFactory(notificationDTO.getRecipient(), notificationDTO.getMessage());
            case "push" -> new PushNotificationFactory(notificationDTO.getRecipient(), notificationDTO.getPayload());
            default -> throw new RuntimeException("Invalid notification type");
        };

        Notification notification = notificationFactory.createNotification();
        notification.sendNotification();
        return ResponseEntity.ok(notification.toString());
    }
}
