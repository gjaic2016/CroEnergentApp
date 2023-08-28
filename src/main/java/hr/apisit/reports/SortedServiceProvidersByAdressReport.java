package hr.apisit.reports;

import hr.apisit.domain.ServiceProvider;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SortedServiceProvidersByAdressReport {

    public static void sortedServiceProvidersByAddress(List<ServiceProvider> serviceProviderList) {
//                    ovo dela, al s duplim zapisima
//                    serviceProviderList.sort(Comparator.comparing(ServiceProvider::getAdresa));

        Set<String> nameSet = new HashSet<>();
        //TODO FILTER PATTERN
        List<ServiceProvider> providersDistinctByName = serviceProviderList.stream()
                .filter(e -> nameSet.add(e.getNaziv()))
                .collect(Collectors.toList());
        providersDistinctByName.sort(Comparator.comparing(ServiceProvider::getAdresa));
        for (ServiceProvider s : providersDistinctByName) {
            System.out.println("->" + s.getNaziv() + ", " + s.getAdresa());
        }
        System.out.println("\n");

    }
}
