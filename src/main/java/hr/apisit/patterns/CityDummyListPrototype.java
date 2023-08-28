package hr.apisit.patterns;

import hr.apisit.domain.City;

import java.util.ArrayList;
import java.util.List;

//TODO PROTOTYPE

public class CityDummyListPrototype implements Cloneable{

    private static List<City> cityList = new ArrayList<>();

    static {
        City city1 = new City(1, "Zagreb", "10000");
        City city2 = new City(2, "Osijek", "31000");
        City city3 = new City(3, "Rijeka", "51000");
        City city4 = new City(4, "Split", "21000");
        City city5 = new City(5, "Zadar", "23000");
        cityList.add(city1);
        cityList.add(city2);
        cityList.add(city3);
        cityList.add(city4);
        cityList.add(city5);
    }

    public List<City> getAllCities() {
        return cityList;
    }

    //CLONING
    @Override
    public List<City> clone() throws CloneNotSupportedException {

        List<City> clonedCityList = new ArrayList<>();
        for (City city : cityList){
            clonedCityList.add(city);
        }

        return clonedCityList;
    }
}
