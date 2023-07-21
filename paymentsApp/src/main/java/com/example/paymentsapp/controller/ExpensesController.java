package com.example.paymentsapp.controller;

import com.example.paymentsapp.dto.PaymentDto.PaymentRequest;
import com.example.paymentsapp.dto.expensesDto.ExpensesRequest;
import com.example.paymentsapp.dto.expensesDto.ExpensesResponse;
import com.example.paymentsapp.model.Payment;
import com.example.paymentsapp.service.ExpensesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expenses")
public class ExpensesController {
    private final ExpensesService expensesService;

    @PostMapping( consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ExpensesResponse> addNewPayment(@RequestPart("expensesRequest") ExpensesRequest expensesRequest,
                                 @RequestPart("files") MultipartFile files) {
        return ResponseEntity.ok(expensesService.addExpenses(expensesRequest, files));
    }

    @GetMapping
    public ResponseEntity<List<ExpensesResponse>> getAllExpenses() {
        return new ResponseEntity<>(expensesService.getAllExpenses(), OK);
    }

    @PutMapping("/{expensesName}")
    public ResponseEntity<ExpensesResponse> updateExpenses(@PathVariable String expensesName
            , @RequestBody ExpensesRequest expensesRequest) {
        return new ResponseEntity<>(expensesService.updateExpenses(expensesName, expensesRequest), OK);

    }
}
