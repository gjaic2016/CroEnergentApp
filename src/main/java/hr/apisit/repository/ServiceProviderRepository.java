package hr.apisit.repository;

import hr.apisit.domain.ServiceProvider;
import hr.apisit.patterns.ServiceProviderBuilder;
import hr.apisit.domain.ServiceType;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ServiceProviderRepository {

    public static final Integer NUMBER_OF_SERVICEPROVIDER_DATA_LINES = 5;

    public static final String SERVICEPROVIDERS_FILE_NAME = "dat/testServiceProviders.txt";

    public List<ServiceProvider> readAllServiceProviders() throws IOException {

        List<ServiceProvider> serviceProvidersList = new ArrayList<>();

        Path stageFilePath = Path.of("dat/serviceProviders.txt");

        List<String> lines = Files.readAllLines(stageFilePath);

        for (int i = 0; i < lines.size() / NUMBER_OF_SERVICEPROVIDER_DATA_LINES; i++) {

            Integer serviceProviderId = Integer.parseInt(lines.get(i * NUMBER_OF_SERVICEPROVIDER_DATA_LINES));
            String serviceProviderNaziv = lines.get(i * NUMBER_OF_SERVICEPROVIDER_DATA_LINES + 1);
            String serviceProviderAdresa = lines.get(i * NUMBER_OF_SERVICEPROVIDER_DATA_LINES + 2);
            ServiceType serviceProviderVrstaUslugeENUM = ServiceType.valueOf(lines.get(i * NUMBER_OF_SERVICEPROVIDER_DATA_LINES + 3));
            String cijenaUslugeString = lines.get(i * NUMBER_OF_SERVICEPROVIDER_DATA_LINES + 4);
            BigDecimal serviceProviderCijenaUsluge = new BigDecimal(cijenaUslugeString);

//            SINGLETON TEST
//            ServiceProvider newServiceProvider = ServiceProvider.getInstance();
//            newServiceProvider.setId(serviceProviderId);
//            newServiceProvider.setNaziv(serviceProviderNaziv);
//            newServiceProvider.setAdresa(serviceProviderAdresa);
//            newServiceProvider.setVrstaUsluge(serviceProviderVrstaUslugeENUM);
//            newServiceProvider.setCijenaUsluge(serviceProviderCijenaUsluge);

//            ServiceProvider newServiceProvider = new ServiceProvider(serviceProviderId, serviceProviderNaziv,
//                    serviceProviderAdresa, serviceProviderVrstaUslugeENUM, serviceProviderCijenaUsluge);
            ServiceProviderBuilder builder = new ServiceProviderBuilder();

            builder.id(serviceProviderId)
                    .naziv(serviceProviderNaziv)
                    .adresa(serviceProviderAdresa)
                    .vrstaUsluge(serviceProviderVrstaUslugeENUM)
                    .cijenaUsluge(serviceProviderCijenaUsluge);
            ServiceProvider newServiceProvider = builder.build();
            serviceProvidersList.add(newServiceProvider);
        }
        return serviceProvidersList;
    }

    public ServiceProvider readById(Integer id) throws IOException {
        return readAllServiceProviders().stream()
                .filter(sp -> sp.getId().equals(id))
                .findFirst()
                .get();
    }

    public void writeServiceProviderToFile(List<ServiceProvider> serviceProviderList) throws IOException {

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(SERVICEPROVIDERS_FILE_NAME));
        //id
        //naziv
        //adresa
        //vrsta usluge
        //cijena usluge
        System.out.println("Writing service provider......\n");
        for (ServiceProvider serviceProvider : serviceProviderList) {
            bufferedWriter.write(serviceProvider.getId().toString());
            bufferedWriter.newLine();
            bufferedWriter.write(serviceProvider.getNaziv().toString());
            bufferedWriter.newLine();
            bufferedWriter.write(serviceProvider.getAdresa().toString());
            bufferedWriter.newLine();
            bufferedWriter.write(serviceProvider.getVrstaUsluge().name());
            bufferedWriter.newLine();
            bufferedWriter.write(serviceProvider.getCijenaUsluge().toString());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }
}
