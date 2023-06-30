package hr.apisit.utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class LocalDateUtility {

    public static final String DEFAULT_DATE_FORMAT = "dd.MM.yyyy.";

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);

    public static LocalDate convertStringToLocalDate(String localDateString) {
        return LocalDate.parse(localDateString, formatter);
    }

    public static LocalDate checkLocalDateEntry(Scanner scanner, String inputMessage, String errorMessage){

        boolean wrongInput = true;
        LocalDate ownerDateOfBirth = null;

        while (wrongInput) {
            System.out.print(inputMessage);
            try {
                String inputDate = scanner.nextLine();
                ownerDateOfBirth = LocalDate.parse(inputDate, formatter);
                wrongInput = false;
            } catch (DateTimeParseException ex) {
                System.out.println(errorMessage);
            }
        }
        return ownerDateOfBirth;
    }
}
