package hr.apisit.domain;

public enum ServiceType {

    ELECTRICITY_SUPPLY("Electricity supply"),
    WATER_SUPPLY("Water supply"),
    GAS_SUPPLY("Gas supply"),
    INTERNET_SUPPLY("Internet supply"),
    GARBAGE_COLLECTION("Garbage collection");

    private String serviceName;

    ServiceType(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }
}
