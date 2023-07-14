package com.webfluxclient.webfluxclient.service;

import com.webfluxclient.webfluxclient.dto.StudentFullInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ClientService {
    List<String> getAllAddressVer1();

    Flux<String> getAllAddressVer2();

    Flux<String> getAllAddressVer3();

    Mono<StudentFullInfo> getStudentFullInfo(String studentId);
}
