package com.github.nicon777.kafkaexampleconsumer.model;

import java.util.Date;
import java.util.List;

public class Order {
        private int id;
        private String customerName;
        private String shippingAddress;
        private List<Product> products;
        private float total;
        private Date date;

//    public static class OrderBuilder{
//        private int id;
//        private String customerName;
//        private String shippingAddress;
//        private List<Product> products;
//        private float total;
//        private Date date;
//
//        public OrderBuilder id(int id){
//            this.id = id;
//            return this;
//        }
//
//        public OrderBuilder customerName(String customerName){
//            this.customerName = customerName;
//            return this;
//        }
//
//        public OrderBuilder shippingAddress(String shippingAddress){
//            this.shippingAddress = shippingAddress;
//            return this;
//        }
//
//        public OrderBuilder products(List<Product> products){
//            this.products = products;
//            return this;
//        }
//
//
//        public OrderBuilder date(Date date) {
//            this.date = date;
//            return this;
//        }
//
//        public Order build(){
//            this.total = products.stream().map(product -> product.getPrice()).reduce(0f, Float::sum);
//            return new Order(this);
//        }
//    }

        public Order() {
        }

//    public Order(OrderBuilder orderBuilder){
//        this.id = orderBuilder.id;
//        this.customerName = orderBuilder.customerName;
//        this.shippingAddress = orderBuilder.shippingAddress;
//        this.products = orderBuilder.products;
//        this.total = orderBuilder.total;
//        this.date = orderBuilder.date;
//    }

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
}
