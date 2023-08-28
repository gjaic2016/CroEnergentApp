package hr.apisit.bridge;

import hr.apisit.domain.RealtyType;

public class Retro implements RealtyType {

    @Override
    public void realtyType() {
        System.out.println("Nekretnina je retro tip.");
    }
}
