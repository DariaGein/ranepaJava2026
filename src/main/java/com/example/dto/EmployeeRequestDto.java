package com.example.dto;

public class EmployeeRequestDto {
}
package ru.ranepa.dto;

import jakarta.validation.constraints.*;
        import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeeRequestDto {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Position is required")
    private String position;

    @NotNull(message = "Salary is required")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal salary;

    @NotNull(message = "Hire date is required")
    @PastOrPresent
    private LocalDate hireDate;
}
