package hr.apisit.main;

import hr.apisit.domain.*;
import hr.apisit.repository.ContractRepository;
import hr.apisit.repository.HouseholdRepository;
import hr.apisit.repository.OwnerRepository;
import hr.apisit.repository.ServiceProviderRepository;
import hr.apisit.utility.CheckNumberUtility;
import hr.apisit.utility.CheckOibUtility;
import hr.apisit.utility.LocalDateUtility;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import java.util.*;

import static hr.apisit.utility.CheckNumberUtility.checkBigDecimalInput;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

//        menuPrint();

        //TODO menu selection
//        menuSelection(scanner);

        /** kreiranje pruzatelja/serviceprovider usluge*/
//       List<ServiceProvider> serviceProviderList = enterServiceProvider(scanner);

        /** ucitavanje serviceprovidera iz datoteke*/
        ServiceProviderRepository serviceProviderRepository = new ServiceProviderRepository();
        List<ServiceProvider> serviceProviderList = serviceProviderRepository.readAllServiceProviders();

        for (ServiceProvider s : serviceProviderList) {
            System.out.println(s.getNaziv() + ", " + s.getAdresa() + ", " + s.getVrstaUsluge() + ", " + s.getCijenaUsluge());
        }

        /** kreiranje vlasnika/ownera*/
//        List<Owner> ownerList = enterOwner(scanner);

        /** ucitavanje ownera iz datoteke*/
        OwnerRepository ownerRepository = new OwnerRepository();
        List<Owner> ownerList = ownerRepository.readAllOwners();

        for (Owner o : ownerList) {
            System.out.println(o.getIme() + ", " + o.getPrezime() + ", " + o.getDatumRodenja() + ", " + o.getOib());
        }

        /** kreiranje household, treba imat kreirane ownere za odabir ownera,listu contracta -> kreirati kontrakte on the fly  */
//        List<Household> householdList = enterHousehold(scanner, ownerList);

        /** kreiranje household/kucanstva iz datoteke*/
//        HouseholdRepository householdRepository = new HouseholdRepository();
//        List<Household> householdList = householdRepository.readAllHouseholds();
//
//        for (Household h : householdList) {
//            System.out.println("h.id: " +  h.getId() + ", "
//                    + "h.adresa: " +h.getAdresa() + ", "
//                    + "h.vlasnik" + h.getVlasnik().getId() + ", "
//                    + "h.ugovor: " + h.getUgovor());
//        }

        /** kreiranje ugovor, treba imat serviceProvider i household*/
        //TODO contract
        ContractRepository contractRepository = new ContractRepository();
        List<Contract> contractsList = contractRepository.readAllContracts();

        for (Contract c : contractsList) {
            System.out.println(c.getId() + ", "
                    + "Vlasnik: " + c.getKucanstvo().getVlasnik().getIme() + ", "
                    + c.getKucanstvo().getVlasnik().getPrezime() + ", "
                    + "Pruzatelj: " + c.getPruzateljUsluge().getNaziv() + ", "
                    + "Usluga: " + c.getPruzateljUsluge().getVrstaUsluge() + ", "
                    + "Cijena: " +c.getPruzateljUsluge().getCijenaUsluge());
        }

        scanner.close();
    }

    public static List<ServiceProvider> enterServiceProvider(Scanner scanner) {
        List<ServiceProvider> serviceProviderList = new ArrayList<>();

        Integer numberOfServiceProviders = CheckNumberUtility.checkIntegerInput(scanner,
                "Unesite broj pruzatelja usluga: ",
                "Neispravan unos prpozatelja usluga, pokusajte ponovo!");
        scanner.nextLine();

        System.out.println("numberOfServiceProviders: " + numberOfServiceProviders + "\n");

        for (int i = 1; i <= numberOfServiceProviders; i++) {
            System.out.println("------------------------------");
            Integer providerId = CheckNumberUtility.checkIntegerInput(scanner,
                    "Unesite id " + i + ". pruzatelja usluga: ",
                    "Neispravan unos id-a, pokusajte ponovo!");
            scanner.nextLine();

            System.out.print("Unesite naziv " + i + ". pruzatelja usluga: ");
            String providerName = scanner.nextLine();

            System.out.print("Unesite adresu " + i + ". pruzatelja usluga: ");
            String providerAddress = scanner.nextLine();

            System.out.println("Unesite tip usluge " + i + ". pruzatelja usluga. ");
            Service_Type providerService = serviceSelection(scanner);

            System.out.println("providerService--> " + providerService);

            BigDecimal providerCijena = checkBigDecimalInput(scanner,
                    "Unesite cijenu usluge " + i + ". pruzatelja usluga: ",
                    "Neispravan unos broja, pokusajte ponovo!");
            scanner.nextLine();

            serviceProviderList.add(new ServiceProvider(providerId, providerName, providerAddress, providerService, providerCijena));

        }

        return serviceProviderList;

    }

    private static List<Owner> enterOwner(Scanner scanner) {

        List<Owner> ownerList = new ArrayList<>();

        Integer numberOfOwners = CheckNumberUtility.checkIntegerInput(scanner,
                "Unesite broj vlasnika: ",
                "Neispravan unos broja vlasnika, molimo unesite pozitivan broj!");
        scanner.nextLine();

        for (int i = 1; i <= numberOfOwners; i++) {
            System.out.println("------------------------------");

            System.out.print("Unesite id " + i + ". vlasnika usluge: ");
            Integer ownerId = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Unesite ime " + i + ". vlasnika usluge: ");
            String ownerFirstName = scanner.nextLine();

            System.out.print("Unesite prezime " + i + ". vlasnika usluge: ");
            String ownerLastName = scanner.nextLine();

            LocalDate ownerDateOfBirth = LocalDateUtility.checkLocalDateEntry(scanner,
                    "Unesite datum rođenja " + i + ". vlasnika (u formatu DD.MM.YYYY.): ",
                    "Unesen krivi format datuma, ponovite unos!");

            String ownerOib = CheckOibUtility.checkOwnerOib(scanner,
                    "Unesite OIB " + i + ". vlasnika usluge (OIB mora sadržavati 11 znakova): ");

            ownerList.add(new Owner(ownerId, ownerFirstName, ownerLastName, ownerDateOfBirth, ownerOib));
        }

        return ownerList;
    }

    private static List<Household> enterHousehold(Scanner scanner,List<Owner> ownerList) {

        List<Household> householdList = new ArrayList<>();

        Integer numberOfHouseholds = CheckNumberUtility.checkIntegerInput(scanner,
                "Unesite broj kucanstava: ",
                "Neispravan unos broja kucanstava, molimo unesite ponovno!");
        scanner.nextLine();

        System.out.println("numberOfHouseholds ---> " + numberOfHouseholds);

        for (int i = 1; i <= numberOfHouseholds; i++) {
            System.out.println("------------------------------");

            System.out.print("Unesite id " + i + ". kucanstva: ");
            Integer householdId = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Unesite adresu " + i + ". kucanstva: ");
            String householdAddress = scanner.nextLine();

            System.out.print("Unesite vlasnika " + i + ". kucanstva: ");
            Owner householdOwner = ownerSelection(scanner, ownerList);

//            System.out.print("Unesite ugovore " + i + ". kucanstva: ");
            List<Contract> householContractList = null;

            householdList.add(new Household(householdId, householdAddress, householdOwner, householContractList));
        }


        return householdList;
    }

    public static Owner ownerSelection(Scanner scanner, List<Owner> ownerList) {

        Owner ownerSelection = null;
        int ownerSelectionNumber = 0;
        do {
            System.out.println("Odaberite vlasnika kucanstva : ");
            Integer counter = 1;

            for (Owner o : ownerList) {
                System.out.println(counter + " - " + o.getIme() + " " + o.getPrezime());
                counter++;
            }

            System.out.print("Odaberite redni broj vlasnika: ");
            ownerSelectionNumber = scanner.nextInt();
            scanner.nextLine();

        } while (ownerSelectionNumber < 1 || ownerSelectionNumber > ownerList.size());

        ownerSelection = ownerList.get(ownerSelectionNumber - 1);

        return ownerSelection;

    }

    public static Service_Type serviceSelection(Scanner scanner) {

        Service_Type serviceTypeSelection = null;
        int serviceSelectionNumber = 0;
        do {
            System.out.println("Odaberite vrstu usluge koju pruža pružatelj usluge: ");
            Integer counter = 1;

            for (Service_Type service : Service_Type.values()) {
                System.out.println(counter + " - " + service.getServiceName());
                counter++;
            }

            System.out.print("Odaberite redni broj usluge: ");
            serviceSelectionNumber = scanner.nextInt();
            scanner.nextLine();

        } while (serviceSelectionNumber < 1 || serviceSelectionNumber > Service_Type.values().length);

        serviceTypeSelection = Service_Type.values()[serviceSelectionNumber - 1];

        return serviceTypeSelection;
    }

    static void menuPrint() {
        System.out.println("------------------------------");
        System.out.println("------- AppCroEnergent -------");
        System.out.println("------------------------------");
        System.out.println("Odaberite opciju");
        System.out.println("1. Unos novih podataka");
        System.out.println("2. Ažuriranje podataka");
        System.out.println("3. Pregled podataka");
        System.out.println("4. Izlaz");
    }

    static void menuSelection(Scanner scanner) {

        int selection = scanner.nextInt();
        switch (selection) {
            case 1:
//                List<ServiceProvider> serviceProviderList = enterServiceProvider(scanner);
//                System.out.println("Unos novih podataka");
                break;
            case 2:
                System.out.println("Ažuriranje podataka");
                break;
            case 3:
                System.out.println("Pregled podataka");
                break;
            case 4:
                System.out.println("Izlaz");
                break;
            default:
                System.out.println("Nevažeći odabir");
        } //end switch
        scanner.nextLine();

    }

}