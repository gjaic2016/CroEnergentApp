package hr.apisit.domain;

import java.time.LocalDate;


public class Owner {

    private Integer id;
    private String ime;
    private String prezime;
    private LocalDate datumRodenja;
    private String oib;

    public Owner(Integer id, String ime, String prezime, LocalDate datumRodenja, String oib) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodenja = datumRodenja;
        this.oib = oib;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public LocalDate getDatumRodenja() {
        return datumRodenja;
    }

    public void setDatumRodenja(LocalDate datumRodenja) {
        this.datumRodenja = datumRodenja;
    }

    public String getOib() {
        return oib;
    }
    public void setOib(String oib) {
        this.oib = oib;
    }
}
