package hr.apisit.decorator;

import hr.apisit.domain.Provider;

public class ExternalServiceProviderDecorator extends ServiceProviderDecorator{
    public ExternalServiceProviderDecorator(Provider wrapped) {
        super(wrapped);
    }

    @Override
    public void testService() {
        super.testService();
        System.out.println("Testing @ExternalServiceProvider Decorator");
    }
}
