package hr.apisit.domain;

import java.util.List;
import java.util.Map;

public class Holding {

    private Map<Service_Type,List<ServiceProvider>> mapaUsluga;

    public Holding(Map<Service_Type, List<ServiceProvider>> mapaUsluga) {
        this.mapaUsluga = mapaUsluga;
    }

    public Map<Service_Type, List<ServiceProvider>> getMapaUsluga() {
        return mapaUsluga;
    }

    public void setMapaUsluga(Map<Service_Type, List<ServiceProvider>> mapaUsluga, List<ServiceProvider> serviceProviderList) {
        this.mapaUsluga = mapaUsluga;
    }
}

//        Map<String, String> holdingMap = new HashMap<>();
//
//        String serviceType = null;
//        String ServiceProvider = null;
//
//        for (int i = 0; i < serviceProviderList.size(); i++) {
//
//            holdingMap.put(serviceType, ServiceProvider);
//
//        }

//        Holding holdingList = new Holding(new HashMap<Service_Type, List<ServiceProvider>>());
//
//        int count=0;
//
//            Map<Service_Type, List<ServiceProvider>> holdingMap  = new HashMap<>();
//
//        for(Service_Type c : Service_Type.values()){
//            System.out.println(c);
//
//            holdingList.setMapaUsluga(holdingMap,serviceProviderList);
//        }
