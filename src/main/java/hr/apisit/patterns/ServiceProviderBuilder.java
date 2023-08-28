package hr.apisit.patterns;

import hr.apisit.domain.ServiceProvider;
import hr.apisit.domain.ServiceType;

import java.math.BigDecimal;

public class ServiceProviderBuilder {
    private Integer id;
    private String naziv;
    private String adresa;
    private ServiceType vrstaUsluge;
    private BigDecimal cijenaUsluge;


//    public static ServiceProviderBuilder newInstance(){
//        return new ServiceProviderBuilder();
//    }

    public ServiceProviderBuilder id(Integer id){
        this.id = id;
        return this;
    }
    public ServiceProviderBuilder naziv(String naziv){
        this.naziv = naziv;
        return this;
    }
    public ServiceProviderBuilder adresa(String adresa){
        this.adresa = adresa;
        return this;
    }
    public ServiceProviderBuilder vrstaUsluge(ServiceType vrstaUsluge){
        this.vrstaUsluge = vrstaUsluge;
        return this;
    }
    public ServiceProviderBuilder cijenaUsluge(BigDecimal cijenaUsluge){
        this.cijenaUsluge = cijenaUsluge;
        return this;
    }

    public ServiceProvider build(){
        return new ServiceProvider(id, naziv, adresa, vrstaUsluge, cijenaUsluge);
    }
}
