package com.example.paymentsapp.dto.studentDto;

import com.example.paymentsapp.model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {
    private String name;
    private String surname;
    private Long number;
    private String email;
    public static StudentResponse convert(Student from){
        return StudentResponse.builder()
                .name(from.getName())
                .surname(from.getSurname())
                .number(from.getNumber())
                .email(from.getEmail())
                .build();
    }

}

