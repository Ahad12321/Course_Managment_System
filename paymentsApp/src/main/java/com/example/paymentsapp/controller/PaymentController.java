package com.example.paymentsapp.controller;

import com.example.paymentsapp.dto.PaymentDto.PaymentRequest;
import com.example.paymentsapp.dto.PaymentDto.PaymentResponse;
import com.example.paymentsapp.dto.studentDto.StudentResponse;
import com.example.paymentsapp.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PaymentResponse> addNewPayment(@RequestPart("payment") PaymentRequest payment,
                                                         @RequestPart("files") MultipartFile files) {
        return ResponseEntity.ok(paymentService.addNewPayment(payment, files));


    }

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getAllPayments() {
        return new ResponseEntity<>(paymentService.getAllPayments(), OK);
    }

    @PutMapping("/{date}")
    public ResponseEntity<PaymentResponse> updateExpenses(@PathVariable LocalDate date
            , @RequestBody PaymentRequest paymentRequest) {
        return new ResponseEntity<>(paymentService.updatePayment(date, paymentRequest), OK);

    }
    @GetMapping("/{date}")
    public ResponseEntity<PaymentResponse> getStudent(@PathVariable LocalDate date) {

        return new ResponseEntity<>(paymentService.getPayment(date), OK);
    }
}

