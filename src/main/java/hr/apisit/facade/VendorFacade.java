package hr.apisit.facade;

import hr.apisit.domain.Household;
import hr.apisit.domain.ServiceProvider;

public class VendorFacade {

    ServiceProvider serviceProvider;
    Household household;
//    Owner owner;

    SignContractCheck signContractCheck;

    public VendorFacade(ServiceProvider serviceProvider, Household household) {
        this.serviceProvider = serviceProvider;
        this.household = household;
        signContractCheck = new SignContractCheck();
    }

    public void signContract(){
        System.out.println("---------------ovjera ugovora---------------");
        signContractCheck.checkSignature(serviceProvider);
        signContractCheck.checkSignature(household);
        System.out.println("Ugovor potpisan i ovjeren od svih ovjerenika.");
    }
}
