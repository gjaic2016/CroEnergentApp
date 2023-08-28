package hr.apisit.utility;

import hr.apisit.domain.*;
import hr.apisit.facade.VendorFacade;
import hr.apisit.factory.ContractFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateContractUtility {

    public static List<Contract> enterContract(Scanner scanner, List<ServiceProvider> serviceProviderList,
                                                List<Household> householdList, List<Contract> contractsList) {

        Integer contractId = contractsList.size() + 1;
        System.out.println("Unesite novi ugovor pod ID: " + contractId);

        System.out.println("Odaberite broj željenog tipa ugovora: ");
        System.out.println("1 - FIXED (OGRANIČEN)");
        System.out.println("2 - INDEFINITE (NEOGRANIČEN)");

        String contractType;
        Integer contractTypeSelection = scanner.nextInt();
        scanner.nextLine();
        if (contractTypeSelection.equals(1)) {
            contractType = "FIXED";
        } else {
            contractType = "INDEFINITE";
        }

        ServiceProvider contractServiceProvider = serviceProviderSelection(scanner, serviceProviderList);

        System.out.print("Odaberite kucanstvo za ugovor: ");
        Household contractHousehold = householdSelection(scanner, householdList);

       /* Contract newContract;
        if (contractType.equals("FIXED")) {
            LocalDate contractStart = LocalDateUtility.checkLocalDateEntry(scanner,
                    "Upišite datum početka ugovora: ",
                    "Unesen krivi format datuma, ponovite unos!");

            LocalDate contractEnd = LocalDateUtility.checkLocalDateEntry(scanner,
                    "Upišite datum završetka ugovora: ",
                    "Unesen krivi format datuma, ponovite unos!");

            newContract = new FixedTermContract(contractId, contractType, contractServiceProvider, contractHousehold,
                    contractStart, contractEnd);

            contractsList.add(newContract);
        } else {
            LocalDate contractStart = LocalDateUtility.checkLocalDateEntry(scanner,
                    "Upišite datum početka ugovora: ",
                    "Unesen krivi format datuma, ponovite unos!");

            newContract = new IndefiniteContract(contractId, contractType, contractServiceProvider, contractHousehold, contractStart);
            contractsList.add(newContract);
        }*/

        //TODO CONTRACT FACTORY PATTERN EXAMPLE
        ContractFactory contractFactory = new ContractFactory();
        Contract newContractFactoryObject = contractFactory.createContract(scanner, contractId, contractType, contractServiceProvider,
                contractHousehold);
        contractsList.add(newContractFactoryObject);

        //TODO FACADE

        VendorFacade vendor = new VendorFacade(contractServiceProvider, contractHousehold);
        vendor.signContract();

        //TODO ADAPTER za export ugovora pdf vs word document???

        Household householdOfContract = null;
        for (Household house : householdList) {
            if (house.getId().equals(contractHousehold.getId())) {
                householdOfContract = house;
            }
        }
//        householdOfContract.getUgovor().add(newContract);


        householdOfContract.getUgovor().add(newContractFactoryObject);

        return contractsList;
    }

    public static Household householdSelection(Scanner scanner, List<Household> householdList) {
        Household householdSelection = null;
        int householdSelectionNumber = 0;

        do {
            System.out.println("Odaberite kucanstvo: ");
            Integer counter = 1;

            for (Household h : householdList) {
                System.out.println(counter + " - " + h.getAdresa() + ", "
                        + h.getVlasnik().getPrezime() + " " + h.getVlasnik().getIme());
                counter++;
            }

            System.out.print("Odaberite redni broj: ");
            householdSelectionNumber = scanner.nextInt();
            scanner.nextLine();
        } while (householdSelectionNumber < 1 || householdSelectionNumber > householdList.size());
        householdSelection = householdList.get(householdSelectionNumber - 1);
        return householdSelection;
    }

    public static ServiceProvider serviceProviderSelection(Scanner scanner, List<ServiceProvider> serviceProviderList) {
        ServiceProvider serviceProviderSelection = null;
        int providerSelectionNumber = 0;

        do {
            System.out.println("Odaberite pružatelja usluge: ");
            Integer counter = 1;

            for (ServiceProvider s : serviceProviderList) {
                System.out.println(counter + " - " + s.getNaziv() + " " + s.getAdresa());
                counter++;
            }

            System.out.print("Odaberite redni broj: ");
            providerSelectionNumber = scanner.nextInt();
            scanner.nextLine();
        } while (providerSelectionNumber < 1 || providerSelectionNumber > serviceProviderList.size());
        serviceProviderSelection = serviceProviderList.get(providerSelectionNumber - 1);
        return serviceProviderSelection;
    }

}
