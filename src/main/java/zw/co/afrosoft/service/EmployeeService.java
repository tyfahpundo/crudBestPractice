package zw.co.afrosoft.service;

import zw.co.afrosoft.domain.Employee;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    void deleteEmployeeById(Long id);
}
