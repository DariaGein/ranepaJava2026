package com.example.dto;

public class EmployeeStatsDto {
}
package ru.ranepa.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class EmployeeStatsDto {
    private long totalEmployees;
    private BigDecimal averageSalary;
    private String topEarnerName;
    private BigDecimal topEarnerSalary;
}