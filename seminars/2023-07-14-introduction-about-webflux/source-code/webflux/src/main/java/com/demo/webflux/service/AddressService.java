package com.demo.webflux.service;

import com.demo.webflux.model.Address;
import reactor.core.publisher.Mono;

public interface AddressService {
    Address findByStudentId(String studentId);

    Mono<Address> findByStudentIdVer2(String studentId);
}
