package hr.apisit.reports;

import hr.apisit.domain.ServiceProvider;

import java.util.*;

public class MostExpensiveServiceReport {

    public static void mostExpensiveService(List<ServiceProvider> serviceProviderList) {

        Map<String, List<ServiceProvider>> mapOfCategory = new HashMap<>();

        for (ServiceProvider serviceProvider : serviceProviderList) {
            if (mapOfCategory.containsKey(serviceProvider.getNaziv())) {
                List<ServiceProvider> noviServiceProviderList = mapOfCategory.get(serviceProvider.getNaziv());
                noviServiceProviderList.add(serviceProvider);
                mapOfCategory.put(serviceProvider.getNaziv(), noviServiceProviderList);
            } else {
                List<ServiceProvider> noviServiceProviderList = new ArrayList<>();
                noviServiceProviderList.add(serviceProvider);
                mapOfCategory.put(serviceProvider.getNaziv(), noviServiceProviderList);
            }
        }

        for (String key : mapOfCategory.keySet()) {
            System.out.println(key + " " + mapOfCategory.get(key).size());

            System.out.println(mapOfCategory.get(key)
                    .stream()
                    .max(Comparator.comparing(s -> s.getCijenaUsluge()))
                    .get().getCijenaUsluge() + " KN");
        }
        System.out.println("\n");

        /*List<ServiceProvider> newTempServiceProviderList = new ArrayList<>();
        Set<String> setOfTypes = new HashSet<>();

        serviceProviderList.forEach(serviceProvider -> setOfTypes.add(serviceProvider.getNaziv()));

        for (String groupBy : setOfTypes) {
            for (ServiceProvider serviceProvider : serviceProviderList) {
                if (serviceProvider.getNaziv().contains(groupBy)) {
                    newTempServiceProviderList.add(serviceProvider);
//                  newTempServiceProviderList.add(serviceProviderList.stream().max(Comparator.comparing(v -> v.getCijenaUsluge())).get());
                }
            }
            BigDecimal maxPrice = newTempServiceProviderList.stream().max(Comparator.comparing(c -> c.getCijenaUsluge())).get().getCijenaUsluge();

            Service_Type maxPriceServiceType = newTempServiceProviderList.stream()
                    .max(Comparator.comparing(c -> c.getCijenaUsluge()))
                    .get().getVrstaUsluge();
            System.out.println("Pružatelj usluga: " + groupBy
                    + ", ima najskuplju uslugu: " + maxPriceServiceType.getServiceName()
                    + ", cijena: " + maxPrice + " KN");
            newTempServiceProviderList.clear();
        }*/

    }
}
