package com.example.dto;

public class EmployeeResponseDto {
}
package ru.ranepa.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class EmployeeResponseDto {
    private Long id;
    private String name;
    private String position;
    private BigDecimal salary;
    private LocalDate hireDate;
}