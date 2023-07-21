package com.example.paymentsapp.dto.expensesDto;

import com.example.paymentsapp.dto.PaymentDto.PaymentResponse;
import com.example.paymentsapp.model.Expenses;
import com.example.paymentsapp.model.ExpensesImageModel;
import com.example.paymentsapp.model.ImageModel;
import com.example.paymentsapp.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpensesResponse {
    private BigDecimal expensesValue;
    private String expensesName;
    private String aboutExpenses;
    private LocalDate date;
    private ExpensesImageModel image;


    public static ExpensesResponse convert(Expenses from){
        return ExpensesResponse.builder()
                .expensesValue(from.getExpensesValue())
                .expensesName(from.getExpensesName())
                .aboutExpenses(from.getAboutExpenses())
                .date(from.getDate())
                .image(from.getImage())
                .build();
    }


}
