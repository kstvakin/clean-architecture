# Clean Architecture Orders API

Companion code for "Clean Architecture Explained with a Real Java API".

## Layout

```
com.shop.orders
├── domain            Entities and business rules (pure Java)
├── application       Use cases + ports (pure Java)
│   └── port          OrderRepository, PaymentGateway
└── infrastructure    Adapters (Spring, persistence, Stripe)
    ├── web
    ├── persistence
    ├── payment
    └── config        Composition root
```

Dependencies point inward: infrastructure -> application -> domain.

## Run

Requires Java 17+ and Maven.

```
mvn test
mvn spring-boot:run
```

```
curl -X POST localhost:8080/orders \
  -H "Content-Type: application/json" \
  -d '{"customerId":"cust-1","items":[{"sku":"SKU-1","quantity":2,"unitPrice":10.00}]}'
```

An empty `items` array returns 400.
