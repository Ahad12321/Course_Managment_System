package com.example.paymentsapp.dto.courseDto;

import com.example.paymentsapp.model.Course;
import lombok.Builder;
import lombok.Data;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Builder
@Data
public class CourseResponseWithStudents {
    private String name;
    private List<StudentLessonDto>students;
    public static CourseResponseWithStudents convert(Course from){
        return CourseResponseWithStudents.builder()
                .name(from.getName())
                . students((from.getStudents()).stream()
                .map(StudentLessonDto::convert).collect(toList()))
                .build();
    }
}
