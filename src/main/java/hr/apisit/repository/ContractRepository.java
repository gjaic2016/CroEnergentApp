package hr.apisit.repository;

import hr.apisit.domain.*;
import hr.apisit.utility.LocalDateUtility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContractRepository {

    public static final Integer NUMBER_OF_CONTRACT_LINES = 6;

    public static final String CONTRACTS_FILE_NAME = "dat/testContracts.txt";

    public ContractRepository() {
    }

    public List<Contract> readAllContracts(List<Household> householdList, List<ServiceProvider> serviceProviderList) throws IOException {

        List<Contract> contractList = new ArrayList<>();

        Path stageFilePath = Path.of("dat/contracts.txt");

        List<String> lines = Files.readAllLines(stageFilePath);

        for (int i = 0; i < lines.size() / NUMBER_OF_CONTRACT_LINES; i++) {

            Integer contractId = Integer.parseInt(lines.get(i * NUMBER_OF_CONTRACT_LINES));             //line 0 id

            String contractType = lines.get(i * NUMBER_OF_CONTRACT_LINES + 1);                          //line 1 type

            Integer serviceProviderId = Integer.parseInt(lines.get(i * NUMBER_OF_CONTRACT_LINES + 2));  //line 2 SProvider

            ServiceProvider serviceProviderOfContract = null;
            for (ServiceProvider serviceProvider : serviceProviderList) {
                if (serviceProvider.getId().equals(serviceProviderId)) {
                    serviceProviderOfContract = serviceProvider;
                }
            }

            Integer householdId = Integer.parseInt(lines.get(i * NUMBER_OF_CONTRACT_LINES + 3));        //line 3 household

            Household householdOfContract = null;

            for (Household house : householdList) {
                if (house.getId().equals(householdId)) {
                    householdOfContract = house;
                }
            }

            Contract newContract;

            if (contractType.equals("FIXED")) {

                String contractStartString = lines.get(i * NUMBER_OF_CONTRACT_LINES + 4);
                LocalDate contractStart = LocalDateUtility.convertStringToLocalDate(contractStartString);

                String contractEndString = lines.get(i * NUMBER_OF_CONTRACT_LINES + 5);
                LocalDate contractEnd = LocalDateUtility.convertStringToLocalDate(contractEndString);


                newContract = new FixedTermContract(contractId, contractType, serviceProviderOfContract, householdOfContract,
                        contractStart, contractEnd);
            } else {
                String contractStartString = lines.get(i * NUMBER_OF_CONTRACT_LINES + 4);
                LocalDate contractStart = LocalDateUtility.convertStringToLocalDate(contractStartString);

                newContract = new IndefiniteContract(contractId, contractType, serviceProviderOfContract, householdOfContract, contractStart);
            }

            householdOfContract.getUgovor().add(newContract);

            contractList.add(newContract);
        }

        return contractList;
    }

//    public Contract readById(Integer id) throws IOException {
//        Contract contract1 = readAllContracts().stream()
//                .filter(contract -> contract.getId().equals(id))
//                .findFirst()
//                .get();
//        return contract1;
//    }

    public void writeContractToFile(List<Contract> contractList) throws IOException {

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(CONTRACTS_FILE_NAME));
        //id
        //type
        //providerId
        //householdId
        //fixed term (startDate, endDate) || indefinite (startDate)
        System.out.println("Writing contract to file......\n");
        for (Contract contract : contractList) {
            bufferedWriter.write(contract.getId().toString());
            bufferedWriter.newLine();
            bufferedWriter.write(contract.getTip().toString());
            bufferedWriter.newLine();
            bufferedWriter.write(contract.getPruzateljUsluge().getId().toString());
            bufferedWriter.newLine();
            bufferedWriter.write(contract.getKucanstvo().getId().toString());
            bufferedWriter.newLine();
            if (contract instanceof FixedTermContract) {
                bufferedWriter.write(LocalDateUtility.convertlocalDateToString(((FixedTermContract) contract).getContractStart()));
                bufferedWriter.newLine();
                bufferedWriter.write(LocalDateUtility.convertlocalDateToString(((FixedTermContract) contract).getContractEnd()));
                bufferedWriter.newLine();
            } else if (contract instanceof IndefiniteContract) {
                bufferedWriter.write(LocalDateUtility.convertlocalDateToString(((IndefiniteContract) contract).getContractStart()));
                bufferedWriter.newLine();
                bufferedWriter.newLine();
            }
        }
        bufferedWriter.close();
    }

}
