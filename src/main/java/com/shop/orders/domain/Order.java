package com.shop.orders.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Order {
    private final UUID id;
    private final String customerId;
    private final List<OrderLine> lines;
    private OrderStatus status;

    private Order(UUID id, String customerId, List<OrderLine> lines) {
        this.id = id;
        this.customerId = customerId;
        this.lines = List.copyOf(lines);
        this.status = OrderStatus.PENDING;
    }

    public static Order create(String customerId, List<OrderLine> lines) {
        if (lines == null || lines.isEmpty())
            throw new IllegalArgumentException("Order must contain at least one item");
        return new Order(UUID.randomUUID(), customerId, lines);
    }

    public BigDecimal total() {
        return lines.stream().map(OrderLine::subtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void markPaid()          { this.status = OrderStatus.PAID; }
    public void markPaymentFailed() { this.status = OrderStatus.PAYMENT_FAILED; }

    public UUID id()            { return id; }
    public String customerId()  { return customerId; }
    public OrderStatus status() { return status; }
}
