package com.cryptovirtual.cryptovirtualworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cryptovirtual.cryptovirtualworld.service.KrakenWebSocketService;
import java.util.Map;

@RestController
@RequestMapping("/api/market")
@CrossOrigin(origins = "http://localhost:3000")
public class MarketController {

    @Autowired
    private KrakenWebSocketService krakenService;

    @GetMapping("/prices")
    public Map<String, String> getPrices() {
        return krakenService.getPrices();
    }
}
