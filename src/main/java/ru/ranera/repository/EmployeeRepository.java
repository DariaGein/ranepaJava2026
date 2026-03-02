package ru.ranera.repository;

import ru.ranera.model.Employee;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class EmployeeRepository {
    private final Map<Long, Employee> employees = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Employee save(Employee employee) {
        Objects.requireNonNull(employee, "Employee cannot be null");
        Employee employeeWithId = new Employee(
                idGenerator.getAndIncrement(),
                employee.getName(), employee.getPosition(),
                employee.getSalary(), employee.getHireDate()
        );
        employees.put(employeeWithId.getId(), employeeWithId);
        return employeeWithId;
    }

    public List<Employee> findAll() { return new ArrayList<>(employees.values()); }
    public Optional<Employee> findById(Long id) { return Optional.ofNullable(employees.get(id)); }
    public boolean delete(Long id) { return employees.remove(id) != null; }
    public void clear() { employees.clear(); idGenerator.set(1); }
}
