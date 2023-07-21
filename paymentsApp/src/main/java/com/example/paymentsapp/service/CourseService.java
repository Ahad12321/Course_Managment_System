package com.example.paymentsapp.service;

import com.example.paymentsapp.dto.courseDto.CourseRequest;
import com.example.paymentsapp.dto.courseDto.CourseResponseWithStudents;
import com.example.paymentsapp.exception.CustomEx.CourseNotFoundException;
import com.example.paymentsapp.model.Course;
import com.example.paymentsapp.model.Student;
import com.example.paymentsapp.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final StudentService studentService;

    public void registration(CourseRequest courseRequest) {
        Course course =CourseRequest.convert(courseRequest);
        courseRepository.save(course);
    }

    public List<CourseResponseWithStudents> getAllCourses()  {
        return courseRepository.findAll().stream().map(CourseResponseWithStudents::convert).collect(Collectors.toList());
    }

    public CourseResponseWithStudents getCourse(String name) {
        Course course= (Course) courseRepository.findByName(name).orElseThrow(() -> new CourseNotFoundException("Course not found with name" + name));
        return CourseResponseWithStudents.convert(course);
    }

    public CourseResponseWithStudents updateCourse(String name, CourseRequest courseRequest) {
        Course course= (Course) courseRepository.findByName(name).orElseThrow(()->new CourseNotFoundException("Course not found with name" + name));
        course.setName(courseRequest.getName());
        courseRepository.save(course);
        return CourseResponseWithStudents.convert(course);
    }

    public CourseResponseWithStudents assignCourseToStudent(Long number, String name) {
        Optional<Student> studentOptional = studentService.findByNumber(number);
        Optional<Course> courseOptional = courseRepository.findByName(name);
        if (studentOptional.isPresent() && courseOptional.isPresent()) {
            Student student = studentOptional.get();
            Course course = courseOptional.get();
            course.addStudent(student);

            courseRepository.save(course);
            return CourseResponseWithStudents.convert(course);


        }
         Course course =courseRepository.findByName(name).orElseThrow(() -> new RuntimeException("student number" + number
                + "ve ya lesson name" + name + "tapilmadi"));
        return CourseResponseWithStudents.convert(course);
    }
    }

