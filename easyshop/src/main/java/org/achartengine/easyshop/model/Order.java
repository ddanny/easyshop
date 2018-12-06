package org.achartengine.easyshop.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = OrderDeserializer.class)
public class Order {

    private long id;

    private final List<Product> products = new ArrayList<>();

    private final String buyerEmail;

    private final long time;
    
    public Order(long id, String buyerEmail, long time, List<Product> products) {
        this.id = id;
        this.buyerEmail = buyerEmail;
        this.time = time;
        this.products.addAll(products);
    }

    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public long getTime() {
        return time;
    }

    public List<Product> getProducts() {
        return products;
    }
    
    public double getTotalAmount() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

}