package ru.ranera.model;

import java.time.LocalDate;
import java.util.Objects;

public final class Employee {
    private final Long id;
    private final String name;
    private final String position;
    private final double salary;
    private final LocalDate hireDate;

    public Employee(Long id, String name, String position, double salary, LocalDate hireDate) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be empty");
        if (position == null || position.trim().isEmpty()) throw new IllegalArgumentException("Position cannot be empty");
        if (salary <= 0) throw new IllegalArgumentException("Salary must be positive");
        if (hireDate == null) throw new IllegalArgumentException("Hire date cannot be null");
        if (hireDate.isAfter(LocalDate.now())) throw new IllegalArgumentException("Hire date cannot be in future");

        this.id = id;
        this.name = name.trim();
        this.position = position.trim();
        this.salary = salary;
        this.hireDate = hireDate;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getPosition() { return position; }
    public double getSalary() { return salary; }
    public LocalDate getHireDate() { return hireDate; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return String.format("Employee{id=%d, name='%s', position='%s', salary=%.2f, hireDate=%s}",
                id, name, position, salary, hireDate);
    }
}