package hr.apisit.repository;

import hr.apisit.domain.Owner;
import hr.apisit.domain.ServiceProvider;
import hr.apisit.domain.Service_Type;
import hr.apisit.utility.LocalDateUtility;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServiceProviderRepository {

    public static final Integer NUMBER_OF_SERVICEPROVIDER_DATA_LINES = 5;

    public List<ServiceProvider> readAllServiceProviders() throws IOException {

        List<ServiceProvider> serviceProvidersList = new ArrayList<>();

        Path stageFilePath = Path.of("dat/serviceProviders.txt");

        List<String> lines = Files.readAllLines(stageFilePath);

        for (int i = 0; i < lines.size() / NUMBER_OF_SERVICEPROVIDER_DATA_LINES; i++) {

            Integer serviceProviderId = Integer.parseInt(lines.get(i * NUMBER_OF_SERVICEPROVIDER_DATA_LINES));
            String serviceProviderNaziv = lines.get(i * NUMBER_OF_SERVICEPROVIDER_DATA_LINES + 1);
            String serviceProviderAdresa = lines.get(i * NUMBER_OF_SERVICEPROVIDER_DATA_LINES + 2);
            Service_Type serviceProviderVrstaUslugeENUM = Service_Type.valueOf(lines.get(i * NUMBER_OF_SERVICEPROVIDER_DATA_LINES + 3));
            String cijenaUslugeString = lines.get(i * NUMBER_OF_SERVICEPROVIDER_DATA_LINES + 4);
            BigDecimal serviceProviderCijenaUsluge = new BigDecimal(cijenaUslugeString);

            ServiceProvider newServiceProvider = new ServiceProvider(serviceProviderId, serviceProviderNaziv,
                    serviceProviderAdresa, serviceProviderVrstaUslugeENUM, serviceProviderCijenaUsluge);
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
}
