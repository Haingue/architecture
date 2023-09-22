package com.imt.monitoring.controller;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;
import jakarta.annotation.PostConstruct;
import jakarta.websocket.server.PathParam;

@RestController
public class MainController {

    @Autowired
    MeterRegistry registry;

    private Timer latencyMetrics;
    private Counter errorMetrics;
    
    @PostConstruct
    private void initialize () {
        latencyMetrics = Metrics.timer("product.random_integer.latency");
        errorMetrics = Counter.builder("product.random_integer.error")
                .register(registry);
    }
    
    @GetMapping
    public ResponseEntity<String> getHome () {
        return ResponseEntity.ok("UP");
    }

    @GetMapping("/random")
    public ResponseEntity<?> getRandomInterger (@PathParam("min") Integer min, @PathParam("max") Integer max) {
        Instant startTimestamp = Instant.now();
        if (min == null) min = 0;
        if (max == null) max = 100;
        Random random = new Random();
        try {
            Thread.sleep(random.nextLong(1500L));
        } catch (InterruptedException e) {
            errorMetrics.increment();
        }
        if (random.nextBoolean()) {
            latencyMetrics.record(Duration.between(startTimestamp, Instant.now()));
            return ResponseEntity.ok(random.nextInt(max)+min);
        }
        errorMetrics.increment();
        return ResponseEntity.internalServerError().build();
    }
    
}
