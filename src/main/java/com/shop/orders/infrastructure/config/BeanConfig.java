package com.shop.orders.infrastructure.config;

import com.shop.orders.application.PlaceOrderService;
import com.shop.orders.application.PlaceOrderUseCase;
import com.shop.orders.application.port.OrderRepository;
import com.shop.orders.application.port.PaymentGateway;
import com.shop.orders.infrastructure.payment.StripePaymentGateway;
import com.shop.orders.infrastructure.persistence.InMemoryOrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Composition root: the only place that knows who plugs into what. */
@Configuration
public class BeanConfig {
    @Bean OrderRepository orderRepository() { return new InMemoryOrderRepository(); }
    @Bean PaymentGateway paymentGateway()   { return new StripePaymentGateway(); }
    @Bean PlaceOrderUseCase placeOrder(OrderRepository r, PaymentGateway p) {
        return new PlaceOrderService(r, p);
    }
}
