package hr.apisit.domain;

public enum ContractType {

    FIXED("Fixed"),
    INDEFINITE("Indefinite");

    private String contractType;

    ContractType(String contractType) {
        this.contractType=contractType;
    }

    public String getContractType() {
        return contractType;
    }
}
