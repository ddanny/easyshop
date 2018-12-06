package org.achartengine.easyshop.service;

import java.util.List;

import org.achartengine.easyshop.model.Order;
import org.achartengine.easyshop.model.Product;

public interface IStorageService {

    List<Product> loadProducts() throws Exception;

    List<Order> loadOrders() throws Exception;

    void saveProducts() throws Exception;

    void saveOrders() throws Exception;

}
