package com.example.paymentsapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Blob;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long expensesId;
    private BigDecimal expensesValue;
    private LocalDate date;
    private String expensesName;
    @Column(columnDefinition = "text")
    private String aboutExpenses;
    @OneToOne(mappedBy = "expenses", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ExpensesImageModel image;


}
