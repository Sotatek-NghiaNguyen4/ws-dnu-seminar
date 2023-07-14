package com.webfluxclient.webfluxclient.controller;

import com.webfluxclient.webfluxclient.dto.StudentFullInfo;
import com.webfluxclient.webfluxclient.model.Student;
import com.webfluxclient.webfluxclient.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/v1/get-all-address")
    public List<String> getAllAddressVer1(){
        return clientService.getAllAddressVer1();
    }

    @GetMapping("/v2/get-all-address")
    public Flux<String> testGetAllAddressVer2(){
        return clientService.getAllAddressVer2();
    }

    @GetMapping("/v3/call-to-a-fake-api")
    public Flux<String> testGetAllAddressVer3(){
        return clientService.getAllAddressVer3();
    }

    @GetMapping("/v3/get-student-full-info/{studentId}")
    public Mono<StudentFullInfo> getStudentFullInfo(@PathVariable("studentId") final String studentId){
        return clientService.getStudentFullInfo(studentId);
    }
}
