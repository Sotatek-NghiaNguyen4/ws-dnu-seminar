package com.demo.webflux.service;


import com.demo.webflux.model.Address;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService{
    @Override
    public Address findByStudentId(String studentId) {
        // Do something that takes time
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            System.out.println("Can not sleep");
        }
        return Address.builder()
                .studentId(studentId)
                .address(List.of("Hai Duong", "Ha Noi", "Kon Tum"))
                .build();
    }

    @Override
    public Mono<Address> findByStudentIdVer2(String studentId) {
        return Mono.just(Address.builder()
                .studentId(studentId)
                .address(List.of("Hai Duong", "Ha Noi", "Kon Tum"))
                .build())
                .delayElement(Duration.ofSeconds(2));
    }
}
