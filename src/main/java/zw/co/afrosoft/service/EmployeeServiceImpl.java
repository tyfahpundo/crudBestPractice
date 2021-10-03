package zw.co.afrosoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.afrosoft.domain.Employee;
import zw.co.afrosoft.exceptions.BusinessException;
import zw.co.afrosoft.persistence.EmployeeRepository;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee addEmployee(Employee employee) {
        if(employee.getName().isEmpty() || employee.getName().length()==0){
            throw new BusinessException("Name should not be blank");
        }
        try{
            Employee savedEmployee = employeeRepository.save(employee);
            return savedEmployee;
        }catch (IllegalArgumentException e){
            throw new BusinessException("Something went wrong in the Service Layer while adding the employee"+ e.getMessage());
        }

    }

    @Override
    public List<Employee> getAllEmployees() {
        try{
            List<Employee> savedEmployees = employeeRepository.findAll();
            if(savedEmployees.isEmpty()){
                throw new BusinessException("The List of employees is currently empty,We have nothing to return");
            }
            return savedEmployees;
        }catch(Exception e){
            throw new BusinessException("Something went wrong in the Service layer while fetching all employees"+ e.getMessage());
        }

    }

    @Override
    public Employee getEmployeeById(Long id) {
        try{
            Employee employee = employeeRepository.findById(id).get();
            return employee;
        }catch (IllegalArgumentException e){
            throw new BusinessException("the id provided is null,Please provide an Id"+ e.getMessage());
        }catch (NoSuchElementException e){
            throw new BusinessException("There is no element with a given id"+ e.getMessage());
        }

    }

    @Override
    public void deleteEmployeeById(Long id) {
        try{
            employeeRepository.deleteById(id);
        }catch (IllegalArgumentException e){
            throw new BusinessException("Please provide an id of the element you would like to delete");
        }catch (NoSuchElementException e){
            throw new BusinessException("There is no element with a given id to delete");
        }

    }

    @Override
    public List<Employee> getEmployeeByName(String name) {
        try{
            List<Employee> employees = employeeRepository.findByName(name);
            if(employees.isEmpty()){
                throw new BusinessException("There are no elements with the given name");
            }
            return employees;
        }catch (IllegalArgumentException e){
            throw new BusinessException("Please provide a name to search for elements");
        }catch (Exception e){
            throw new BusinessException("Something went wrong while returning the List of employees with the given name");
        }

    }

}
