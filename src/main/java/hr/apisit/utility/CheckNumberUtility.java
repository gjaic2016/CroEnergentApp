package hr.apisit.utility;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public final class CheckNumberUtility {

    public static <T extends Number> T checkNumericInput(Class<T> numberType, Scanner input, String inputMessage, String errorMessage){

        while(true) {
            System.out.print(inputMessage);
            try{
                if (numberType.equals(Integer.class)) {
                    T number = numberType.cast(input.nextInt());
                    input.nextLine();
                    return number;
                }
                else if (numberType.equals(Long.class)) {
                    T number = numberType.cast(input.nextLong());
                    input.nextLine();
                    return number;
                }
                else if(numberType.equals(BigDecimal.class)) {
                    T number = numberType.cast(input.nextBigDecimal());
                    input.nextLine();
                    return number;
                }

            } catch(InputMismatchException ex){
                System.out.println(errorMessage);
                input.nextLine();
            }

        }

    }

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
