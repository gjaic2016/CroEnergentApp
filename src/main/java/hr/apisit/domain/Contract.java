package hr.apisit.domain;

public abstract class Contract {

    private Integer id;

    private ServiceProvider pruzateljUsluge;

    private Household kucanstvo;

    public Contract(){}

    public Contract(Integer id, ServiceProvider pruzateljUsluge, Household kucanstvo) {
        this.id = id;
        this.pruzateljUsluge = pruzateljUsluge;
        this.kucanstvo = kucanstvo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ServiceProvider getPruzateljUsluge() {
        return pruzateljUsluge;
    }

    public void setPruzateljUsluge(ServiceProvider pruzateljUsluge) {
        this.pruzateljUsluge = pruzateljUsluge;
    }

    public Household getKucanstvo() {
        return kucanstvo;
    }

    public void setKucanstvo(Household kucanstvo) {
        this.kucanstvo = kucanstvo;
    }
}
