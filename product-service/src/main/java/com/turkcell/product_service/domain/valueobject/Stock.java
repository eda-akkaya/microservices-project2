package com.turkcell.product_service.domain.valueobject;

import java.util.Objects;

public class Stock {
    private final int quantity;

    public Stock(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isInStock() {
        return quantity > 0;
    }

    public Stock add(int amount) {
        return new Stock(this.quantity + amount);
    }

    public Stock subtract(int amount) {
        if (amount > this.quantity) {
            throw new IllegalArgumentException("Cannot subtract more than available stock");
        }
        return new Stock(this.quantity - amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return quantity == stock.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity);
    }

    @Override
    public String toString() {
        return String.valueOf(quantity);
    }
}


