package org.example.servlet.dto;

import org.example.model.Customer;

import java.util.Date;
import java.util.Objects;

public class IncomingOrderDTO {
    private int id;
    private Customer customer;

    private Date date;

    public IncomingOrderDTO(int id, Customer customer,  Date date) {
        this.id = id;
        this.customer = customer;
        this.date = date;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncomingOrderDTO that = (IncomingOrderDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "IncomingOrderDTO{" +
                "id=" + id +
                ", customer=" + customer +
                ", date=" + date +
                '}';
    }
}
