package org.achartengine.easyshop;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.easyshop.model.Order;
import org.achartengine.easyshop.model.Product;
import org.achartengine.easyshop.service.IOrderService;
import org.achartengine.easyshop.service.IProductService;
import org.achartengine.easyshop.service.OrderService;
import org.achartengine.easyshop.service.ProductService;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class OrderTest extends TestCase {

    public OrderTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(OrderTest.class);
    }

    public void testOrders() {
        IProductService productService = new ProductService();
        IOrderService orderService = new OrderService();

        productService.addProduct(new Product(1, "HP laptop", 899.99));
        productService.addProduct(new Product(2, "HP printer", 100.99));
        assertEquals(2, productService.getProducts().size());

        List<Product> orderProducts = new ArrayList<>();
        orderProducts.add(productService.findProduct(1));
        orderService.placeOrder(new Order(1, "buyerEmail", 10, orderProducts));
        assertEquals(1, orderService.getAllOrders().size());
        assertEquals(899.99, orderService.getAllOrders().get(0).getTotalAmount());

        productService.updateProduct(1, new Product(1, "HP notebook", 849.99));
        assertEquals(899.99, orderService.getAllOrders().get(0).getTotalAmount());
        assertEquals(1, orderService.getAllOrders().size());
        assertEquals(1, orderService.getOrders(0, 100).size());
        assertEquals(0, orderService.getOrders(100, 1000).size());
        
        assertEquals(2, orderService.getNextId());
    }
}
