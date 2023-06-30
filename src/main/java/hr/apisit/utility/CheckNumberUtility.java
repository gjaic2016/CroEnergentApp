package hr.apisit.utility;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public final class CheckNumberUtility {

    public static Integer checkIntegerInput(Scanner input, String inputMessage, String errorMessage) {
        Boolean wrongInput = true;
        Integer integerInput = 0;

        while (wrongInput) {
            System.out.print(inputMessage);
            try {
                integerInput = input.nextInt();
                wrongInput = false;
            } catch (InputMismatchException ex) {
                System.out.println(errorMessage);
                input.nextLine();
            }
        }

        return integerInput;
    }

    public static BigDecimal checkBigDecimalInput(Scanner input, String inputMessage, String errorMessage) {
        Boolean wrongInput = true;
        BigDecimal bigDecimalInput = new BigDecimal(0);

        while (wrongInput) {
            System.out.print(inputMessage);
            try {
                bigDecimalInput = input.nextBigDecimal();
                wrongInput = false;
            } catch (InputMismatchException ex) {
                System.out.println(errorMessage);
                input.nextLine();
            }
        }

        return bigDecimalInput;
    }

}
