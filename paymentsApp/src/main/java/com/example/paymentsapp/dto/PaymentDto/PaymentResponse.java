package com.example.paymentsapp.dto.PaymentDto;

import com.example.paymentsapp.dto.studentDto.StudentRequest;
import com.example.paymentsapp.dto.studentDto.StudentResponse;
import com.example.paymentsapp.model.ImageModel;
import com.example.paymentsapp.model.Payment;
import com.example.paymentsapp.model.Student;
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
public class PaymentResponse {
    private BigDecimal amount;
    private Integer month;
    private String courseName;//ve hemin student bu kursu goturubse
    private String student;//eger bu adda student
    private String cardOwner;
    private ImageModel image;
    private LocalDate date;
    public static PaymentResponse convert(Payment from){
        return PaymentResponse.builder()
                .amount(from.getAmount())
                .month(from.getMonth())
                .courseName(from.getCourseName())
                .student(from.getStudent())
                .cardOwner(from.getCardOwner())
                .image(from.getImage())
                .date(from.getDate())
                .build();
    }
}
