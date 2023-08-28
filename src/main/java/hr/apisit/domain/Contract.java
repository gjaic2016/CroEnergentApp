package hr.apisit.domain;

public abstract class Contract {

    private Integer id;

    private String tip;

    private ServiceProvider pruzateljUsluge;

    private Household kucanstvo;

    public Contract(){}

    public Contract(Integer id, String tip, ServiceProvider pruzateljUsluge, Household kucanstvo) {
        this.id = id;
        this.tip = tip;
        this.pruzateljUsluge = pruzateljUsluge;
        this.kucanstvo = kucanstvo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
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

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", tip='" + tip + '\'' +
                ", pruzateljUsluge=" + pruzateljUsluge +
                ", kucanstvo=" + kucanstvo +
                '}';
    }
}
