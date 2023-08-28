package hr.apisit.factory;

import hr.apisit.domain.*;
import hr.apisit.utility.LocalDateUtility;

import java.time.LocalDate;
import java.util.Scanner;

public class ContractFactory {

    public Contract createContract(Scanner scanner, Integer contractId, String contractType,
                                   ServiceProvider contractServiceProvider, Household contractHousehold) {

        Contract newContract;
        /*switch(contractType){
            case "Fixed" -> {
                LocalDate contractStart = LocalDateUtility.checkLocalDateEntry(scanner,
                        "Upišite datum početka ugovora: ",
                        "Unesen krivi format datuma, ponovite unos!");

                LocalDate contractEnd = LocalDateUtility.checkLocalDateEntry(scanner,
                        "Upišite datum završetka ugovora: ",
                        "Unesen krivi format datuma, ponovite unos!");

                newContract = new FixedTermContract(contractId, contractType, contractServiceProvider, contractHousehold,
                        contractStart, contractEnd);
                }
            case "Indefinite" -> {
                LocalDate contractStart = LocalDateUtility.checkLocalDateEntry(scanner,
                        "Upišite datum početka ugovora: ",
                        "Unesen krivi format datuma, ponovite unos!");

                newContract = new IndefiniteContract(contractId, contractType, contractServiceProvider, contractHousehold, contractStart);
            }

        }*/

        if (contractType.equals("FIXED")) {

            LocalDate contractStart = LocalDateUtility.checkLocalDateEntry(scanner,
                    "Upišite datum početka ugovora: ",
                    "Unesen krivi format datuma, ponovite unos!");

            LocalDate contractEnd = LocalDateUtility.checkLocalDateEntry(scanner,
                    "Upišite datum završetka ugovora: ",
                    "Unesen krivi format datuma, ponovite unos!");

            newContract = new FixedTermContract(contractId, contractType, contractServiceProvider, contractHousehold,
                    contractStart, contractEnd);
        } else {
            LocalDate contractStart = LocalDateUtility.checkLocalDateEntry(scanner,
                    "Upišite datum početka ugovora: ",
                    "Unesen krivi format datuma, ponovite unos!");

            newContract = new IndefiniteContract(contractId, contractType, contractServiceProvider, contractHousehold, contractStart);
        }
        return newContract;
    }
}
