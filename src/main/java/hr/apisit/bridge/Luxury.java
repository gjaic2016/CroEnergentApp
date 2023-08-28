package hr.apisit.bridge;

import hr.apisit.domain.RealtyType;

public class Luxury implements RealtyType {

    @Override
    public void realtyType() {
        System.out.println("Nekretnina je luksuzan tip.");
    }
}
