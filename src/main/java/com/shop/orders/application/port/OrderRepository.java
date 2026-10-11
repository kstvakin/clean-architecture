package com.shop.orders.application.port;

import com.shop.orders.domain.Order;
import java.util.Optional;
import java.util.UUID;

/** Port owned by the core; implemented by the outer layers. */
public interface OrderRepository {
    void save(Order order);
    Optional<Order> findById(UUID id);
}
