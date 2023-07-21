package com.example.paymentsapp.repository;

import com.example.paymentsapp.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Optional<Payment> findByDate(LocalDate date);

}
