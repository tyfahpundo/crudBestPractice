package zw.co.afrosoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.afrosoft.domain.Employee;
import zw.co.afrosoft.exceptions.BusinessException;
import zw.co.afrosoft.persistence.EmployeeRepository;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee addEmployee(Employee employee) {
        try{
            if(employee.getName().isEmpty() || employee.getName().length()==0){
                throw new BusinessException("601","Name should not be blank");
            }
            Employee savedEmployee = employeeRepository.save(employee);
            return savedEmployee;
        }catch (IllegalArgumentException e){
            throw new BusinessException("602","Given employee is null"+ e.getMessage());
        }catch (Exception e){
            throw new BusinessException("603","Something went wrong in the Service Layer while adding the employee"+ e.getMessage());
        }

    }

    @Override
    public List<Employee> getAllEmployees() {
        try{
            List<Employee> savedEmployees = employeeRepository.findAll();
            if(savedEmployees.isEmpty()){
                throw new BusinessException("604","The List of employees is currently empty,We have nothing to return");
            }
            return savedEmployees;
        }catch(Exception e){
            throw new BusinessException("605","Something went wrong in the Service layer while fetching all employees"+ e.getMessage());
        }

    }

    @Override
    public Employee getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).get();
        return employee;
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getEmployeeByName(String name) {
        List<Employee> employees = employeeRepository.findByName(name);
        return employees;
    }

}
