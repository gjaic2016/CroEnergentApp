package hr.apisit.repository;

import hr.apisit.domain.Contract;
import hr.apisit.domain.FixedTermContract;
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

public class HouseholdRepository {

    public static final Integer NUMBER_OF_HOUSEHOLD_LINES = 4;

    public static final String HOUSEHOLD_FILE_NAME = "dat/testHousehold.txt";

    private OwnerRepository ownerRepository;

    public HouseholdRepository() {
        this.ownerRepository = new OwnerRepository();
    }

    public List<Household> readAllHouseholds() throws IOException {

        List<Household> householdList = new ArrayList<>();

        Path stageFilePath = Path.of("dat/households.txt");
        List<String> lines = Files.readAllLines(stageFilePath);

        for (int i = 0; i < lines.size() / NUMBER_OF_HOUSEHOLD_LINES; i++) {

            Integer householdId = Integer.parseInt(lines.get(i * NUMBER_OF_HOUSEHOLD_LINES));           //id
            String householdAddress = lines.get(i * NUMBER_OF_HOUSEHOLD_LINES + 1);                     //address

            Integer householdOwnerId = Integer.parseInt(lines.get(i * NUMBER_OF_HOUSEHOLD_LINES + 2));  //ownerid
                Owner householdOwner = ownerRepository.readById(householdOwnerId);

            List<Contract> householdContractsList = new ArrayList<>();                                  //contracts

            Household newHousehold = new Household(householdId, householdAddress, householdOwner, householdContractsList);
            householdList.add(newHousehold);
        }
        return householdList;
    }

//    public Household readById(Integer id) throws IOException {
//        return readAllHouseholds().stream()
//                .filter(household -> household.getId().equals(id))
//                .findFirst()
//                .get();
//    }

    public void writeHouseholdToFile(List<Household> householdList) throws IOException {

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(HOUSEHOLD_FILE_NAME));
        //id
        //adress
        //owner id
        //contract list
        System.out.println("Writing household......\n");
        for(Household household : householdList){
            bufferedWriter.write(household.getId().toString());
            bufferedWriter.newLine();
            bufferedWriter.write(household.getAdresa().toString());
            bufferedWriter.newLine();
            bufferedWriter.write(household.getVlasnik().getId().toString());
            bufferedWriter.newLine();
            for( Contract contract : household.getUgovor()){
                bufferedWriter.write(contract.getId().toString() + " ");
            }
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }
}
