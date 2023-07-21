package com.example.paymentsapp.dto.courseDto;

import com.example.paymentsapp.dto.studentDto.StudentRequest;
import com.example.paymentsapp.model.Course;
import com.example.paymentsapp.model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequest {
    private String name;
    public static Course convert(CourseRequest from){
        return Course.builder()
                .name(from.getName())
                .build();
    }

}
