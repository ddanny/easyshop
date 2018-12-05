package org.achartengine.easyshop.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.achartengine.easyshop.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.DoubleNode;

public class OrderDeserializer extends StdDeserializer<Order> {
    private AtomicLong counter = new AtomicLong();
    
    @Autowired
    private IProductService productService;

    public OrderDeserializer() {
        this(null);
    }

    public OrderDeserializer(Class<?> c) {
        super(c);
    }

    @Override
    public Order deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = parser.getCodec().readTree(parser);
        // TODO: post products instead of just IDs such as price changes not to update existing order changes
        ArrayNode productIds = (ArrayNode) node.get("productIds");
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < productIds.size(); i++) {
            long productId = productIds.get(i).asLong();
            Product product = productService.findProduct(productId);
            if (product != null) {
                products.add(product);
            }
        }
        String buyerEmail = node.get("buyerEmail").asText();
        return new Order(counter.incrementAndGet(), buyerEmail, System.currentTimeMillis(), products);
    }
}