package hr.apisit.domain;

import hr.apisit.service.NotificationService;

import static hr.apisit.domain.Event.*;

//TODO OBSERVER PATTERN EXAMPLE
public class ServiceProviderStore {

    public NotificationService notificationService;

    public ServiceProviderStore() {
        this.notificationService = new NotificationService();
    }

    public void contractExpirationAlert() {
        notificationService.notifyCustomers(CONTRACT_EXPIRATION_DATE_ALERT);
    }

    public NotificationService getService() {
        return notificationService;
    }

}
