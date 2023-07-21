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
public class StudentRequest {
    private String name;
    private String surname;
    private Long number;
    private String email;
    public static Student convert(StudentRequest from){
        return Student.builder()
                .name(from.getName())
                .surname(from.getSurname())
                .number(from.getNumber())
                .email(from.getEmail())
                .build();
    }
}
