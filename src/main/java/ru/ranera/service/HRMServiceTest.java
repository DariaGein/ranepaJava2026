//package ru.ranera.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import ru.ranera.model.Employee;
//import ru.ranera.repository.EmployeeRepository;
//import java.time.LocalDate;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.*;
//
//class HRMServiceTest {
//    private HRMService service;
//
////    @BeforeEach
//    void setUp() { service = new HRMService(new EmployeeRepository()); }
//
//    @Test
//    void shouldCalculateAverageSalary() {
//        service.addEmployee("Ivan", "Dev", 1000, LocalDate.now());
//        service.addEmployee("Maria", "Manager", 2000, LocalDate.now());
//        service.addEmployee("Petr", "QA", 3000, LocalDate.now());
//        assertEquals(2000, service.calculateAverageSalary());
//    }
//
//    @Test
//    void shouldReturnZeroAverageForEmptyList() {
//        assertEquals(0, service.calculateAverageSalary());
//    }
//
//    @Test
//    void shouldFindTopEarner() {
//        service.addEmployee("Ivan", "Dev", 1000, LocalDate.now());
//        service.addEmployee("Maria", "Manager", 2000, LocalDate.now());
//        service.addEmployee("Petr", "QA", 1500, LocalDate.now());
//        assertEquals("Maria", service.findTopEarner().get().getName());
//    }
//
//    @Test
//    void shouldReturnEmptyForNoTopEarner() {
//        assertTrue(service.findTopEarner().isEmpty());
//    }
//
//    @Test
//    void shouldFilterByPosition() {
//        service.addEmployee("Ivan", "Dev", 1000, LocalDate.now());
//        service.addEmployee("Maria", "Manager", 2000, LocalDate.now());
//        service.addEmployee("Petr", "Dev", 1500, LocalDate.now());
//        assertEquals(2, service.filterByPosition("Dev").size());
//    }
//
//    @Test
//    void shouldFindById() {
//        Employee emp = service.addEmployee("Ivan", "Dev", 1000, LocalDate.now());
//        assertTrue(service.findEmployeeById(emp.getId()).isPresent());
//    }
//
//    @Test
//    void shouldDeleteById() {
//        Employee emp = service.addEmployee("Ivan", "Dev", 1000, LocalDate.now());
//        assertTrue(service.deleteEmployee(emp.getId()));
//        assertTrue(service.findEmployeeById(emp.getId()).isEmpty());
//    }
//
//    @Test
//    void shouldNotDeleteNonExistent() {
//        assertFalse(service.deleteEmployee(999L));
//    }
//
//    @Test
//    void shouldNotAddNegativeSalary() {
//        assertThrows(IllegalArgumentException.class,
//                () -> service.addEmployee("Ivan", "Dev", -1000, LocalDate.now()));
//    }
//
//    @Test
//    void shouldNotAddFutureHireDate() {
//        assertThrows(IllegalArgumentException.class,
//                () -> service.addEmployee("Ivan", "Dev", 1000, LocalDate.now().plusDays(1)));
//    }
//}