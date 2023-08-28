package hr.apisit.reports;

import hr.apisit.domain.Contract;
import hr.apisit.domain.FixedTermContract;
import hr.apisit.domain.IndefiniteContract;

import java.util.List;

public class ContractTypePerServiceProviderReport {

    public static void contractTypePerServiceProvider(List<Contract> contractsList) {
//        Map<String, List<Contract>> contractTypePerServiceProvider = new HashMap<>();

        for (Contract c : contractsList) {
//                        String serviceProviderName = c.getPruzateljUsluge().getNaziv();
//                        contractTypePerServiceProvider.put(serviceProviderName, new ArrayList<>());
            System.out.println(c.getPruzateljUsluge().getNaziv());
            int fixed = 0;
            int indefinite = 0;

            for (Contract contract : c.getKucanstvo().getUgovor()) {
                if (contract instanceof FixedTermContract fixedContract) {
                    fixed++;
                } else if (contract instanceof IndefiniteContract indefiniteContract) {
                    indefinite++;
                }
            }
            System.out.println("FIXED: " + fixed);
            System.out.println("INDEFINITE: " + indefinite);
            System.out.println("\n");
        }
    }
}
