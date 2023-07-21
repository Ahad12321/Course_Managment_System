package com.example.paymentsapp.controller;

import com.example.paymentsapp.dto.studentDto.StudentRequest;
import com.example.paymentsapp.dto.studentDto.StudentResponse;
import com.example.paymentsapp.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResponse> registration(@RequestBody StudentRequest studentRequest) {
        return ResponseEntity.ok(studentService.registration(studentRequest));
    }

    @PutMapping("/{number}")
    public ResponseEntity<StudentResponse>updateStudent(@PathVariable Long number
                                                        ,@RequestBody StudentRequest studentRequest){
        return new ResponseEntity<>(studentService.updateStudent(number,studentRequest),OK);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getStudent() {
        return new ResponseEntity<>(studentService.getAllStudents(), OK);
    }

    @GetMapping("/{number}")
    public ResponseEntity<StudentResponse> getStudent(@PathVariable Long number) {

        return new ResponseEntity<>(studentService.getStudent(number), OK);
    }
}
