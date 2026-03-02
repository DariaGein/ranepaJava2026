package ru.ranera;

import ru.ranera.presentation.HRMConsole;
import ru.ranera.repository.EmployeeRepository;
import ru.ranera.service.HRMService;

public class HrmApplication {
    public static void main(String[] args) {
        new HRMConsole(new HRMService(new EmployeeRepository())).start();
    }
}