package hr.apisit.domain;

public class EmailListener implements Listener {

    public String email;

    public EmailListener(String email) {
        this.email = email;
    }

    @Override
    public void update(Event eventType) {
        System.out.println("Sending mail to customer "+email+" about " + eventType);
    }
}
