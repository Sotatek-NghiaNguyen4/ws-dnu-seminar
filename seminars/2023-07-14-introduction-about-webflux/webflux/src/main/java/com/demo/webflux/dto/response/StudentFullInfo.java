package com.demo.webflux.dto.response;

import com.demo.webflux.model.Address;
import com.demo.webflux.model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StudentFullInfo {
    private String id;
    private String name;
    private int age;
    private List<String> address;

    public static StudentFullInfo from(Student student, Address address){
        return StudentFullInfo.builder()
                .id(student.getId())
                .name(student.getName())
                .age(student.getAge())
                .address(address.getAddress())
                .build();
    }
}
