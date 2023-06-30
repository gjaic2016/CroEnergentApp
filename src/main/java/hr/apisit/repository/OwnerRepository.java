package hr.apisit.repository;

import hr.apisit.domain.Owner;
import hr.apisit.utility.LocalDateUtility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OwnerRepository {

    public static final Integer NUMBER_OF_OWNER_DATA_LINES = 5;

    public List<Owner> readAllOwners() throws IOException {

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

}
