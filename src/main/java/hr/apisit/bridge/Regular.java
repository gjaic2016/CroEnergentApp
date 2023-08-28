package hr.apisit.bridge;

import hr.apisit.domain.RealtyType;

public class Regular implements RealtyType {
    @Override
    public void realtyType() {
        System.out.println("Nekretnina je regularan tip.");
    }
}
