package hr.apisit.test;

import org.w3c.dom.ls.LSOutput;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Test {

    public static void main(String[] args) {

    String inputData = "31.10.1999.";

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy.");
    LocalDate ownerDateOfBirth = LocalDate.parse(inputData, formatter);

        System.out.println(ownerDateOfBirth.format(formatter).toString());
    }


    //sortiranje service providera po adresama
//                    List<ServiceProvider> sortedProviders = serviceProviderList.stream()
//                            .sorted(Comparator.comparing(ServiceProvider::getAdresa))
//                            .collect(Collectors.toList());

//    test za git


   /* public static void writeToChangesFile(Manufacturer after, ReentrantLock lock) {
        lock.lock();
        try {
            FileWriter fileWriter = new FileWriter("dat/changes.txt", true);
            fileWriter.append(after.getName() + " - " + after.getCountry().getCountryName() + " " + LocalDateTime.now() + System.lineSeparator());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Dogodila se greška tijekom zapisivanja promjene u changes.txt");
        } finally {
            lock.unlock();
        }
    }*/

    /**
     * služi za čitanje sadržja dokumenta changes.txt
     *
     * @param lock Lock mehanizam koji služi za sinkronizaciju Threadova
     */
    /*public static void readFromChangesFile(ReentrantLock lock) {
        lock.lock();
        try {
            Path file = Path.of("dat/changes.txt");
            lock.unlock();
            List<String> stringList = new ArrayList<>();
            try {
                Scanner scanner = new Scanner(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            List<String> lines = null;
            try {
                lines = Files.readAllLines(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (int i = 0; i < lines.size(); i++) {
                String nameCountry = lines.get(i);
                stringList.add(nameCountry);
            }
            String lastEntry = stringList.get(stringList.size() - 1);

            System.out.println("Posljednja promjena je >> " + lastEntry);
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }*/

    /**
     * ažurira atribute odabranog objekta Manufacturer te zapisuje promjenu u datoteku manufacturers.txt, poziva metodu
     * readFromChangesFile() i writeToChangesFile()
     *
     * @param scanner
     * @param manufacturerList
     * @throws FileNotFoundException
     */
    /*public static void updateManufacturer(Scanner scanner, List<Manufacturer> manufacturerList) throws FileNotFoundException {
        ReentrantLock lock = new ReentrantLock();

        System.out.println("Kojeg proizvođača želite ažurirati?");
        while (true) {
            int i = 1;
            for (Manufacturer m : manufacturerList) {
                System.out.println(i + ".) " + m.getName() + " - " + m.getCountry().getCountryName());
                i++;
            }
            System.out.print("Unesite vaš odabir: ");
            Integer chocieManu = checkInputGeneric(scanner, Integer.class, "Unesite vaš odabir: ", "Niste unijeli odgovarajuć broj!");
            Manufacturer afterUpdate = manufacturerList.get(chocieManu - 1);
            System.out.println("Odabrani proizvođač sadrži sljedeće podatke: ");
            System.out.println("1.) " + afterUpdate.getCountry().getCountryName() + " 2.) " + afterUpdate.getName());
            Integer choiceSecond = checkInputGeneric(scanner, Integer.class, "Odaberite dio objekta: ", "Niste unijeli odgovarajuć broj!");
            if (choiceSecond.equals(1)) {
                System.out.println("Unesite novu vrijednost za zemmlju tvrtke");
                Integer newManufCountryLine = 0;
                newManufCountryLine = showCountries(scanner, newManufCountryLine);
                Country newManufCountry = Country.values()[newManufCountryLine - 1];
                afterUpdate.setCountry(newManufCountry);
                manufacturerList.set(chocieManu - 1, afterUpdate);
            } else if (choiceSecond.equals(2)) {
                System.out.print("Unesite novu vrijednost za naziv tvrtke: ");
                String newNameVal = scanner.nextLine();
                afterUpdate.setName(newNameVal);
                manufacturerList.set(chocieManu - 1, afterUpdate);
            }


            try {
                FileWriter fw = new FileWriter("dat/manufacturers.txt");
                Thread bookHand = new Thread(() -> {
                    for (int w = 0; w < 1; w++) {
                        writeToChangesFile(afterUpdate, lock);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                bookHand.start();
                for (Manufacturer m : manufacturerList) {
                    fw.write(m.getName() + " - " + m.getCountry().getCountryName().toUpperCase() + System.lineSeparator());
                }
                fw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println("linija je uspjesno promijenjena!");
            Thread bookWorm = new Thread(() -> {
                while (true) {
                    readFromChangesFile(lock);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            bookWorm.start();
            System.out.println("Želite li još što promijeniti?");
            String changeFlag = scanner.nextLine();
            if (changeFlag.toUpperCase().equals("DA")) {
                ;
            } else if (changeFlag.toUpperCase().equals("NE")) {
                break;
            } else {
                System.out.println("Krivo ste unijeli vrijednost za nastavak unosa!");
            }
        }
    }*/


}
