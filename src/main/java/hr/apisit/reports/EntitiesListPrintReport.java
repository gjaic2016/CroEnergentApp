package hr.apisit.reports;

import hr.apisit.domain.*;
import hr.apisit.utility.LocalDateUtility;

import java.util.List;

public class EntitiesListPrintReport {

    public static void serviceProviderListPrint(List<ServiceProvider> serviceProviderList) {
        for (ServiceProvider s : serviceProviderList) {
            System.out.println(s.getNaziv() + ", " + s.getAdresa() + ", " + s.getVrstaUsluge() + ", " + s.getCijenaUsluge());
        }
        System.out.println("\n");
    }

    public static void ownerListPrint(List<Owner> ownerList) {
        for (Owner o : ownerList) {
            System.out.println(o.getIme() + ", " + o.getPrezime() + ", " + LocalDateUtility.convertlocalDateToString(o.getDatumRodenja()) + ", " + o.getOib());
        }
        System.out.println("\n");
    }

    public static void householdListPrint(List<Household> householdList) {
        for (Household h : householdList) {
            System.out.println("Id: " + h.getId() + ", " + "adresa kucanstva: " + h.getAdresa() + ", "
                    + "vlasnik kucanstva: " + h.getVlasnik().getIme() + " " + h.getVlasnik().getPrezime() + ", " + "ugovor: " + h.getUgovor());
        }
        System.out.println("\n");
    }

    public static void contractListPrint(List<Contract> contractsList) {

        for (Contract c : contractsList) {
            System.out.print("Id ugovora: " + c.getId() + ", "
                    + "pružatelj usluge: " + c.getPruzateljUsluge().getNaziv() + ", "
                    + "adresa kućanstva: " + c.getKucanstvo().getAdresa()
                    + ", vrsta usluge: " + c.getPruzateljUsluge().getVrstaUsluge().getServiceName() + ", "
                    + "tip ugovora: " + c.getTip() + ", "
            );
            c.getKucanstvo().getUgovor().forEach(contract -> {
                if (contract instanceof FixedTermContract fixedTermContract)
                    System.out.print("Start date: " + LocalDateUtility.convertlocalDateToString(fixedTermContract.getContractStart()) + " "
                            + ", end date: " + LocalDateUtility.convertlocalDateToString(fixedTermContract.getContractEnd())
                    );
                if (contract instanceof IndefiniteContract indefiniteContract)
                    System.out.print("Signed date: " + LocalDateUtility.convertlocalDateToString(indefiniteContract.getContractStart())
                    );
            });
        }
        System.out.println("\n");
    }
}
