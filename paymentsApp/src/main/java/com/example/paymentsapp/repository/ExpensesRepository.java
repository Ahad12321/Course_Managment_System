package com.example.paymentsapp.repository;

import com.example.paymentsapp.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpensesRepository extends JpaRepository<Expenses,Long> {
    Optional<Expenses> findByExpensesName(String name);
}

