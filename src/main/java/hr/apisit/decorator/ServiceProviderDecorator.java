package hr.apisit.decorator;

import hr.apisit.domain.Provider;

public abstract class ServiceProviderDecorator implements Provider {

    private final Provider wrapped;

    protected ServiceProviderDecorator(Provider wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void testService() {
        System.out.println("Testing @ServiceProvider Decorator");
    }
}
