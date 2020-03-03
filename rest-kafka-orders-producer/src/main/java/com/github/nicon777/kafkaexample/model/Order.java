package com.github.nicon777.kafkaexample.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Order {
    private int id;
    private String customerName;
    private String shippingAddress;
    private List<Product> products;
    private float total;
    private Date date;

    public Order() {
    }

    public Order(int id, String customerName, String shippingAddress, List<Product> products, float total, Date date) {
        this.id = id;
        this.customerName = customerName;
        this.shippingAddress = shippingAddress;
        this.products = products;
        this.total = total;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public float getTotal() {
        return products.stream().map(Product::getPrice).reduce(0f, Float::sum);
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", products=" + products +
                ", total=" + total +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                Float.compare(order.total, total) == 0 &&
                Objects.equals(customerName, order.customerName) &&
                Objects.equals(shippingAddress, order.shippingAddress) &&
                Objects.equals(products, order.products) &&
                Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerName, shippingAddress, products, total, date);
    }
}
