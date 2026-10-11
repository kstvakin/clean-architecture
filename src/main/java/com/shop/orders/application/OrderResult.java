package com.shop.orders.application;

import com.shop.orders.domain.OrderStatus;
import java.math.BigDecimal;
import java.util.UUID;

public record OrderResult(UUID orderId, OrderStatus status, BigDecimal total) {}
