package hr.apisit.main;

import hr.apisit.bridge.Luxury;
import hr.apisit.bridge.Regular;
import hr.apisit.bridge.Retro;
import hr.apisit.decorator.ExternalServiceProviderDecorator;
import hr.apisit.decorator.InternalServiceProviderDecorator;
import hr.apisit.decorator.ServiceProviderDecorator;
import hr.apisit.domain.*;
import hr.apisit.reports.*;
import hr.apisit.repository.ContractRepository;
import hr.apisit.repository.HouseholdRepository;
import hr.apisit.repository.OwnerRepository;
import hr.apisit.repository.ServiceProviderRepository;
import hr.apisit.utility.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.*;
import static hr.apisit.utility.CheckNumberUtility.checkNumericInput;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        ServiceProviderRepository serviceProviderRepository = new ServiceProviderRepository();
        List<ServiceProvider> serviceProviderList = serviceProviderRepository.readAllServiceProviders();

        OwnerRepository ownerRepository = new OwnerRepository();
        List<Owner> ownerList = ownerRepository.readAllOwners();

        HouseholdRepository householdRepository = new HouseholdRepository();
        List<Household> householdList = householdRepository.readAllHouseholds();

        ContractRepository contractRepository = new ContractRepository();
        List<Contract> contractsList = contractRepository.readAllContracts(householdList, serviceProviderList);

        //TODO OBSERVER
        ServiceProviderStore store = new ServiceProviderStore();
        store.getService().subscribe(Event.CONTRACT_EXPIRATION_DATE_ALERT, new EmailListener("pero.peric@mail.com"));
        store.getService().subscribe(Event.CONTRACT_EXPIRATION_DATE_ALERT, new EmailListener("grga.grgic@mail.com"));
        store.getService().subscribe(Event.CONTRACT_EXPIRATION_DATE_ALERT, new EmailListener("ivo.ivic@mail.com"));
        store.getService().subscribe(Event.CONTRACT_PROMOTION_ALERT, new EmailListener("ivo.ivic@mail.com"));
        store.contractExpirationAlert();

        //TODO DECORATOR
        Provider testProvider = new ExternalServiceProviderDecorator(
                new InternalServiceProviderDecorator(
                new ServiceProvider(17,"BBC", "Ninska 2", ServiceType.ELECTRICITY_SUPPLY, new BigDecimal(12))
        ));
        testProvider.testService();

        //TODO COMMAND
        LocalDate birthdate = LocalDate.of(1966,5,12);
        Owner newOwner = new Owner(7, "Nosonja", "Smogovac", birthdate, "22222222222");
        newOwner.executeCommand();

        //TODO BRIDGE
        Realty newOffice = new Office(6, "Bibc 33", newOwner, contractsList,  new Retro());
        Realty newOffice2 = new Office(7, "brijunska 43", newOwner, contractsList,  new Regular());
        Realty newOffice3 = new Office(8, "Listopadska 3", newOwner, contractsList,  new Luxury());
        System.out.println("Novi office bridge");

        System.out.println(newOffice.getAdresa().toString() + ", "); newOffice.realtyType();
        System.out.println(newOffice2.getAdresa().toString()+ ", ");newOffice2.realtyType();
        System.out.println(newOffice3.getAdresa().toString()+ ", ");newOffice3.realtyType();

        menuSelection(scanner, serviceProviderList, ownerList, householdList, contractsList,
                serviceProviderRepository, ownerRepository, householdRepository, contractRepository);

        scanner.close();

    } //END MAIN

    static void menuPrint() {
        System.out.println("\n");
        System.out.println("------------------------------");
        System.out.println("------- AppCroEnergent -------");
        System.out.println("------------------------------");
        System.out.println("\n");
        System.out.println("Odaberite opciju");
        System.out.println("1. Unos pružatelja usluga");
        System.out.println("2. Unos vlasnika");
        System.out.println("3. Unos kučanstva");
        System.out.println("4. Unos ugovora");
        System.out.println("5. AŽuriranje pružatelja usluga");
        System.out.println("6. AŽuriranje vlasnika");
        System.out.println("7. AŽuriranje kučanstva");
        System.out.println("8. AŽuriranje ugovora");
        System.out.println("9. Pregled podataka, reporti");
        System.out.println("10. ** Izlaz **");
    }

    static void subMenuPrint() {
        System.out.println("Odaberite opciju...");
        System.out.println("1. Ispis pružatelja usluga");
        System.out.println("2. Ispis vlasnika");
        System.out.println("3. Ispis kučanstva");
        System.out.println("4. Ispis ugovora");
        System.out.println("5. Ispis sortiranih pružatelja usluga po adresi");
        System.out.println("6. DODATNI ZADATAK - Ispis grupiranih pružatelja usluga po tipu usluga");
        System.out.println("7. Ispis najskupljih usluga");
        System.out.println("8. Ispis tip ugovora po pružatelju usluga");
        System.out.println("9. Ispis ukupnih troskova po kucanstvu");
        System.out.println("10. <-- Nazad");
    }

    static void menuSelection(Scanner scanner, List<ServiceProvider> serviceProviderList, List<Owner> ownerList,
                              List<Household> householdList, List<Contract> contractsList,
                              ServiceProviderRepository serviceProviderRepository, OwnerRepository ownerRepository,
                              HouseholdRepository householdRepository, ContractRepository contractRepository ) throws IOException, CloneNotSupportedException {
        boolean wrongInput = true;
        Integer selection;
        do {
            menuPrint();

            selection = checkNumericInput(Integer.class, scanner,
                    "Odabir: ",
                    "Neispravan unos, molimo pokušajte ponovo!");

            switch (selection) {
                case 1:
                    System.out.println("1. Unos pružatelja usluga");
                    serviceProviderList = CreateServiceProviderUtility.enterServiceProvider(scanner, serviceProviderList);
                    serviceProviderRepository.writeServiceProviderToFile(serviceProviderList);
                    break;
                case 2:
                    System.out.println("2. Unos vlasnika");
                    ownerList = CreateOwnerUtility.enterOwner(scanner, ownerList);
//                    ownerRepository.writeOwnerToFile(ownerList);
                    break;
                case 3:
                    System.out.println("3. Unos kučanstva");
                    householdList = CreateHouseholdUtility.enterHousehold(scanner, ownerList, householdList);
//                    householdRepository.writeHouseholdToFile(householdList);
                    break;
                case 4:
                    System.out.println("4. Unos ugovora");
                    contractsList = CreateContractUtility.enterContract(scanner, serviceProviderList, householdList, contractsList);
//                    contractRepository.writeContractToFile(contractsList);
                    break;
                case 5:
                    System.out.println("5. AŽuriranje pružatelja usluga");
                    UpdateServiceProviderUtility.updateServiceProvider(scanner, serviceProviderList);
                    serviceProviderRepository.writeServiceProviderToFile(serviceProviderList);
                    break;
                case 6:
                    System.out.println("6. AŽuriranje vlasnika - USLUGA TRENUTNO NEDOSTUPNA");
                    break;
                case 7:
                    System.out.println("7. AŽuriranje kučanstva - USLUGA TRENUTNO NEDOSTUPNA");
                    break;
                case 8:
                    System.out.println("8. AŽuriranje ugovora - USLUGA TRENUTNO NEDOSTUPNA");
                    break;
                case 9:
                    System.out.println("\n");
                    System.out.println("9. Pregled podataka, reporti...\n");
                    subMenuSelection(scanner, serviceProviderList, ownerList, householdList, contractsList,householdRepository, ownerRepository);
                    break;
                case 10:
                    System.out.println("Izlaz");
                    wrongInput = false;
                    break;
                default:
                    System.out.println("Nevažeći odabir");
            } //END SWITCH

        } while (wrongInput); // END DO-WHILE
    }

    static void subMenuSelection(Scanner scanner, List<ServiceProvider> serviceProviderList, List<Owner> ownerList,
                                 List<Household> householdList, List<Contract> contractsList,
                                 HouseholdRepository householdRepository, OwnerRepository ownerRepository) throws IOException, CloneNotSupportedException {
        boolean wrongInputSubMenu = true;
        Integer selectionData;
        do {
            subMenuPrint();
            selectionData = CheckNumberUtility.checkIntegerInput(scanner,
                    "Odabir ispisa podataka: ",
                    "Neispravan unos, molimo pokušajte ponovo!");
            scanner.nextLine();
            System.out.println("-----------------------------");
            switch (selectionData) {
                case 1:
                    System.out.println("ISPIS PRUŽTALJA USLUGA\n");
                    EntitiesListPrintReport.serviceProviderListPrint(serviceProviderList);
                    break;
                case 2:
                    System.out.println("ISPIS VLASNIKA KUCANSTVA\n");
                    EntitiesListPrintReport.ownerListPrint(ownerList);
                    break;
                case 3:
                    System.out.println("ISPIS KUCANSTVA\n");
                    EntitiesListPrintReport.householdListPrint(householdList);
                    break;
                case 4:
                    System.out.println("ISPIS UGOVORA\n");
                    EntitiesListPrintReport.contractListPrint(contractsList);
                    break;
                case 5:
                    System.out.println("SORTIRANI PRUŽATELJI USLUGA PO ADRESI\n");
                    SortedServiceProvidersByAdressReport.sortedServiceProvidersByAddress(serviceProviderList);
                    break;
                case 6:
                    System.out.println("GRUPIRANI PRUŽATELJI USLUGA PO TIPU USLUGA\n");
                    GroupServiceProvidersByServiceTypeReport.groupedServiceProvidersByServiceType(serviceProviderList);
                    break;
                case 7:
                    System.out.println("ISPIS NAJSKUPLJIH USLUGA\n");
                    MostExpensiveServiceReport.mostExpensiveService(serviceProviderList);
                    break;
                case 8:
                    System.out.println("TIPOVI UGOVORA PO PRUZATELJU USLUGA\n");
                    ContractTypePerServiceProviderReport.contractTypePerServiceProvider(contractsList);
                    break;
                case 9:
                    System.out.println("UKUPNI TROŠKOVI PO KUCANSTVU\n");
                    TotalServiceCostPerHouseholdReport.totalServiceCostPerHousehold(householdList);
                    break;
                case 10:
                    //"10. <-- Nazad"
                    wrongInputSubMenu = false;
                    break;
                case 11:
                    ContractsPerHouseholdReport.contractsPerHousehold(householdList);       //test
                    break;
                case 12:
                    //TODO PROTOTYPE TEST
                    CityPrototypeReport.cityPrototypeReport();
                    break;
                case 13:
                    //thread practice trigger writing HOUSEHOLD
                    System.out.println("thread practice trigger writing HOUSEHOLD");
                    householdRepository.writeHouseholdToFile(householdList);
                    break;
                case 14:
                    //thread practice trigger writing OWNER
                    System.out.println("thread practice trigger writing OWNER");
                    ownerRepository.writeOwnerToFile(ownerList);
                    break;
                default:
                    System.out.println("Nevažeći odabir");
            }
        } while (wrongInputSubMenu);
    }
}