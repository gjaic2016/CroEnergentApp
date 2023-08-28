package hr.apisit.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.Scanner;

public class LocalDateUtility {

    public static final String DEFAULT_DATE_FORMAT = "dd.MM.yyyy.";

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);

    public static LocalDate convertStringToLocalDate(String localDateString) {
        return LocalDate.parse(localDateString, formatter);

    }

    public static LocalDate checkLocalDateEntry(Scanner scanner, String inputMessage, String errorMessage){

        boolean wrongInput = true;

        Optional<LocalDate> localdate = Optional.empty();

        while (wrongInput) {
            System.out.print(inputMessage);
            String inputDate = scanner.nextLine();
            try {
                localdate = Optional.of(LocalDate.parse(inputDate, formatter));
                wrongInput = false;
            } catch (DateTimeParseException ex) {
                System.out.println(errorMessage);
            }
        }
        return localdate.get();
    }

    public static String convertlocalDateToString(LocalDate localDate){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
        String formattedString = localDate.format(formatter);

        return formattedString;
    }
}
