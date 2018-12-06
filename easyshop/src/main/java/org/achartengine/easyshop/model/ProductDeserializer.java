package org.achartengine.easyshop.model;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

@SuppressWarnings("serial")
public class ProductDeserializer extends StdDeserializer<Product> {
    private AtomicLong counter = new AtomicLong();

    public ProductDeserializer() {
        this(null);
    }

    public ProductDeserializer(Class<?> c) {
        super(c);
    }

    @Override
    public Product deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = parser.getCodec().readTree(parser);
        double price = node.get("price").asDouble();
        String name = node.get("name").asText();
        return new Product(counter.incrementAndGet(), name, price);
    }
}