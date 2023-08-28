package hr.apisit.utility;

import hr.apisit.domain.ServiceProvider;
import hr.apisit.domain.ServiceType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import static hr.apisit.utility.CheckNumberUtility.checkNumericInput;

public class CreateServiceProviderUtility {

    public static List<ServiceProvider> enterServiceProvider(Scanner scanner, List<ServiceProvider> serviceProviderList) {

        System.out.println("DODAVANJE PRUŽATELJA USLUGA");

        Integer providerId = serviceProviderList.size() + 1;
        System.out.println("Unesite novog pružatelja usluge pod ID: " + providerId);



        System.out.print("Unesite naziv pruzatelja usluga: ");
        String providerName = scanner.nextLine();

        System.out.print("Unesite adresu pruzatelja usluga: ");
        String providerAddress = scanner.nextLine();

        System.out.println("Unesite tip usluge pruzatelja usluga. ");
        ServiceType providerService = serviceSelection(scanner);

        System.out.println("Odabrano: " + providerService);

        BigDecimal providerCijena = checkNumericInput(BigDecimal.class, scanner,
                "Unesite cijenu usluge pruzatelja usluga sa DECIMALNIM ZAREZOM: ",
                "Neispravan unos broja, pokusajte ponovo!");

        ServiceProvider singleServiceProvider = new ServiceProvider(providerId, providerName, providerAddress, providerService, providerCijena);

        serviceProviderList.add(singleServiceProvider);

        return serviceProviderList;

    }


    public static ServiceType serviceSelection(Scanner scanner) {
        ServiceType serviceTypeSelection = null;
        int serviceSelectionNumber = 0;
        do {
            System.out.println("Odaberite vrstu usluge koju pruža pružatelj usluge: ");
            Integer counter = 1;

            for (ServiceType service : ServiceType.values()) {
                System.out.println(counter + " - " + service.getServiceName());
                counter++;
            }
            System.out.print("Odaberite redni broj usluge: ");
            serviceSelectionNumber = scanner.nextInt();
            scanner.nextLine();
        } while (serviceSelectionNumber < 1 || serviceSelectionNumber > ServiceType.values().length);
        serviceTypeSelection = ServiceType.values()[serviceSelectionNumber - 1];
        return serviceTypeSelection;
    }

}
