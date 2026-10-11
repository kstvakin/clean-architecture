package com.shop.orders.domain;

import java.math.BigDecimal;

public record OrderLine(String sku, int quantity, BigDecimal unitPrice) {
    public OrderLine {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
        if (unitPrice.signum() < 0) throw new IllegalArgumentException("Price cannot be negative");
    }

    public BigDecimal subtotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
