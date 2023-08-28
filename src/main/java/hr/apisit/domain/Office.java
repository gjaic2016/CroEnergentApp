package hr.apisit.domain;

import java.sql.SQLOutput;
import java.util.List;

public class Office extends Realty{

    private Integer id;
    private Owner vlasnik;

    private List<Contract> ugovor;

    public Office(Integer id,String adresa, Owner vlasnik, List<Contract> ugovor, RealtyType realtyType) {
        super(adresa, realtyType);
        this.id = id;
        this.vlasnik = vlasnik;
        this.ugovor = ugovor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public void realtyType() {
       System.out.println("Novi ured. ");
       realtyType.realtyType();
    }
}
