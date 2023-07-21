package com.example.paymentsapp.dto.expensesDto;

import com.example.paymentsapp.dto.PaymentDto.PaymentRequest;
import com.example.paymentsapp.model.Expenses;
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
public class ExpensesRequest {
    private BigDecimal expensesValue;
    private String expensesName;
    private String aboutExpenses;
    private LocalDate date;

    public static Expenses convert(ExpensesRequest from){
        return Expenses.builder()
                .expensesValue(from.getExpensesValue())
                .expensesName(from.getExpensesName())
                .aboutExpenses(from.getAboutExpenses())
                .date(from.getDate())
                .build();
    }


}
