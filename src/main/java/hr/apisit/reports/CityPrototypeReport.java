package hr.apisit.reports;

import hr.apisit.domain.City;
import hr.apisit.patterns.CityDummyListPrototype;

import java.util.List;

public class CityPrototypeReport {

    public static void cityPrototypeReport() {

        CityDummyListPrototype cityDummyList = new CityDummyListPrototype();
        List<City> allCities = cityDummyList.getAllCities();

        System.out.println("Original city list...");
        allCities.forEach(s -> System.out.println(s.getImeGrada() + " " + s.getPostanskiBroj()));
        System.out.println("------------------");


        try {
            List<City> updatedCityList = cityDummyList.clone();
//            City newCity = new City(6, "Dubrovnik", "20000");
//            updatedCityList.add(newCity);

            System.out.println("Cloned and Updated city list...");
            updatedCityList.forEach(s -> System.out.println(s.getImeGrada() + " " + s.getPostanskiBroj()));
            System.out.println("------------------");
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

}
