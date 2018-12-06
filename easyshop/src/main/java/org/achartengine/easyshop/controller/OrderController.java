package org.achartengine.easyshop.controller;

import java.util.Collection;
import java.util.logging.Logger;

import org.achartengine.easyshop.model.Order;
import org.achartengine.easyshop.service.IOrderService;
import org.achartengine.easyshop.service.IStorageService;
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

    @Autowired
    private IStorageService storageService;
    
    @RequestMapping(method = RequestMethod.PUT, path = "/order")
    public ResponseEntity<?> placeOrder(@RequestBody Order order) {
        orderService.placeOrder(order);
        try {
            storageService.saveOrders();
        } catch (Exception e) {
            LOG.warning("Could not save orders " + e.getMessage());
        }
        LOG.info("New order placed");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/orders/{start}/{stop}")
    public Collection<Order> getOrders(@PathVariable("start") long startDate, @PathVariable("stop") long stopDate) {
        return orderService.getOrders(startDate, stopDate);
    }

}