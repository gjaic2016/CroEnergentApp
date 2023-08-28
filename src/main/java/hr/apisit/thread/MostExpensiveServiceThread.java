package hr.apisit.thread;

import hr.apisit.domain.ServiceProvider;

import java.util.*;
import java.util.concurrent.Callable;

public class MostExpensiveServiceThread implements Callable {

    List<ServiceProvider> serviceProviderList;

    public MostExpensiveServiceThread(List<ServiceProvider> serviceProviderList) {
        this.serviceProviderList = serviceProviderList;
    }

    @Override
    public String call() throws Exception {

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
                    .get().getCijenaUsluge());
        }
    return "TEST FUTURE CALL METODA";

    }


}
