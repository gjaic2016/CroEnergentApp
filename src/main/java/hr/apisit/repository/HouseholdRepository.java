package hr.apisit.repository;

import hr.apisit.domain.Contract;
import hr.apisit.domain.FixedTermContract;
import hr.apisit.domain.Household;
import hr.apisit.domain.Owner;
import hr.apisit.utility.LocalDateUtility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HouseholdRepository {

    public static final Integer NUMBER_OF_HOUSEHOLD_LINES = 4;

    private OwnerRepository ownerRepository;

    public HouseholdRepository() {
        this.ownerRepository = new OwnerRepository();
    }

    public List<Household> readAllHouseholds() throws IOException {

        List<Household> householdList = new ArrayList<>();

        Path stageFilePath = Path.of("dat/households.txt");
        List<String> lines = Files.readAllLines(stageFilePath);

        for (int i = 0; i < lines.size() / NUMBER_OF_HOUSEHOLD_LINES; i++) {

            Integer householdId = Integer.parseInt(lines.get(i * NUMBER_OF_HOUSEHOLD_LINES));
            String householdAddress = lines.get(i * NUMBER_OF_HOUSEHOLD_LINES + 1);

            Integer householdOwnerId = Integer.parseInt(lines.get(i * NUMBER_OF_HOUSEHOLD_LINES + 2));
                Owner householdOwner = ownerRepository.readById(householdOwnerId);

            String householdContractIds = lines.get(i * NUMBER_OF_HOUSEHOLD_LINES + 3);
                String[] householdContractsIdArray = householdContractIds.split("\\s+");

                List<Contract> householdContractsList = new ArrayList<>();

                for (String contractId : householdContractsIdArray) {
                    Integer setId = Integer.parseInt(contractId);
                    //TODO iscupat contract is datoteke
//                    Contract contract =
                    //TODO OVDJE JE PROBLEM, treba unjet contract (abstract class)
                            //TODO koji jos ne postoji, a moze biti fixed ili indefinite
//                    householdContractsList.add(setId);
                }

            Household newHousehold = new Household(householdId, householdAddress, householdOwner, householdContractsList);
            householdList.add(newHousehold);
        }
        return householdList;
    }

    public Household readById(Integer id) throws IOException {
        return readAllHouseholds().stream()
                .filter(owner -> owner.getId().equals(id))
                .findFirst()
                .get();
    }

}
