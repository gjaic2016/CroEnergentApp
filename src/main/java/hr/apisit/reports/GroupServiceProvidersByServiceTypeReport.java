package hr.apisit.reports;

import hr.apisit.domain.Holding;
import hr.apisit.domain.ServiceProvider;
import hr.apisit.domain.ServiceType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupServiceProvidersByServiceTypeReport {

    public static void groupedServiceProvidersByServiceType(List<ServiceProvider> serviceProviderList) {

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
        System.out.println("\n");

//                    mapaUsluga.entrySet().stream().forEach(e -> System.out.println(e.getKey() + ":" + e.getValue()));

//                    for (Object entry : mapaUsluga.entrySet()) {
//                        System.out.println(entry.getKey() + ":" + entry.getValue().toString());
//                    }

    }
}
