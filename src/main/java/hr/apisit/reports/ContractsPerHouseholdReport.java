package hr.apisit.reports;

import hr.apisit.domain.Contract;
import hr.apisit.domain.Household;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ContractsPerHouseholdReport {

    public static void contractsPerHousehold(List<Household> householdList) {
        Map<Household, List<Contract>> householdContractMap = householdOwnersPrint(householdList);

        for (Household household : householdContractMap.keySet()) {
            System.out.println("Household: " + household.getAdresa());
            System.out.println("Contract: ");
            for (Contract contract : household.getUgovor()) {
                System.out.println("TIP: " + contract.getTip());
                System.out.println("Vlasnik: " + contract.getKucanstvo().getVlasnik().getPrezime()

                );
            }
        }
    }
    static Map<Household, List<Contract>> householdOwnersPrint(List<Household> householdList) {
        Map<Household, List<Contract>> householdContractMap = new TreeMap<>();

        for (Household household : householdList) {
            householdContractMap.put(household, household.getUgovor());
        }
        return householdContractMap;
    }

}
