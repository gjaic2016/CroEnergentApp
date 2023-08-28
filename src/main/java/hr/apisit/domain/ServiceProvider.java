package hr.apisit.domain;

import java.math.BigDecimal;

public class ServiceProvider implements Provider{

    private final Integer id;
    private final String naziv;
    private final String adresa;
    private final ServiceType vrstaUsluge;
    private final BigDecimal cijenaUsluge;



    //TODO SINGLETON PATTERN EXAMPLE
//    private static ServiceProvider instance;
//
//    public static ServiceProvider getInstance() {
//        if (instance == null) {
//            instance = new ServiceProvider();
//        }
//        return instance;
//    }
//    private ServiceProvider() {
//
//    }

//    ORIGINAL
    //TODO SERVICE PROVIDER BUILDER EXAMPLE
    public ServiceProvider(Integer id, String naziv, String adresa, ServiceType vrstaUsluge, BigDecimal cijenaUsluge) {
        this.id = id;
        this.naziv = naziv;
        this.adresa = adresa;
        this.vrstaUsluge = vrstaUsluge;
        this.cijenaUsluge = cijenaUsluge;
    }

    public Integer getId() {
        return id;
    }

//    public void setId(Integer id) {
//        this.id = id;
//    }

    public String getNaziv() {
        return naziv;
    }

//    public void setNaziv(String naziv) {
//        this.naziv = naziv;
//    }

    public String getAdresa() {
        return adresa;
    }

//    public void setAdresa(String adresa) {
//        this.adresa = adresa;
//    }

    public ServiceType getVrstaUsluge() {
        return vrstaUsluge;
    }

//    public void setVrstaUsluge(ServiceType vrstaUsluge) {
//        this.vrstaUsluge = vrstaUsluge;
//    }

    public BigDecimal getCijenaUsluge() {
        return cijenaUsluge;
    }

//    public void setCijenaUsluge(BigDecimal cijenaUsluge) {
//        this.cijenaUsluge = cijenaUsluge;
//    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public void testService() {
        System.out.println("Testing @RegularServiceProvider....");
    }
}
