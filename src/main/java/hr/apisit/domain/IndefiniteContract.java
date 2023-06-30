package hr.apisit.domain;

public class IndefiniteContract extends Contract implements Indefinite{


    public IndefiniteContract() {
    }

    public IndefiniteContract(Integer id, ServiceProvider pruzateljUsluge, Household kucanstvo) {
        super(id, pruzateljUsluge, kucanstvo);
    }

    @Override
    public void signContract() {

    }
}
