package hr.apisit.utility;

import hr.apisit.domain.ServiceProvider;
import hr.apisit.domain.ServiceType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import static hr.apisit.utility.CheckNumberUtility.checkNumericInput;

public class UpdateServiceProviderUtility {

    public static void updateServiceProvider(Scanner scanner, List<ServiceProvider> serviceProviderList){

        System.out.println("AŽURIRANJE PRUŽATELJA USLUGA");

        System.out.println("Unesite id pružatelja usluge koji želite ažurirati: ");
        Integer serviceProviderId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Unesite novi naziv pruzatelja usluga: ");
        String newProviderName = scanner.nextLine();

        System.out.print("Unesite novu adresu pruzatelja usluga: ");
        String newProviderAddress = scanner.nextLine();

        System.out.println("Unesite novi tip usluge pruzatelja usluga. ");
        ServiceType newProviderService = CreateServiceProviderUtility.serviceSelection(scanner);

        BigDecimal newProviderPrice = checkNumericInput(BigDecimal.class, scanner,
                "Unesite cijenu usluge pruzatelja usluga sa DECIMALNIM ZAREZOM: ",
                "Neispravan unos broja, pokusajte ponovo!");

        ServiceProvider updatedServiceProvider = new ServiceProvider(serviceProviderId, newProviderName, newProviderAddress, newProviderService, newProviderPrice);

        for (ServiceProvider sp : serviceProviderList) {
            if(serviceProviderId.equals(sp.getId())){
                serviceProviderList.set(serviceProviderId, updatedServiceProvider);
            }

        }
        System.out.println("Ažuriran pružatelj");

    }
}
