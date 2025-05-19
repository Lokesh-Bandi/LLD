package com.example.hotel_management_system;

import com.example.hotel_management_system.Customer.Customer;
import com.example.hotel_management_system.actions.CheckInTemplate;
import com.example.hotel_management_system.actions.RegularCheckIn;
import com.example.hotel_management_system.actions.VIPCheckIn;
import com.example.hotel_management_system.booking.Booking;
import com.example.hotel_management_system.booking.BookingDTO;
import com.example.hotel_management_system.command.BookingManager;
import com.example.hotel_management_system.command.commands.BookingCommand;
import com.example.hotel_management_system.decorators.WithBreakfastDecorator;
import com.example.hotel_management_system.decorators.WithParkingDecorator;
import com.example.hotel_management_system.decorators.WithWifiDecorator;
import com.example.hotel_management_system.room.Room;
import com.example.hotel_management_system.room.factory.RoomFactory;
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
import com.example.hotel_management_system.notification.observer.NotificationService;
import com.example.hotel_management_system.notification.observer.observers.EmailNotificationNotificationObserver;
import com.example.hotel_management_system.notification.observer.observers.PushNotificationNotificationObserver;
import com.example.hotel_management_system.notification.observer.observers.SMSNotificationNotificationObserver;
import com.example.hotel_management_system.payment.*;
import com.example.hotel_management_system.payment.gateway.PaypalGateway;
import com.example.hotel_management_system.payment.gateway.RazorpayGateway;
import com.example.hotel_management_system.payment.gateway.StripeGateway;
import com.example.hotel_management_system.services.IndividualService;
import com.example.hotel_management_system.services.ServicePackage;
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

    @Autowired
    NotificationService notificationService;

    @Autowired
    BookingManager bookingManager;

    public MainController(NotificationService notificationService) {
        this.notificationService = notificationService;

        this.notificationService.registerObserver(new EmailNotificationNotificationObserver());
        this.notificationService.registerObserver(new SMSNotificationNotificationObserver());
        this.notificationService.registerObserver(new PushNotificationNotificationObserver());

    }
    @PostMapping("/booking/create")
    public ResponseEntity<String> createBooking(@RequestBody BookingDTO bookingDTO) {
        try {
            Room room = roomFactory.createRoom(bookingDTO.getRoomType(), bookingDTO.getRoomNumber(), bookingDTO.getPricePerNight());
            if(bookingDTO.isWifiIncluded()) {
                room = new WithWifiDecorator(room);
            }
            if(bookingDTO.isBreakfastIncluded()) {
                room = new WithBreakfastDecorator(room);
            }
            if(bookingDTO.isParkingRequired()) {
                room = new WithParkingDecorator(room);
            }


            Customer customer = new Customer(bookingDTO.getCustomerName(), bookingDTO.getCustomerEmail(), bookingDTO.getCustomerPhoneNumber(), bookingDTO.getHeadCount());

            Booking booking = new Booking.Builder()
                    .setRoom(room)
                    .setCustomer(customer)
                    .build();

            BookingCommand bookingCommand = new BookingCommand(booking);
            bookingManager.executeCommand(bookingCommand);

            bookingManager.executeCommand(bookingCommand);

            NotificationDTO notificationDTO = new NotificationDTO.Builder()
                                                        .recipient(booking.getCustomer().getEmail())
                                                        .subject("Booking Confirmation!!")
                                                        .message("Booking details : " + booking.toString())
                                                        .payload("Room Description : " + booking.getRoom().getDescription())
                                                        .build();

            System.out.println("Description : " + booking.getRoom().getDescription());
            notificationService.sendNotification(notificationDTO);



            return ResponseEntity.ok(booking.toString());
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

    @GetMapping("/services/init")
    public ResponseEntity<String> initServices() {
        try {
            // Initialize services
            IndividualService spaService = new IndividualService("SPA", 100);
            IndividualService swimmingPoolService = new IndividualService("Swimming pool ", 200);
            IndividualService gymService = new IndividualService("GYM", 300);

            ServicePackage servicePackage = new ServicePackage("Luxury Package");
            servicePackage.addService(spaService);
            servicePackage.addService(swimmingPoolService);
            servicePackage.addService(gymService);

            // Print service details
            System.out.println("Service Package: " + servicePackage.getServiceName());
            System.out.println("Luxury Package Services cost: " + servicePackage.getCost());

            return ResponseEntity.ok("Services initialized successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/rooms/check-in/{checkInType}")
    public ResponseEntity<String> checkIn(@PathVariable String checkInType) {
        try {
            CheckInTemplate checkInTemplate = switch (checkInType) {
                case "regular" -> new RegularCheckIn();
                case "vip" -> new VIPCheckIn();
                default -> throw new IllegalArgumentException("Invalid check-in type");
            };

            checkInTemplate.checkIn();

            return ResponseEntity.ok("Check-in process completed successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
