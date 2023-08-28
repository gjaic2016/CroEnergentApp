package hr.apisit.domain;

import java.time.LocalDate;

public non-sealed class FixedTermContract extends Contract implements FixedTerm{


    public FixedTermContract(LocalDate contractStart, LocalDate contractEnd) {
        this.contractStart = contractStart;
        this.contractEnd = contractEnd;
    }

    public FixedTermContract(Integer id, String tip, ServiceProvider pruzateljUsluge, Household kucanstvo, LocalDate contractStart, LocalDate contractEnd) {
        super(id, tip, pruzateljUsluge, kucanstvo);
        this.contractStart = contractStart;
        this.contractEnd = contractEnd;
    }

    private LocalDate contractStart;

    private LocalDate contractEnd;


    @Override
    public void setContractStart(LocalDate startDate) {
        this.contractStart = startDate;
    }

    @Override
    public void setContractEnd(LocalDate endDate) {
        this.contractStart = endDate;
    }

    public LocalDate getContractStart() {
        return contractStart;
    }

    public LocalDate getContractEnd() {
        return contractEnd;
    }

    @Override
    public String toString() {
        return "FixedTermContract{" +
                "contractStart=" + contractStart +
                ", contractEnd=" + contractEnd +
                '}';
    }
}
