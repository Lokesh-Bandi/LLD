package com.example.hotel_management_system.services;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IndividualService implements Service{
    private String serviceName;
    private int cost;

    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public int getCost() {
        return cost;
    }

}
