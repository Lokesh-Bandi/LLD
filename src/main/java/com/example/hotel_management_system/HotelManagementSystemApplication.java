package com.example.hotel_management_system;

import com.example.hotel_management_system.booking.factory.Room;
import com.example.hotel_management_system.booking.factory.RoomFactory;
import com.example.hotel_management_system.booking.factory.RoomFactoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelManagementSystemApplication.class, args);

	}

}
