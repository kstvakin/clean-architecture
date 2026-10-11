package com.shop.orders.application;

import java.math.BigDecimal;
import java.util.List;

public record PlaceOrderCommand(String customerId, List<Item> items) {
    public record Item(String sku, int quantity, BigDecimal unitPrice) {}
}
