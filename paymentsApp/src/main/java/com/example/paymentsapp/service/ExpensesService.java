package com.example.paymentsapp.service;

import com.example.paymentsapp.dto.expensesDto.ExpensesRequest;
import com.example.paymentsapp.dto.expensesDto.ExpensesResponse;
import com.example.paymentsapp.exception.CustomEx.ExpensesNotFoundException;
import com.example.paymentsapp.model.Expenses;
import com.example.paymentsapp.model.ExpensesImageModel;
import com.example.paymentsapp.repository.ExpensesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExpensesService {
    private final ExpensesRepository expensesRepository;


    public List<ExpensesResponse> getAllExpenses() {
        return expensesRepository.findAll().stream().map(ExpensesResponse::convert).collect(Collectors.toList());

    }

    public ExpensesResponse updateExpenses(String expensesName, ExpensesRequest expensesRequest)  {
        Optional<Expenses> expenses = Optional.ofNullable(expensesRepository.findByExpensesName(expensesName)
                .orElseThrow(() -> new ExpensesNotFoundException("Expenses not found with expenses name" + expensesName)));
        if (expenses.isPresent()) {
            Expenses expense = expenses.get();

            if (expensesRequest.getExpensesValue() != null) {
                expense.setExpensesValue(expensesRequest.getExpensesValue());
            }


            if (expensesRequest.getAboutExpenses() != null && !expensesRequest.getAboutExpenses().isEmpty()) {
                expense.setAboutExpenses(expensesRequest.getAboutExpenses());
            }



            if (expensesRequest.getDate() != null) {
                expense.setDate(expensesRequest.getDate());
            }

            if (expensesRequest.getExpensesName() != null && !expensesRequest.getExpensesName().isEmpty()) {
                expense.setExpensesName(expensesRequest.getExpensesName());
            }

            expensesRepository.save(expense);

            return ExpensesResponse.convert(expense);
        } else {
            return null;
        }
    }

    public ExpensesResponse addExpenses(ExpensesRequest expensesRequest, MultipartFile files) {
        try {
            ExpensesImageModel imageModels = uploadImage(files);
            Expenses expenses = Expenses.builder()
                    .expensesValue(expensesRequest.getExpensesValue())
                    .expensesName(expensesRequest.getExpensesName())
                    .aboutExpenses(expensesRequest.getAboutExpenses())
                    .date(expensesRequest.getDate())
                    .image(imageModels)
                    .build();
            expensesRepository.save(expenses);

            return ExpensesResponse.convert(expenses);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return null;
        }
    }

    public ExpensesImageModel uploadImage(MultipartFile file) {

        try {
            ExpensesImageModel imageModel = new ExpensesImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            return imageModel;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
