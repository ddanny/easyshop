package org.achartengine.easyshop.service;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.achartengine.easyshop.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductService implements IProductService {

    private Map<Long, Product> products = Collections.synchronizedMap(new LinkedHashMap<Long, Product>());

    public synchronized Collection<Product> getProducts() {
        return products.values();
    }

    public synchronized void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public void updateProduct(long id, Product updatedProduct) {
        Product product = products.get(id);
        if (product != null) {
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
        } else {
            throw new RuntimeException("Unknown product");
        }
    }
    
    public Product findProduct(long id) {
        return products.get(id);
    }

}
