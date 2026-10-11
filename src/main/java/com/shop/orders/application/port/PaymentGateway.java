package com.shop.orders.application.port;

import java.math.BigDecimal;

/** Port owned by the core; implemented by Stripe, PayPal, fakes, etc. */
@FunctionalInterface
public interface PaymentGateway {
    boolean charge(String customerId, BigDecimal amount);
}
