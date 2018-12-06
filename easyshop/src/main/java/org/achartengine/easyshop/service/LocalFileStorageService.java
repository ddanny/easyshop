package org.achartengine.easyshop.service;

import java.io.File;
import java.nio.file.Files;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import org.achartengine.easyshop.model.Order;
import org.achartengine.easyshop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Component
public class LocalFileStorageService implements IStorageService {
    private static final Logger LOG = Logger.getLogger(LocalFileStorageService.class.getName());

    private static final File PRODUCT_FILE = new File("products.json");

    private static final File ORDER_FILE = new File("orders.json");

    @Autowired
    private IProductService productService;

    @Autowired
    private IOrderService orderService;

    @Override
    public List<Product> loadProducts() throws Exception {
        LOG.info("Loading products");
        return new ObjectMapper().readValue(Files.readAllBytes(PRODUCT_FILE.toPath()), new TypeReference<List<Product>>() {
        });
    }

    @Override
    public List<Order> loadOrders() throws Exception {
        LOG.info("Loading orders");
        return new ObjectMapper().readValue(Files.readAllBytes(ORDER_FILE.toPath()), new TypeReference<List<Order>>() {
        });
    }

    @Override
    public void saveProducts() throws Exception {
        Collection<Product> products = productService.getProducts();
        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = writer.writeValueAsString(products);
        Files.write(PRODUCT_FILE.toPath(), json.getBytes());
        LOG.info("Products saved");
    }

    @Override
    public void saveOrders() throws Exception {
        List<Order> orders = orderService.getAllOrders();
        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = writer.writeValueAsString(orders);
        Files.write(ORDER_FILE.toPath(), json.getBytes());
        LOG.info("Orders saved");
    }

}
