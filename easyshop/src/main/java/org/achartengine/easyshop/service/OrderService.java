package org.achartengine.easyshop.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.achartengine.easyshop.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderService implements IOrderService {
    private static final Logger LOG = Logger.getLogger(OrderService.class.getName());

    @Autowired
    private IStorageService storageService;

    private List<Order> orders = Collections.synchronizedList(new ArrayList<Order>());

    @PostConstruct
    public void init() {
        try {
            orders.addAll(storageService.loadOrders());
        } catch (Exception e) {
            LOG.warning("Could not load orders " + e.getMessage());
        }
    }

    public synchronized Collection<Order> getOrders(long startDate, long stopDate) {
        List<Order> filteredOrders = new ArrayList<>();
        for (Order order : orders) {
            long time = order.getTime();
            if (startDate <= time && time <= stopDate) {
                filteredOrders.add(order);
            }
        }
        return filteredOrders;
    }

    public synchronized List<Order> getAllOrders() {
        return orders;
    }

    public synchronized void placeOrder(Order order) {
        if (order.getId() == IStorageService.NO_ID) {
            order.setId(getNextId());
        }
        orders.add(order);
    }
    
    public synchronized long getNextId() {
        return orders.size() + 1;
    }

}
