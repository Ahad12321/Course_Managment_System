package com.example.paymentsapp.service;

import com.example.paymentsapp.dto.studentDto.StudentRequest;
import com.example.paymentsapp.dto.studentDto.StudentResponse;
import com.example.paymentsapp.exception.CustomEx.StudentNotFoundException;
import com.example.paymentsapp.model.Student;
import com.example.paymentsapp.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentResponse registration(StudentRequest studentRequest) {
        Student student=StudentRequest.convert(studentRequest);
        studentRepository.save(student);
        return StudentResponse.convert(student);
    }


    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll().stream().map(StudentResponse::convert).collect(Collectors.toList());
    }

    public StudentResponse getStudent(Long number) {
        Student student= (Student) studentRepository.findByNumber(number).orElseThrow(() -> new StudentNotFoundException("Student not found with phone number" + number));
        return StudentResponse.convert(student);
    }

    public StudentResponse updateStudent(Long number, StudentRequest studentRequest) {
        Student student=studentRepository.findByNumber(number)
                .orElseThrow(()->new StudentNotFoundException("Student not found with phone number" + number));
        student.setName(studentRequest.getName());
        student.setEmail(studentRequest.getEmail());
        student.setSurname(studentRequest.getSurname());
        student.setNumber(studentRequest.getNumber());
        studentRepository.save(student);
        return StudentResponse.convert(student);
    }

    public Optional<Student> findByNumber(Long number) {
        return studentRepository.findByNumber(number);
    }
}
