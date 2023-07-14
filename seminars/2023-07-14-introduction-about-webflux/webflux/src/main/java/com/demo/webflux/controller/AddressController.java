package com.demo.webflux.controller;

import com.demo.webflux.model.Address;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.awt.*;
import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @GetMapping("/v1/get-all-address")
    public List<String> getAllAddress() throws InterruptedException {
        Thread.sleep(5000);
        return List.of("Ha Noi", "Hai Duong", "Kon Tum", "Thai Nguyen", "Hung Yen");
    }

    @GetMapping(value = "/v2/get-all-address", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getAllAddressWithReactive() {
        return Flux.just("Ha Noi", "Hai Duong", "Kon Tum", "Thai Nguyen", "Hung Yen")
                .log()
                .delayElements(Duration.ofSeconds(2));
    }
}
