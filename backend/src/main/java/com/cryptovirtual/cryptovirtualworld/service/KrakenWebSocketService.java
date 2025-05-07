package com.cryptovirtual.cryptovirtualworld.service;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import java.net.URI;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class KrakenWebSocketService {

    private final Map<String, String> prices = new ConcurrentHashMap<>();
    private final List<String> PAIRS = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();


    @PostConstruct
    public void connect() {

        try {
            StandardWebSocketClient client = new StandardWebSocketClient();

            client.execute(new AbstractWebSocketHandler() {
                @Override
                public void afterConnectionEstablished(WebSocketSession session) throws Exception {
                    System.out.println("WebSocket connection is established");

                    String subscribeMessage = """
                        {
                          "event": "subscribe",
                          "pair": %s,
                          "subscription": { "name": "ticker" }
                        }
                    """.formatted(PAIRS.toString());
                    session.sendMessage(new TextMessage(subscribeMessage));
                }

                @Override
                protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
                    String payload = message.getPayload();
                    System.out.println("Received: " + payload);
                    try {
                        JsonNode json = objectMapper.readTree(payload);
                        if (json.isArray() && json.size() >= 4 && json.get(1).has("c")) {
                            String pair = json.get(3).asText();
                            String price = json.get(1).get("c").get(0).asText();
                        
                            prices.put(pair, price);
                            System.out.println("Updated price: " + pair + " -> " + price);
                        }

                    } catch (Exception e) {
                        System.err.println("Failed to parse WebSocket error: " + payload);
                        e.printStackTrace();
                    }
                    
                }

            }, null, URI.create("wss://ws.kraken.com/"));
        } catch (Exception e) {
            System.err.println("Failed to connect: " + e.getMessage());
        }
    }
    public Map<String, String> getPrices() {
        return prices;
    }
}
