package com.webfluxclient.webfluxclient.service;

import reactor.core.publisher.Flux;

import java.util.List;

public interface ClientService {
    List<String> getAllAddressVer1();

    Flux<String> getAllAddressVer2();

    Flux<String> getAllAddressVer3();
}
