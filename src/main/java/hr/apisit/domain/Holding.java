package hr.apisit.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Holding {

    private Map<ServiceType,List<ServiceProvider>> mapaUsluga;

    public Holding(Map<ServiceType, List<ServiceProvider>> mapaUsluga) {
        this.mapaUsluga = mapaUsluga;
    }

    public Map<ServiceType, List<ServiceProvider>> getMapaUsluga() {
        return mapaUsluga;
    }

    public void setMapaUsluga(Map<ServiceType, List<ServiceProvider>> mapaUsluga, List<ServiceProvider> serviceProviderList) {
        this.mapaUsluga = mapaUsluga;
    }

    public void addServiceprovider(ServiceType serviceType, ServiceProvider serviceProvider) {
        mapaUsluga.computeIfAbsent(serviceType, k -> new ArrayList<>()).add(serviceProvider);
    }
}

