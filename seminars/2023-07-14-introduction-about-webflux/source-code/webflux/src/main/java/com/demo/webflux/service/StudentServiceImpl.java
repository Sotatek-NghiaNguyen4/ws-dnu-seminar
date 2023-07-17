package com.demo.webflux.service;

import com.demo.webflux.model.Student;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class StudentServiceImpl implements StudentService{
    @Override
    public Student getById(String studentId) {
        // Do something that takes time
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Can not sleep");
        }
        return Student.builder()
                .id(studentId)
                .name("Le Viet Tho")
                .age(31)
                .build();
    }

    @Override
    public Mono<Student> getByIdVer2(String studentId) {
        return Mono.just(Student.builder()
                .id(studentId)
                .name("Le Viet Tho")
                .age(31)
                .build())
                .delayElement(Duration.ofSeconds(2));
    }
}
