package com.shop.orders.application;

/** Input boundary: how the outside world asks the core to place an order. */
public interface PlaceOrderUseCase {
    OrderResult execute(PlaceOrderCommand command);
}
