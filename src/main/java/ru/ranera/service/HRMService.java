package ru.ranera.service;

import ru.ranera.model.Employee;
import ru.ranera.repository.EmployeeRepository;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class HRMService {
    private final EmployeeRepository repository;

    public HRMService(EmployeeRepository repository) { this.repository = repository; }

    public Employee addEmployee(String name, String position, double salary, LocalDate hireDate) {
        return repository.save(new Employee(null, name, position, salary, hireDate));
    }

    public List<Employee> getAllEmployees() { return repository.findAll(); }
    public Optional<Employee> findEmployeeById(Long id) { return repository.findById(id); }
    public boolean deleteEmployee(Long id) { return repository.delete(id); }

    public double calculateAverageSalary() {
        return repository.findAll().stream().mapToDouble(Employee::getSalary).average().orElse(0.0);
    }

    public Optional<Employee> findTopEarner() {
        return repository.findAll().stream().max(Comparator.comparingDouble(Employee::getSalary));
    }

    public List<Employee> filterByPosition(String position) {
        return repository.findAll().stream()
                .filter(emp -> emp.getPosition().equalsIgnoreCase(position))
                .collect(Collectors.toList());
    }

    public String getCompanyStatistics() {
        List<Employee> employees = repository.findAll();
        double avgSalary = calculateAverageSalary();
        Optional<Employee> topEarner = findTopEarner();

        StringBuilder stats = new StringBuilder("=== Статистика компании ===\n");
        stats.append(String.format("Всего сотрудников: %d\n", employees.size()));
        stats.append(String.format("Средняя зарплата: %.2f\n", avgSalary));
        topEarner.ifPresent(emp -> stats.append(String.format("Самый высокооплачиваемый: %s (%.2f)\n", emp.getName(), emp.getSalary())));
        return stats.toString();
    }
}