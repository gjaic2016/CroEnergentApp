package hr.apisit.domain;

import java.util.ArrayList;
import java.util.List;

public class Household {

    private Integer id;
    private String adresa;
    private Owner vlasnik;
    private List<Contract> ugovor;

    public Household(Integer id, String adresa, Owner vlasnik, List<Contract> ugovor) {
        this.id = id;
        this.adresa = adresa;
        this.vlasnik = vlasnik;
//        this.ugovor = new ArrayList<>();
        this.ugovor = ugovor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Owner getVlasnik() {
        return vlasnik;
    }

    public void setVlasnik(Owner vlasnik) {
        this.vlasnik = vlasnik;
    }

    public List<Contract> getUgovor() {
        return ugovor;
    }

    public void setUgovor(List<Contract> ugovor) {
        this.ugovor = ugovor;
    }
}
