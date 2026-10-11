package com.shop.orders.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.shop.orders.domain.OrderStatus;
import com.shop.orders.infrastructure.persistence.InMemoryOrderRepository;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;

/** Pure unit tests: no Spring, no database, no network. */
class PlaceOrderServiceTest {

    @Test
    void marksOrderPaidWhenPaymentSucceeds() {
        var service = new PlaceOrderService(new InMemoryOrderRepository(), (c, a) -> true);

        var result = service.execute(new PlaceOrderCommand("cust-1",
                List.of(new PlaceOrderCommand.Item("SKU-1", 2, new BigDecimal("10.00")))));

        assertEquals(OrderStatus.PAID, result.status());
        assertEquals(new BigDecimal("20.00"), result.total());
    }

    @Test
    void marksOrderFailedWhenPaymentDeclined() {
        var service = new PlaceOrderService(new InMemoryOrderRepository(), (c, a) -> false);

        var result = service.execute(new PlaceOrderCommand("cust-1",
                List.of(new PlaceOrderCommand.Item("SKU-1", 1, new BigDecimal("5.00")))));

        assertEquals(OrderStatus.PAYMENT_FAILED, result.status());
    }

    @Test
    void rejectsEmptyCart() {
        var service = new PlaceOrderService(new InMemoryOrderRepository(), (c, a) -> true);
        assertThrows(IllegalArgumentException.class,
                () -> service.execute(new PlaceOrderCommand("cust-1", List.of())));
    }
}
