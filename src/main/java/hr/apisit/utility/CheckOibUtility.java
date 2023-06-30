package hr.apisit.utility;

import java.util.Scanner;

public class CheckOibUtility {

    public static String checkOwnerOib(Scanner scanner, String inputMessage) {
        String ownerOib;
        boolean check = true;
        do {
            System.out.print(inputMessage);
            ownerOib = scanner.nextLine();
            check = checkOIBState(ownerOib);
        } while (!check);
        return ownerOib;
    }

    public static boolean checkOIBState(String oib) {
        if (oib.length() != 11)
            return false;
        return true;
    }
}
