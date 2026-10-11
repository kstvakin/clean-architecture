package com.shop.orders.infrastructure.payment;

import com.shop.orders.application.port.PaymentGateway;
import java.math.BigDecimal;

/** Payment adapter. Replace the body with real Stripe SDK calls. */
public class StripePaymentGateway implements PaymentGateway {
    @Override
    public boolean charge(String customerId, BigDecimal amount) {
        // Call the Stripe SDK here; map its exceptions to true/false.
        return true;
    }
}
