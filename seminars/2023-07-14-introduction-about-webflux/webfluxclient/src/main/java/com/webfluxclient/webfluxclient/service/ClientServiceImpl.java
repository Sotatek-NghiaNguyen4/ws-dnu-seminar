package com.webfluxclient.webfluxclient.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{
    @Override
    public List<String> getAllAddressVer1() {
        WebClient webClient = WebClient.create("http://localhost:8080");
        webClient.get()
                .uri("/address/v1/get-all-address")
                .header("Authorization", "Bearer token")
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(resource -> {
                    System.out.println(resource);
                });
        return new ArrayList<>();
    }

    @Override
    public Flux<String> getAllAddressVer2() {
        WebClient webClient = WebClient.create("http://localhost:8080");
        webClient.get()
                .uri("/address/v2/get-all-address")
                .accept(MediaType.ALL)
                .retrieve()
                .bodyToFlux(String.class)
                .log()
                .subscribe(System.out::println);
        return Flux.just("Ok");
    }
}
