package org.achartengine.easyshop.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.achartengine.easyshop.service.IProductService;
import org.achartengine.easyshop.service.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;

@SuppressWarnings("serial")
public class OrderDeserializer extends StdDeserializer<Order> {
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
        List<Product> products = new ArrayList<>();
        ArrayNode productIds = (ArrayNode) node.get("productIds");
        if (productIds != null) {
            // extracting from an order coming from the REST call
            for (int i = 0; i < productIds.size(); i++) {
                long productId = productIds.get(i).asLong();
                Product product = productService.findProduct(productId);
                if (product != null) {
                    // create a new Product instance, such as price changes not to affect an
                    // existing order
                    products.add(new Product(product.getId(), product.getName(), product.getPrice()));
                }
            }
        } else {
            // extracting from an order read from file
            ArrayNode prods = (ArrayNode) node.get("products");
            for (int i = 0; i < prods.size(); i++) {
                JsonNode productNode = prods.get(i);
                long productId = productNode.get("id").asLong();
                String name = productNode.get("name").asText();
                double price = productNode.get("price").asDouble();
                products.add(new Product(productId, name, price));
            }
        }
        String buyerEmail = node.get("buyerEmail").asText();
        return new Order(IStorageService.NO_ID, buyerEmail, System.currentTimeMillis(), products);
    }
}