package cse364.milestone1application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cse364.milestone1application.domain.Employee;
import cse364.milestone1application.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "employees/")
@RequiredArgsConstructor
public class EmployeeController {
    @Autowired
    private final EmployeeRepository employeeRepository;

    @GetMapping()
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Employee> getById(@PathVariable Long id) {
        return employeeRepository.findById(id);
    }

    @PostMapping()
    public Employee post(@RequestBody EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setRole(employeeDto.getRole());
        return employeeRepository.save(employee);
    }

    @PostMapping("/{id}")
    public Employee postById(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        Employee employee = new Employee(id, employeeDto.getName(), employeeDto.getRole());
        return employeeRepository.save(employee);
    }

    @PutMapping("/{id}")
    public Employee replace(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        employeeRepository.deleteById(id);
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setRole(employeeDto.getRole());
        return employeeRepository.save(employee);
    }

}
