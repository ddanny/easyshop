package org.achartengine.easyshop.controller;

import java.util.Collection;
import java.util.logging.Logger;

import org.achartengine.easyshop.model.Product;
import org.achartengine.easyshop.service.IProductService;
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
public class ProductController {
    private static final Logger LOG = Logger.getLogger(ProductController.class.getName());

    @Autowired
    private IProductService productService;

    @Autowired
    private IStorageService storageService;

    @RequestMapping(method = RequestMethod.PUT, path = "/product")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        try {
            storageService.saveProducts();
        } catch (Exception e) {
            LOG.warning("Could not store products " + e.getMessage());
        }
        LOG.info("New product added " + product.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/products")
    public Collection<Product> getProductList() {
        return productService.getProducts();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
        try {
            productService.updateProduct(id, product);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
        try {
            storageService.saveProducts();
        } catch (Exception e) {
            LOG.warning("Could not store products " + e.getMessage());
        }
        LOG.info("Product updated with id " + product.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}