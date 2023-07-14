package com.webfluxclient.webfluxclient.service;

import com.webfluxclient.webfluxclient.dto.StudentFullInfo;
import com.webfluxclient.webfluxclient.model.Address;
import com.webfluxclient.webfluxclient.model.Student;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

    private final WebClient webClient = WebClient.create("http://localhost:8080");
    @Override
    public List<String> getAllAddressVer1() {
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
        webClient.get()
                .uri("/address/v2/get-all-address")
                .accept(MediaType.ALL)
                .retrieve()
                .bodyToFlux(String.class)
                .log()
                .subscribe(System.out::println);
        return Flux.just("Ok");
    }

    @Override
    public Flux<String> getAllAddressVer3() {
        webClient.get()
                .uri("/address/v2/fake-url")
                .accept(MediaType.ALL)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    System.out.println("Loi 4xx");
                    return Mono.error(new RuntimeException("Client error"));
                })
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> {
                    System.out.println("Loi 5xx");
                    return Mono.error(new RuntimeException("Server error"));
                })
                .bodyToFlux(String.class)
                .log()
                .subscribe(System.out::println);
        return Flux.just("Ok");
    }

    @Override
    public Mono<StudentFullInfo> getStudentFullInfo(String studentId) {
        Mono<Student> studentMono =  webClient.get()
                .uri("/student/v3/get-by-id/" + studentId)
                .accept(MediaType.ALL)
                .retrieve()
                .bodyToMono(Student.class);
        Mono<Address> addressMono = webClient.get()
                .uri("/address/v3/get-by-student-id/" + studentId)
                .accept(MediaType.ALL)
                .retrieve()
                .bodyToMono(Address.class);
        return studentMono.zipWith(addressMono)
                .map(value -> StudentFullInfo.from(value.getT1(), value.getT2()));
    }
}
