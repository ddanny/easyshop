package org.achartengine.easyshop;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the application.
 */
@SpringBootApplication
public class EasyShopApplication {
    private static final Logger LOG = Logger.getLogger(EasyShopApplication.class.getName());
    
    public static void main(String[] args) {
        SpringApplication.run(EasyShopApplication.class, args);
        LOG.info("EasyShop successfully started");
    }
}
