package ru.ranera.presentation;

import ru.ranera.model.Employee;
import ru.ranera.service.HRMService;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class HRMConsole {
    private final HRMService service;
    private final Scanner scanner = new Scanner(System.in);

    public HRMConsole(HRMService service) { this.service = service; }

    public void start() {
        while (true) {
            System.out.println("=== HRM System Menu ===");
            System.out.println("1. Вывести всех сотрудников\n2. Добавить сотрудника\n3. Удалить сотрудника\n4. Найти сотрудника\n5. Статистика\n6. Выход");

            switch (readInt("Выберите опцию: ")) {
                case 1 -> service.getAllEmployees().forEach(System.out::println);
                case 2 -> addEmployee();
                case 3 -> deleteEmployee();
                case 4 -> findEmployee();
                case 5 -> System.out.println(service.getCompanyStatistics());
                case 6 -> { System.out.println("Выход..."); return; }
                default -> System.out.println("Неверный выбор");
            }
        }
    }

    private void addEmployee() {
        try {
            String name = readString("Имя: ");
            String position = readString("Должность: ");
            double salary = readDouble("Зарплата: ");
            LocalDate hireDate = readDate("Дата найма (ГГГГ-ММ-ДД): ");
            Employee emp = service.addEmployee(name, position, salary, hireDate);
            System.out.printf("Добавлен с ID: %d\n", emp.getId());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void deleteEmployee() {
        Long id = readLong("ID для удаления: ");
        System.out.println(service.deleteEmployee(id) ? "Удален" : "Не найден");
    }

    private void findEmployee() {
        Long id = readLong("ID для поиска: ");
        service.findEmployeeById(id).ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Не найден")
        );
    }

    private String readString(String prompt) { System.out.print(prompt); return scanner.nextLine().trim(); }
    private int readInt(String prompt) { while (true) try { System.out.print(prompt); return Integer.parseInt(scanner.nextLine()); } catch (Exception e) { System.out.println("Ошибка ввода"); } }
    private long readLong(String prompt) { while (true) try { System.out.print(prompt); return Long.parseLong(scanner.nextLine()); } catch (Exception e) { System.out.println("Ошибка ввода"); } }
    private double readDouble(String prompt) { while (true) try { System.out.print(prompt); return Double.parseDouble(scanner.nextLine()); } catch (Exception e) { System.out.println("Ошибка ввода"); } }
    private LocalDate readDate(String prompt) { while (true) try { System.out.print(prompt); return LocalDate.parse(scanner.nextLine()); } catch (DateTimeParseException e) { System.out.println("Неверный формат даты"); } }
}
