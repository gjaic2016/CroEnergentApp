package hr.apisit.repository;

import hr.apisit.domain.*;
import hr.apisit.utility.LocalDateUtility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContractRepository {

    public static final Integer NUMBER_OF_CONTRACT_LINES = 6;

    private ServiceProviderRepository serviceProviderRepository;

    private HouseholdRepository householdRepository;

    public ContractRepository() {
        this.serviceProviderRepository = new ServiceProviderRepository();
        this.householdRepository = new HouseholdRepository();
    }

    public List<Contract> readAllContracts() throws IOException {

        List<Contract> contractList = new ArrayList<>();

        Path stageFilePath = Path.of("dat/contracts.txt");

        List<String> lines = Files.readAllLines(stageFilePath);

        for (int i = 0; i < lines.size() / NUMBER_OF_CONTRACT_LINES; i++) {

            Integer contractId = Integer.parseInt(lines.get(i * NUMBER_OF_CONTRACT_LINES));

            String contractType = lines.get(i * NUMBER_OF_CONTRACT_LINES + 1);

            Integer serviceProviderId = Integer.parseInt(lines.get(i * NUMBER_OF_CONTRACT_LINES + 2));
                ServiceProvider serviceProviderOfContract = serviceProviderRepository.readById(serviceProviderId);

            Integer householdId = Integer.parseInt(lines.get(i * NUMBER_OF_CONTRACT_LINES + 3));
                Household householdOfContract = householdRepository.readById(householdId);
            System.out.println(householdOfContract.getVlasnik().getIme());
                Contract newContract;

            if(contractType.equals("FIXED")) {
            String contractStartString = lines.get(i * NUMBER_OF_CONTRACT_LINES + 4);
                LocalDate contractStart = LocalDateUtility.convertStringToLocalDate(contractStartString);

            String contractEndString = lines.get(i * NUMBER_OF_CONTRACT_LINES + 5);
                LocalDate contractEnd = LocalDateUtility.convertStringToLocalDate(contractEndString);


                newContract = new FixedTermContract(contractId, serviceProviderOfContract, householdOfContract,
                        contractStart, contractEnd);
            }
            else {
                newContract = new IndefiniteContract(contractId, serviceProviderOfContract, householdOfContract);
            }

            contractList.add(newContract);
        }
        return contractList;
    }

}
