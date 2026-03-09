package controller;

public class EmployeeController {
}
package ru.ranepa.controller;

import ru.ranepa.dto.EmployeeRequestDto;
import ru.ranepa.dto.EmployeeResponseDto;
import ru.ranepa.dto.EmployeeStatsDto;
import ru.ranepa.model.Employee;
import ru.ranepa.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
        import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Tag(name = "Employee Controller", description = "Управление сотрудниками")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    @Operation(summary = "Получить всех сотрудников")
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить сотрудника по ID")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PostMapping
    @Operation(summary = "Создать нового сотрудника")
    public ResponseEntity<EmployeeResponseDto> createEmployee(@Valid @RequestBody EmployeeRequestDto employeeDto) {
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setPosition(employeeDto.getPosition());
        employee.setSalary(employeeDto.getSalary());
        employee.setHireDate(employeeDto.getHireDate());

        EmployeeResponseDto createdEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить сотрудника по ID")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/position/{position}")
    @Operation(summary = "Найти сотрудников по должности")
    public ResponseEntity<List<EmployeeResponseDto>> getEmployeesByPosition(@PathVariable String position) {
        return ResponseEntity.ok(employeeService.getEmployeesByPosition(position));
    }

    @GetMapping("/stats")
    @Operation(summary = "Получить статистику по компании")
    public ResponseEntity<EmployeeStatsDto> getEmployeeStats() {
        return ResponseEntity.ok(employeeService.getEmployeeStats());
    }
}
