package com.example.paymentsapp.service;

import com.example.paymentsapp.dto.PaymentDto.PaymentRequest;
import com.example.paymentsapp.dto.PaymentDto.PaymentResponse;
import com.example.paymentsapp.exception.CustomEx.PaymentNotFoundException;
import com.example.paymentsapp.model.ImageModel;
import com.example.paymentsapp.model.Payment;
import com.example.paymentsapp.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private final PaymentRepository paymentRepository;



    public PaymentResponse addNewPayment(PaymentRequest payment, MultipartFile files) {

        try {
            ImageModel imageModels = uploadImage(files);
            Payment payment1 = Payment.builder()
                    .date(LocalDate.now())
                    .amount(payment.getAmount())
                    .month(payment.getMonth())
                    .courseName(payment.getCourseName())
                    .student(payment.getStudent())
                    .image(imageModels)
                    .build();
            paymentRepository.save(payment1);
            return PaymentResponse.convert(payment1);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return null;
        }
    }

    public ImageModel uploadImage(MultipartFile file) {

        try {
            ImageModel imageModel = new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            return imageModel;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<PaymentResponse> getAllPayments() {
        return paymentRepository.findAll().stream().map(PaymentResponse::convert).collect(Collectors.toList());

    }

    public PaymentResponse updatePayment(LocalDate date, PaymentRequest paymentRequest)  {
        Optional<Payment> payment = Optional.ofNullable(paymentRepository.findByDate(date)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with date" + date)));
        if (payment.isPresent()) {
            Payment payment1 = payment.get();

            if (paymentRequest.getStudent() != null&& !paymentRequest.getStudent().isEmpty()) {
                payment1.setStudent(paymentRequest.getStudent());
            }


            if (paymentRequest.getAmount() != null ) {
                payment1.setAmount(paymentRequest.getAmount());
            }



            if (paymentRequest.getDate() != null) {
                payment1.setDate(paymentRequest.getDate());
            }

            if (paymentRequest.getMonth() != null) {
                payment1.setMonth(paymentRequest.getMonth());
            }
            if (paymentRequest.getCardOwner() != null&& !paymentRequest.getCardOwner().isEmpty()) {
                payment1.setCardOwner(paymentRequest.getCardOwner());
            }
            if (paymentRequest.getCourseName() != null&& !paymentRequest.getCourseName().isEmpty()) {
                payment1.setCourseName(paymentRequest.getCourseName());
            }

            paymentRepository.save(payment1);

            return PaymentResponse.convert(payment1);
        } else {
            return null;
        }
    }

    public PaymentResponse getPayment(LocalDate date) {
        Payment payment= paymentRepository.findByDate(date).orElseThrow(() -> new PaymentNotFoundException("Payment not found with date" + date));
        return PaymentResponse.convert(payment);

    }
}
