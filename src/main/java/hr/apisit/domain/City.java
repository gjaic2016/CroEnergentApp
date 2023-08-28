package hr.apisit.domain;

public class City {

    private Integer id;
    private String imeGrada;
    private String postanskiBroj;

    public City(Integer id, String imeGrada, String postanskiBroj) {
        this.id = id;
        this.imeGrada = imeGrada;
        this.postanskiBroj = postanskiBroj;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImeGrada() {
        return imeGrada;
    }

    public void setImeGrada(String imeGrada) {
        this.imeGrada = imeGrada;
    }

    public String getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(String postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    @Override
    public String toString() {
        return imeGrada;
    }
}


