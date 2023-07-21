package com.example.paymentsapp.controller;

import com.example.paymentsapp.dto.courseDto.CourseRequest;
import com.example.paymentsapp.dto.courseDto.CourseResponseWithStudents;
import com.example.paymentsapp.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registration(@RequestBody CourseRequest courseRequest) {
        courseService.registration(courseRequest);
    }
    @GetMapping
    public ResponseEntity<List<CourseResponseWithStudents>> getCourse() {
        return new ResponseEntity<>(courseService.getAllCourses(), OK);
    }
    @GetMapping("/{name}")
    public ResponseEntity<CourseResponseWithStudents> getStudent(@PathVariable String name) {
        return new ResponseEntity<>(courseService.getCourse(name), OK);
    }
    @PutMapping("/{name}")
        public ResponseEntity<CourseResponseWithStudents>updateStudent(@PathVariable String name
                ,@RequestBody CourseRequest courseRequest){
            return new ResponseEntity<>(courseService.updateCourse(name,courseRequest),OK);
        }
    @PutMapping("/{number}/course/{name}")
    private CourseResponseWithStudents assignCourseToStudent(
            @PathVariable Long number,
            @PathVariable String name){
        return courseService.assignCourseToStudent(number,name);
    }
}
