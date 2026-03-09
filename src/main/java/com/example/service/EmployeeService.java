package com.example.service;

public class EmployeeService {
}
package ru.ranepa.service;

import ru.ranepa.dto.EmployeeResponseDto;
import ru.ranepa.dto.EmployeeStatsDto;
import ru.ranepa.exception.ResourceNotFoundException;
import ru.ranepa.model.Employee;
import ru.ranepa.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<EmployeeResponseDto> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public EmployeeResponseDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        return mapToDto(employee);
    }

    public EmployeeResponseDto createEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return mapToDto(savedEmployee);
    }

    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }

    public List<EmployeeResponseDto> getEmployeesByPosition(String position) {
        return employeeRepository.findByPosition(position).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public EmployeeStatsDto getEmployeeStats() {
        List<Employee> allEmployees = employeeRepository.findAll();
        long total = allEmployees.size();

        if (total == 0) {
            return EmployeeStatsDto.builder()
                    .totalEmployees(0)
                    .averageSalary(BigDecimal.ZERO)
                    .topEarnerName("N/A")
                    .topEarnerSalary(BigDecimal.ZERO)
                    .build();
        }

        BigDecimal averageSalary = allEmployees.stream()
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(total), 2, RoundingMode.HALF_UP);

        Employee topEarner = allEmployees.stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow();

        return EmployeeStatsDto.builder()
                .totalEmployees(total)
                .averageSalary(averageSalary)
                .topEarnerName(topEarner.getName())
                .topEarnerSalary(topEarner.getSalary())
                .build();
    }

    private EmployeeResponseDto mapToDto(Employee employee) {
        return EmployeeResponseDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .position(employee.getPosition())
                .salary(employee.getSalary())
                .hireDate(employee.getHireDate())
                .build();
    }
}
