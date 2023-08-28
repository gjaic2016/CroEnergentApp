package hr.apisit.decorator;

import hr.apisit.domain.Provider;

public class InternalServiceProviderDecorator extends ServiceProviderDecorator{

    public InternalServiceProviderDecorator(Provider wrapped) {
        super(wrapped);
    }

    @Override
    public void testService() {
        super.testService();
        System.out.println("Testing @InternalServiceProvider Decorator");
    }
}
