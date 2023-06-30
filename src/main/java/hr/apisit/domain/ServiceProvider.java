package hr.apisit.domain;

import java.math.BigDecimal;

public class ServiceProvider {

    private Integer id;
    private String naziv;
    private String adresa;
    private Service_Type vrstaUsluge;
    private BigDecimal cijenaUsluge;

    public ServiceProvider(Integer id, String naziv, String adresa, Service_Type vrstaUsluge, BigDecimal cijenaUsluge) {
        this.id = id;
        this.naziv = naziv;
        this.adresa = adresa;
        this.vrstaUsluge = vrstaUsluge;
        this.cijenaUsluge = cijenaUsluge;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Service_Type getVrstaUsluge() {
        return vrstaUsluge;
    }

    public void setVrstaUsluge(Service_Type vrstaUsluge) {
        this.vrstaUsluge = vrstaUsluge;
    }

    public BigDecimal getCijenaUsluge() {
        return cijenaUsluge;
    }

    public void setCijenaUsluge(BigDecimal cijenaUsluge) {
        this.cijenaUsluge = cijenaUsluge;
    }
}
