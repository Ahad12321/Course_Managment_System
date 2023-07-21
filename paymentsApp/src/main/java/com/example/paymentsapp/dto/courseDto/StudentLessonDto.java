package com.example.paymentsapp.dto.courseDto;

import com.example.paymentsapp.dto.studentDto.StudentRequest;
import com.example.paymentsapp.model.Student;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StudentLessonDto {
    private String studentName;
    private String surname;
    private Long number;
    public static StudentLessonDto convert(Student from){
        return StudentLessonDto.builder()
                .studentName(from.getName())
                .surname(from.getSurname())
                .number(from.getNumber())
                .build();
    }
}
