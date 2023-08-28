package hr.apisit.domain;

public abstract class Realty implements RealtyType {

    public  RealtyType realtyType;
    private String adresa;

    public Realty(String adresa, RealtyType realtyType) {
        this.adresa = adresa;
        this.realtyType = realtyType;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Override
    public void realtyType() {

    }
}
