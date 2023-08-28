package hr.apisit.utility;

import hr.apisit.domain.Owner;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class CreateOwnerUtility {

    public static List<Owner> enterOwner(Scanner scanner, List<Owner> ownerList) {

        Integer ownerId = ownerList.size() + 1;
        System.out.println("Unesite novog vlasnika usluge pod ID: " + ownerId);

        System.out.print("Unesite ime vlasnika usluge: ");
        String ownerFirstName = scanner.nextLine();

        System.out.print("Unesite prezime vlasnika usluge: ");
        String ownerLastName = scanner.nextLine();

        LocalDate ownerDateOfBirth = LocalDateUtility.checkLocalDateEntry(scanner,
                "Unesite datum rođenja vlasnika (u formatu DD.MM.YYYY.): ",
                "Unesen krivi format datuma, ponovite unos!");

        String ownerOib = CheckOibUtility.checkOwnerOib(scanner,
                "Unesite OIB vlasnika usluge (OIB mora sadržavati 11 znakova): ");

        ownerList.add(new Owner(ownerId, ownerFirstName, ownerLastName, ownerDateOfBirth, ownerOib));

        return ownerList;
    }
}
