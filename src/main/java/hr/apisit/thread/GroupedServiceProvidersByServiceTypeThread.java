package hr.apisit.thread;

import hr.apisit.domain.Holding;
import hr.apisit.domain.ServiceProvider;
import hr.apisit.domain.ServiceType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupedServiceProvidersByServiceTypeThread implements Runnable {

    List<ServiceProvider> serviceProviderList;


    public GroupedServiceProvidersByServiceTypeThread(List<ServiceProvider> serviceProviderList) {
        this.serviceProviderList = serviceProviderList;
    }

    @Override
    public void run() {

        while (true) {
            try {
                Map<ServiceType, List<ServiceProvider>> mapaUslugaPoTipu = new HashMap<>();
                Holding holding = new Holding(mapaUslugaPoTipu);

                for (ServiceProvider s : serviceProviderList) {
                    if (s.getVrstaUsluge().equals(ServiceType.GAS_SUPPLY)) {
                        holding.addServiceprovider(s.getVrstaUsluge(), s);
                    }
                    if (s.getVrstaUsluge().equals(ServiceType.WATER_SUPPLY)) {
                        holding.addServiceprovider(s.getVrstaUsluge(), s);
                    }
                    if (s.getVrstaUsluge().equals(ServiceType.ELECTRICITY_SUPPLY)) {
                        holding.addServiceprovider(s.getVrstaUsluge(), s);
                    }
                    if (s.getVrstaUsluge().equals(ServiceType.INTERNET_SUPPLY)) {
                        holding.addServiceprovider(s.getVrstaUsluge(), s);
                    }
                    if (s.getVrstaUsluge().equals(ServiceType.GARBAGE_COLLECTION)) {
                        holding.addServiceprovider(s.getVrstaUsluge(), s);
                    }
                }

                Map mapaUsluga = holding.getMapaUsluga();
                mapaUsluga.forEach((k, v) -> System.out.println((k + ":" + v)));

                Thread.sleep(7000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
