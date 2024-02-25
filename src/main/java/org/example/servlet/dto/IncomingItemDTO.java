package org.example.servlet.dto;

import org.example.model.Order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class IncomingItemDTO {
    private int id;
    private String name;

    private BigDecimal price;
    List<Order> orderList;

    public IncomingItemDTO(int id, String name, BigDecimal price, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.orderList = orders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncomingItemDTO that = (IncomingItemDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "IncomingItemDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", orderList=" + orderList +
                '}';
    }
}
