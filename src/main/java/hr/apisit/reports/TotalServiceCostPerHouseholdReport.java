package hr.apisit.reports;

import hr.apisit.domain.Contract;
import hr.apisit.domain.Household;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TotalServiceCostPerHouseholdReport {

    public static void totalServiceCostPerHousehold(List<Household> householdList) {
//        ukupan trosak za podmirivanja troskova usluga za kucanstva
        Map<Household, List<Contract>> householdContractSumMap = householdOwnersPrint(householdList);
        for (Household household : householdContractSumMap.keySet()) {
            System.out.print("Household: " + household.getAdresa() + ", ");

            BigDecimal sumPrice = new BigDecimal(0);

            for (Contract contract : household.getUgovor()) {
                sumPrice = sumPrice.add(contract.getPruzateljUsluge().getCijenaUsluge());
            }
            System.out.println("Ukupan trošak iznosi: " + sumPrice + " Kn.");
        }
        System.out.println("\n");
    }

    static Map<Household, List<Contract>> householdOwnersPrint(List<Household> householdList) {
        Map<Household, List<Contract>> householdContractMap = new TreeMap<>();

        for (Household household : householdList) {
            householdContractMap.put(household, household.getUgovor());
        }
        return householdContractMap;
    }
}
