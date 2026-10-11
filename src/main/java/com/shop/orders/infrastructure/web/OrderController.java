package com.shop.orders.infrastructure.web;

import com.shop.orders.application.OrderResult;
import com.shop.orders.application.PlaceOrderCommand;
import com.shop.orders.application.PlaceOrderUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** Web adapter: translates HTTP into a use case call. */
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final PlaceOrderUseCase placeOrder;

    public OrderController(PlaceOrderUseCase placeOrder) { this.placeOrder = placeOrder; }

    @PostMapping
    public ResponseEntity<OrderResult> place(@RequestBody PlaceOrderCommand cmd) {
        return ResponseEntity.status(HttpStatus.CREATED).body(placeOrder.execute(cmd));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> badRequest(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
