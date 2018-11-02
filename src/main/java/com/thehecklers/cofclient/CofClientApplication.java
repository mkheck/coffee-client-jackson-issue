package com.thehecklers.cofclient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.time.Instant;

@SpringBootApplication
public class CofClientApplication {
    @Bean
    WebClient client() {
        return WebClient.create("http://localhost:8080/coffees");
    }

    // Simplified to just pull the coffees. Still no joy.
    @Bean
    CommandLineRunner demo(WebClient client) {
        return args -> client.get()
                .retrieve()
                .bodyToFlux(Coffee.class)
                .subscribe(System.out::println);
    }

    public static void main(String[] args) {
        SpringApplication.run(CofClientApplication.class, args);
    }
}

class CoffeeOrder {
    private final String coffeeId;
    private final Instant now;

    public CoffeeOrder(String coffeeId, Instant now) {
        this.coffeeId = coffeeId;
        this.now = now;
    }

    public String getCoffeeId() {
        return coffeeId;
    }

    public Instant getNow() {
        return now;
    }

    @Override
    public String toString() {
        return "CoffeeOrder{" +
                "coffeeId='" + coffeeId + '\'' +
                ", now=" + now +
                '}';
    }
}

class Coffee {
    private final String id;
    private final String name;

    public Coffee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}