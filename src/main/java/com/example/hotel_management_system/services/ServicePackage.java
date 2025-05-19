package com.example.hotel_management_system.services;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ServicePackage implements Service {
    @Getter
    private List<Service> services;
    private String serviceName;
    public ServicePackage(String servicePackageName) {
        services = new ArrayList<>();
        this.serviceName = servicePackageName;
    }

    public void addService(Service service) {
        services.add(service);
    }
    public void removeService(Service service) {
        services.remove(service);
    }

    @Override
    public int getCost() {
        int totalCost = 0;
        for (Service service : services) {
            totalCost += service.getCost();
        }
        return totalCost;
    }
    @Override
    public String getServiceName() {
        return serviceName;
    }
}

