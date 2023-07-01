package hr.apisit.domain;

import java.time.LocalDate;

public class FixedTermContract extends Contract implements FixedTerm{


    public FixedTermContract(LocalDate contractStart, LocalDate contractEnd) {
        this.contractStart = contractStart;
        this.contractEnd = contractEnd;
    }

    public FixedTermContract(Integer id, ServiceProvider pruzateljUsluge, Household kucanstvo, LocalDate contractStart, LocalDate contractEnd) {
        super(id, pruzateljUsluge, kucanstvo);
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
}
