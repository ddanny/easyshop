package org.achartengine.easyshop;

import org.achartengine.easyshop.model.Product;
import org.achartengine.easyshop.service.IProductService;
import org.achartengine.easyshop.service.ProductService;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ProductTest extends TestCase {

    public ProductTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(ProductTest.class);
    }

    public void testProducts() {
        IProductService productService = new ProductService();

        productService.addProduct(new Product(1, "HP laptop", 899.99));
        productService.addProduct(new Product(2, "HP printer", 100.99));
        assertEquals(2, productService.getProducts().size());

        productService.updateProduct(1, new Product(1, "HP notebook", 849.99));
        assertEquals(2, productService.getProducts().size());

        assertNotNull(productService.findProduct(1));
        assertNull(productService.findProduct(10));
        assertEquals(3, productService.getNextId());
    }

}
