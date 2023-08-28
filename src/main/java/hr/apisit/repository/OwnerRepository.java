package hr.apisit.repository;

import hr.apisit.domain.Contract;
import hr.apisit.domain.Household;
import hr.apisit.domain.Owner;
import hr.apisit.utility.LocalDateUtility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OwnerRepository {

    public static final Integer NUMBER_OF_OWNER_DATA_LINES = 5;

    public static final String OWNER_FILE_NAME = "dat/owners.txt";

    public static List<Owner> readAllOwners() throws IOException {

        List<Owner> ownerList = new ArrayList<>();

        Path stageFilePath = Path.of("dat/owners.txt");

        List<String> lines = Files.readAllLines(stageFilePath);

        for (int i = 0; i < lines.size() / NUMBER_OF_OWNER_DATA_LINES; i++) {

            Integer ownerId = Integer.parseInt(lines.get(i * NUMBER_OF_OWNER_DATA_LINES));
            String ownerIme = lines.get(i * NUMBER_OF_OWNER_DATA_LINES + 1);
            String ownerPrezime = lines.get(i * NUMBER_OF_OWNER_DATA_LINES + 2);
            String ownerDatumRodenjaString = lines.get(i * NUMBER_OF_OWNER_DATA_LINES + 3);
            LocalDate ownerDatumRodenja =
                    LocalDateUtility.convertStringToLocalDate(
                            ownerDatumRodenjaString);
            String ownerOib = lines.get(i * NUMBER_OF_OWNER_DATA_LINES + 4);

            Owner newOwner = new Owner(ownerId, ownerIme, ownerPrezime, ownerDatumRodenja, ownerOib);
            ownerList.add(newOwner);
        }
        return ownerList;
    }

    public Owner readById(Integer id) throws IOException {
        return readAllOwners().stream()
                .filter(owner -> owner.getId().equals(id))
                .findFirst()
                .get();
    }

    public static void writeOwnerToFile(List<Owner> ownerList) throws IOException {

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(OWNER_FILE_NAME));
        //id
        //ime
        //prezime
        //datumrodenja
        //oib
        System.out.println("Writing owner......\n");
        for (Owner owner : ownerList) {
            bufferedWriter.write(owner.getId().toString());
            bufferedWriter.newLine();
            bufferedWriter.write(owner.getIme().toString());
            bufferedWriter.newLine();
            bufferedWriter.write(owner.getPrezime().toString());
            bufferedWriter.newLine();
            bufferedWriter.write(LocalDateUtility.convertlocalDateToString(owner.getDatumRodenja()));
            bufferedWriter.newLine();
            bufferedWriter.write(owner.getOib().toString());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }

    public static void writeOwnerToFileThread(List<Owner> ownerList) throws IOException {

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("dat/owners.txt"));
        //id
        //ime
        //prezime
        //datumrodenja
        //oib
        System.out.println("Writing owner......\n");
        for (Owner owner : ownerList) {
            bufferedWriter.write(owner.getId().toString());
            bufferedWriter.newLine();
            bufferedWriter.write(owner.getIme().toString());
            bufferedWriter.newLine();
            bufferedWriter.write(owner.getPrezime().toString());
            bufferedWriter.newLine();
            bufferedWriter.write(LocalDateUtility.convertlocalDateToString(owner.getDatumRodenja()));
            bufferedWriter.newLine();
            bufferedWriter.write(owner.getOib().toString());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }

}
