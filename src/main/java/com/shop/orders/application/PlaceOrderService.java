package com.shop.orders.application;

import com.shop.orders.application.port.OrderRepository;
import com.shop.orders.application.port.PaymentGateway;
import com.shop.orders.domain.Order;
import com.shop.orders.domain.OrderLine;

/** Use case implementation: orchestrates the flow with no framework knowledge. */
public class PlaceOrderService implements PlaceOrderUseCase {
    private final OrderRepository orders;
    private final PaymentGateway payments;

    public PlaceOrderService(OrderRepository orders, PaymentGateway payments) {
        this.orders = orders;
        this.payments = payments;
    }

    @Override
    public OrderResult execute(PlaceOrderCommand cmd) {
        var lines = cmd.items().stream()
                .map(i -> new OrderLine(i.sku(), i.quantity(), i.unitPrice()))
                .toList();

        Order order = Order.create(cmd.customerId(), lines);

        if (payments.charge(order.customerId(), order.total())) order.markPaid();
        else order.markPaymentFailed();

        orders.save(order);
        return new OrderResult(order.id(), order.status(), order.total());
    }
}
