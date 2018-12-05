package org.achartengine.easyshop.service;

import java.util.Collection;

import org.achartengine.easyshop.model.Order;

public interface IOrderService {

    Collection<Order> getOrders(long startDate, long stopDate);

    void placeOrder(Order order);

}
