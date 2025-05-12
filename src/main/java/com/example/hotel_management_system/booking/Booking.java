package com.example.hotel_management_system.booking;

import com.example.hotel_management_system.Customer.Customer;
import com.example.hotel_management_system.room.factory.Room;
import lombok.Data;

@Data
public class Booking {
    private Room room;
    private Customer customer;

    private String paymentType;
    private double amount;

    private final boolean isWifiIncluded;
    private final boolean isBreakfastIncluded;
    private final boolean isParkingIncluded;

    public Booking(Builder builder) {
        this.room = builder.room;
        this.customer = builder.customer;
        this.paymentType = builder.paymentType;
        this.amount = builder.amount;
        this.isWifiIncluded = builder.isWifiIncluded;
        this.isBreakfastIncluded = builder.isBreakfastIncluded;
        this.isParkingIncluded = builder.isParkingIncluded;
    }

    public static class Builder {
        private Room room;
        private Customer customer;

        private String paymentType;
        private double amount;

        private boolean isWifiIncluded;
        private boolean isBreakfastIncluded;
        private boolean isParkingIncluded;

        public Builder setRoom(Room room) {
            this.room = room;
            return this;
        }

        public Builder setCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder setWifiIncluded(boolean isWifiIncluded) {
            this.isWifiIncluded = isWifiIncluded;
            return this;
        }

        public Builder setBreakfastIncluded(boolean isBreakfastIncluded) {
            this.isBreakfastIncluded = isBreakfastIncluded;
            return this;
        }

        public Builder setParkingIncluded(boolean isParkingIncluded) {
            this.isParkingIncluded = isParkingIncluded;
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
