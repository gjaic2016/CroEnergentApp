package hr.apisit.domain;

import java.time.LocalDate;

public sealed interface Indefinite permits IndefiniteContract{

    void signContract(LocalDate startDate);
}
