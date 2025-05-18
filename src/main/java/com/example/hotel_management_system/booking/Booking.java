package com.example.hotel_management_system.booking;

import com.example.hotel_management_system.Customer.Customer;
import com.example.hotel_management_system.room.Room;
import lombok.Data;

@Data
public class Booking {
    private Room room;
    private Customer customer;

    private String paymentType;
    private double amount;

    public Booking(Builder builder) {
        this.room = builder.room;
        this.customer = builder.customer;
        this.paymentType = builder.paymentType;
        this.amount = builder.amount;
    }

    public static class Builder {
        private Room room;
        private Customer customer;

        private String paymentType;
        private double amount;


        public Builder setRoom(Room room) {
            this.room = room;
            return this;
        }

        public Builder setCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder setPaymentType(String paymentType) {
            this.paymentType = paymentType;
            return this;
        }

        public Builder setAmount(double amount) {
            this.amount = amount;
            return this;
        }

        public Booking build() {
            return new Booking(this);
        }
    }
}
