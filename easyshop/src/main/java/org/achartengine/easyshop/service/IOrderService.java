package org.achartengine.easyshop.service;

import java.util.Collection;
import java.util.List;

import org.achartengine.easyshop.model.Order;

public interface IOrderService {

    Collection<Order> getOrders(long startDate, long stopDate);

    List<Order> getAllOrders();

    void placeOrder(Order order);

    long getNextId();

}
