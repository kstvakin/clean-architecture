package com.shop.orders.infrastructure.persistence;

import com.shop.orders.application.port.OrderRepository;
import com.shop.orders.domain.Order;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/** Persistence adapter. Swap for JPA/Mongo later without touching the core. */
public class InMemoryOrderRepository implements OrderRepository {
    private final Map<UUID, Order> store = new ConcurrentHashMap<>();

    @Override
    public void save(Order order) { store.put(order.id(), order); }

    @Override
    public Optional<Order> findById(UUID id) { return Optional.ofNullable(store.get(id)); }
}
