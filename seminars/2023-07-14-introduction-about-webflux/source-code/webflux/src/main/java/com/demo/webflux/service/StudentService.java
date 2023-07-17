package com.demo.webflux.service;

import com.demo.webflux.model.Student;
import reactor.core.publisher.Mono;

public interface StudentService {
    Student getById(String studentId);

    Mono<Student> getByIdVer2(String studentId);
}
