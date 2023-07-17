package com.demo.webflux.controller;

import com.demo.webflux.model.Address;
import com.demo.webflux.model.Student;
import com.demo.webflux.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

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

    @GetMapping("/v3/get-by-student-id/{studentId}")
    public Mono<Address> getAddressMonoByStudentId(@PathVariable("studentId") final String studentId){
        Address address = addressService.findByStudentId(studentId);
        return Mono.just(address);
    }
}
