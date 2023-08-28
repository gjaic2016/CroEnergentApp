package hr.apisit.utility;

import hr.apisit.domain.Contract;
import hr.apisit.domain.Household;
import hr.apisit.domain.Owner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateHouseholdUtility {

    public static List<Household> enterHousehold(Scanner scanner, List<Owner> ownerList, List<Household> householdList) {

        Integer householdId = householdList.size() + 1;
        System.out.println("Unesite novo kucanstvo pod ID: " + householdId);

        System.out.print("Unesite adresu kucanstva: ");
        String householdAddress = scanner.nextLine();

        System.out.print("Unesite vlasnika kucanstva: ");
        Owner householdOwner = ownerSelection(scanner, ownerList);

        List<Contract> householContractList = new ArrayList<>();

        householdList.add(new Household(householdId, householdAddress, householdOwner, householContractList));

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
}
