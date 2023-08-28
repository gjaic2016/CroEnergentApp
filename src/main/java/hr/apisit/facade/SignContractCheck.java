package hr.apisit.facade;

import hr.apisit.domain.Household;
import hr.apisit.domain.ServiceProvider;

public class SignContractCheck {

//    public boolean checkSignature(ServiceProvider sp){
//
//        if(sp instanceof ServiceProvider) {
//            System.out.println("Pružatelj usluge je potpisao ugovor.");
//            return true;
//        }
//        else {
//            System.out.println("Pružatelj usluge nije potpisao ugovor.");
//            return false;
//        }
//    }

    public <T> boolean checkSignature(Object object){

        if(object instanceof ServiceProvider) {
            System.out.println("Pružatelj usluge je potpisao ugovor.");
            return true;
        } else if (object instanceof Household){
            System.out.println("Kućanstvo je potpisao ugovor.");
            return true;
        }
        else {
            System.out.println("Pružatelj usluge nije potpisao ugovor.");
            return false;
        }
    }


}
