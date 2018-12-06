package org.achartengine.easyshop.controller;

import java.util.Collection;
import java.util.logging.Logger;

import org.achartengine.easyshop.model.Order;
import org.achartengine.easyshop.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private static final Logger LOG = Logger.getLogger(OrderController.class.getName());

    @Autowired
    private IOrderService orderService;

    @RequestMapping(method = RequestMethod.PUT, path = "/order")
    public ResponseEntity<?> placeOrder(@RequestBody Order order) {
        orderService.placeOrder(order);
        LOG.info("New order placed");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/orders/{start}/{stop}")
    public Collection<Order> getOrders(@PathVariable("start") long startDate, @PathVariable("stop") long stopDate) {
        return orderService.getOrders(startDate, stopDate);
    }

}