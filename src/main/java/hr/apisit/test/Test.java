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



}
