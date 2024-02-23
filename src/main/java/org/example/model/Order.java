package org.example.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Order {
    private int id;
    private Customer customer;

    private List<Item> itemList;

    private Date date;


    public Order(int id, Customer customer, Date date, List<Item> itemList) {
        this.id = id;
        this.customer = customer;
        this.date = date;
        this.itemList = itemList;
    }

    public Order(int id, Customer customer, Date date) {
        this.id = id;
        this.customer = customer;
        this.date = date;
    }

    public Order(Customer customer, Date date) {
        this.customer = customer;
        this.date = date;
    }

    public Order(Customer customer) {
        this.customer = customer;
    }

    public Order() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", itemList=" + itemList +
                ", date=" + date +
                '}';
    }
}
