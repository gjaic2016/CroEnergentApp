package hr.apisit.domain;

import java.time.LocalDate;

public sealed interface FixedTerm permits FixedTermContract{

    void setContractStart(LocalDate startDate);

    void setContractEnd(LocalDate endDate);

}
