package com.demo.webflux.controller;

import com.demo.webflux.dto.response.StudentFullInfo;
import com.demo.webflux.model.Address;
import com.demo.webflux.model.Student;
import com.demo.webflux.service.AddressService;
import com.demo.webflux.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final AddressService addressService;

    @GetMapping("/v1/get-by-id/{studentId}")
    public StudentFullInfo getById(@PathVariable("studentId") final String studentId){
        Student student = studentService.getById(studentId);
        Address address = addressService.findByStudentId(studentId);
        return StudentFullInfo.from(student, address);
    }

    @GetMapping("/v1b/get-by-id/{studentId}")
    public StudentFullInfo getByIdVer1b(@PathVariable("studentId") final String studentId){
        CompletableFuture<Student> studentCompletableFuture = CompletableFuture.supplyAsync(() -> studentService.getById(studentId));
        CompletableFuture<Address> addressCompletableFuture = CompletableFuture.supplyAsync(() -> addressService.findByStudentId(studentId));
        CompletableFuture.allOf(studentCompletableFuture, addressCompletableFuture).join();
        Student student = studentCompletableFuture.join();
        Address address = addressCompletableFuture.join();
        return StudentFullInfo.from(student, address);
    }

    @GetMapping("/v2/get-by-id/{studentId}")
    public Mono<StudentFullInfo> getByIdVer2(@PathVariable("studentId") final String studentId){
        Mono<Student> studentMono = studentService.getByIdVer2(studentId);
        Mono<Address> addressMono = addressService.findByStudentIdVer2(studentId);
        return studentMono.zipWith(addressMono)
                .map(value -> StudentFullInfo.from(value.getT1(), value.getT2()));
    }
}
