package hr.apisit.main;

import hr.apisit.domain.*;
import hr.apisit.reports.*;
import hr.apisit.repository.ContractRepository;
import hr.apisit.repository.HouseholdRepository;
import hr.apisit.repository.OwnerRepository;
import hr.apisit.repository.ServiceProviderRepository;
import hr.apisit.thread.GroupedServiceProvidersByServiceTypeThread;
import hr.apisit.thread.MostExpensiveServiceThread;
import hr.apisit.thread.PrintOwnerThread;
import hr.apisit.thread.SortedServiceProvidersThread;
import hr.apisit.utility.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import static hr.apisit.utility.CheckNumberUtility.checkNumericInput;

public class MainThread {

    public static void main(String[] args) throws Exception {

        Lock lock = new ReentrantLock();

        Scanner scanner = new Scanner(System.in);

        ServiceProviderRepository serviceProviderRepository = new ServiceProviderRepository();
        List<ServiceProvider> serviceProviderList = serviceProviderRepository.readAllServiceProviders();

        OwnerRepository ownerRepository = new OwnerRepository();
        List<Owner> ownerList = ownerRepository.readAllOwners();

        HouseholdRepository householdRepository = new HouseholdRepository();
        List<Household> householdList = householdRepository.readAllHouseholds();

        ContractRepository contractRepository = new ContractRepository();
        List<Contract> contractsList = contractRepository.readAllContracts(householdList, serviceProviderList);

        //TODO Print owner thread

        var readjob = new Thread( () -> {
            while(true){
                lock.lock();
                try {
                    System.out.println("\n");
                    var updateOwners = OwnerRepository.readAllOwners();
                    System.out.println("--------------------------------");
                    System.out.println("--------------read-------------");
                    for (Owner o : updateOwners) {
                        System.out.println(o.getIme() + ", " + o.getPrezime() + ", "
                                + LocalDateUtility.convertlocalDateToString(o.getDatumRodenja()) + ", " + o.getOib());
                    }

                    Thread.sleep(2000);
                } catch (InterruptedException | IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
        });
        List<Owner> finalOwnerList = ownerList;

        var writejob = new Thread( () -> {
            while(true){
                lock.lock();
                try {
                    System.out.println("\n");
                    System.out.println("--------------------------------");
                    System.out.println("-------------write--------------");
                    OwnerRepository.writeOwnerToFileThread(finalOwnerList);
                    Thread.sleep(1000);
                } catch (InterruptedException | IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
        });

        readjob.start();
        writejob.start();

        //TODO threadovi rade
//        SortedServiceProvidersThread newThread = new SortedServiceProvidersThread(serviceProviderList);
//        new Thread(newThread).start();

//        GroupedServiceProvidersByServiceTypeThread newThread2 = new GroupedServiceProvidersByServiceTypeThread(serviceProviderList);
//        new Thread(newThread2).start();

        //TODO Future
//        Callable<String> task1 = () -> "Tekst test";          //Callable test
//
//        MostExpensiveServiceThread mostExpensiveServiceThread = new MostExpensiveServiceThread(serviceProviderList);
//        Callable<String> task2 = mostExpensiveServiceThread;
//
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        Future<String> future = executorService.submit(task2);
//
//        try {
//            System.out.println(future.get());
//        } catch (ExecutionException | InterruptedException e) {
//            e.printStackTrace();
//        }
//        if (executorService.isTerminated()) System.out.println("Finished");
//        else System.out.println("At least one task is still running");
//
//        executorService.awaitTermination(5, TimeUnit.SECONDS);


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
                    contractRepository.writeContractToFile(contractsList);
                    break;
                case 5:
                    System.out.println("5. AŽuriranje pružatelja usluga - USLUGA TRENUTNO NEDOSTUPNA");
                    updateServiceProvider(scanner, serviceProviderList);
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

        scanner.close();

    } //END MAIN

    static void updateServiceProvider(Scanner scanner, List<ServiceProvider> serviceProviderList){

        System.out.println("AŽURIRANJE PRUŽATELJA USLUGA");

        for (ServiceProvider sp : serviceProviderList) {

        }

    }

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

    static void subMenuSelection(Scanner scanner, List<ServiceProvider> serviceProviderList, List<Owner> ownerList,
                                 List<Household> householdList, List<Contract> contractsList,
                                 HouseholdRepository householdRepository, OwnerRepository ownerRepository) throws IOException {
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
                    serviceProviderListPrint(serviceProviderList);
                    break;
                case 2:
                    System.out.println("ISPIS VLASNIKA KUCANSTVA\n");
                    ownerListPrint(ownerList);
                    break;
                case 3:
                    System.out.println("ISPIS KUCANSTVA\n");
                    householdListPrint(householdList);
                    break;
                case 4:
                    System.out.println("ISPIS UGOVORA\n");
                    contractListPrint(contractsList);
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
//                    System.out.println("Izlaz...");
                    wrongInputSubMenu = false;
                    break;
                case 11:
                    ContractsPerHouseholdReport.contractsPerHousehold(householdList);       //test
                    break;
                case 12:

                    break;
                case 13:
                    householdRepository.writeHouseholdToFile(householdList);
                    break;
                case 14:
                    ownerRepository.writeOwnerToFile(ownerList);
                    break;
                default:
                    System.out.println("Nevažeći odabir");
            }
        } while (wrongInputSubMenu);
    }

    static void serviceProviderListPrint(List<ServiceProvider> serviceProviderList) {
        for (ServiceProvider s : serviceProviderList) {
            System.out.println(s.getNaziv() + ", " + s.getAdresa() + ", " + s.getVrstaUsluge() + ", " + s.getCijenaUsluge());
        }
    }

    static void ownerListPrint(List<Owner> ownerList) {
        for (Owner o : ownerList) {
            System.out.println(o.getIme() + ", " + o.getPrezime() + ", " + LocalDateUtility.convertlocalDateToString(o.getDatumRodenja()) + ", " + o.getOib());
        }
        System.out.println("\n");
    }

    static void householdListPrint(List<Household> householdList) {
        for (Household h : householdList) {
            System.out.println("Id: " + h.getId() + ", " + "adresa kucanstva: " + h.getAdresa() + ", "
                    + "vlasnik kucanstva: " + h.getVlasnik().getIme() + " " + h.getVlasnik().getPrezime() + ", " + "ugovor: " + h.getUgovor());
        }
        System.out.println("\n");
    }

    static void contractListPrint(List<Contract> contractsList) {
        //TODO format datuma na ispisu zeza
        for (Contract c : contractsList) {
            System.out.print("Id ugovora: " + c.getId() + ", "
                    + "pružatelj usluge: " + c.getPruzateljUsluge().getNaziv() + ", "
                    + "adresa kućanstva: " + c.getKucanstvo().getAdresa()
                    + ", vrsta usluge: " + c.getPruzateljUsluge().getVrstaUsluge().getServiceName() + ", "
                    + "tip ugovora: " + c.getTip() + ", "
            );
            c.getKucanstvo().getUgovor().forEach(contract -> {
                if (contract instanceof FixedTermContract fixedTermContract)
                    System.out.print(fixedTermContract.getContractStart() + " " + fixedTermContract.getContractEnd());
                if (contract instanceof IndefiniteContract indefiniteContract)
                    System.out.print(indefiniteContract.getContractStart());
            });
            System.out.println();
        }
    }

}