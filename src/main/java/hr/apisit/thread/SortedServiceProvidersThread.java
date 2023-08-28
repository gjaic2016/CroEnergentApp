package hr.apisit.thread;

import hr.apisit.domain.ServiceProvider;
import hr.apisit.repository.ServiceProviderRepository;

import java.util.*;
import java.util.stream.Collectors;

public class SortedServiceProvidersThread implements Runnable {


    List<ServiceProvider> serviceProviderList;

    public SortedServiceProvidersThread(List<ServiceProvider> serviceProviderList) {
        this.serviceProviderList = serviceProviderList;
    }

    @Override
    public void run() {

        while (true) {
            try {
                Set<String> nameSet = new HashSet<>();

                List<ServiceProvider> providersDistinctByName = serviceProviderList.stream()
                        .filter(e -> nameSet.add(e.getNaziv()))
                        .collect(Collectors.toList());
                providersDistinctByName.sort(Comparator.comparing(ServiceProvider::getAdresa));
                for (ServiceProvider s : providersDistinctByName) {
                    System.out.println("->" + s.getNaziv() + ", " + s.getAdresa());
                }
                System.out.println("\n");

                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
