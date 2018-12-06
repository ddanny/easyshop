package org.achartengine.easyshop.service;

import java.util.Collection;

import org.achartengine.easyshop.model.Product;

public interface IProductService {

    Collection<Product> getProducts();

    void addProduct(Product product);

    void updateProduct(long id, Product product);

    Product findProduct(long id);
    
    long getNextId();

}
