package hr.apisit.service;

import hr.apisit.domain.Event;
import hr.apisit.domain.Listener;

import java.util.*;

public class NotificationService {

    private final Map<Event, List<Listener>> customers;


    public NotificationService() {
        this.customers = new HashMap<>();
        Arrays.stream(Event.values()).forEach(event -> customers.put(event, new ArrayList<>()));
    }

    public void subscribe(Event eventType, Listener listener) {
        customers.get(eventType).add(listener);
    }

    public void notifyCustomers(Event eventType) {
        customers.get(eventType).forEach(listener -> listener.update(eventType));
    }

}

