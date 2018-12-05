package org.achartengine.easyshop.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.achartengine.easyshop.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderService implements IOrderService {

    private List<Order> orders = Collections.synchronizedList(new ArrayList<Order>());

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

    public synchronized void placeOrder(Order order) {
        orders.add(order);
    }

}
