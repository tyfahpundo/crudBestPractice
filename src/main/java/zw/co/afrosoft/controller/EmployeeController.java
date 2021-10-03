package zw.co.afrosoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.afrosoft.domain.Employee;
import zw.co.afrosoft.exceptions.BusinessException;
import zw.co.afrosoft.exceptions.ControllerException;
import zw.co.afrosoft.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/code")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/save")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee){
        try{
            Employee savedEmployee = employeeService.addEmployee(employee);
            return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
        }catch (BusinessException e){
            ControllerException ce = new ControllerException(e.getErrorMessage());
            return new ResponseEntity<>(ce, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            ControllerException ce = new ControllerException("Something went wrong in the Controller!");
            return new ResponseEntity<>(ce,HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Employee>>  getAllEmployees(){
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<?>  getEmployeeById(@PathVariable Long id){
        try{
            Employee employee = employeeService.getEmployeeById(id);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }catch (BusinessException e){
            ControllerException ce = new ControllerException(e.getErrorMessage());
            return new ResponseEntity<>(ce, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            ControllerException ce = new ControllerException("Something went wrong in the Controller");
            return new ResponseEntity<>(ce, HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/getEmployeeByName/{name}")
    public ResponseEntity<List<Employee>> getEmployeeByName(@PathVariable String name){
        List<Employee> allEmployees = employeeService.getEmployeeByName(name);
        return new ResponseEntity<>(allEmployees, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable Long id){
        try{
            employeeService.deleteEmployeeById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch (BusinessException e){
            ControllerException ce = new ControllerException(e.getErrorMessage());
            return new ResponseEntity<>(ce, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            ControllerException ce = new ControllerException("Something went wrong in the Controller!");
            return new ResponseEntity<>(ce, HttpStatus.BAD_REQUEST);
        }

    }
    @PutMapping("/update")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee){
        try{
            Employee updatedEmployee = employeeService.addEmployee(employee);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        }catch (BusinessException e){
            ControllerException ce = new ControllerException(e.getErrorMessage());
            return new ResponseEntity<>(ce, HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            ControllerException ce = new ControllerException("Something went wrong in the Controller");
            return new ResponseEntity<>(ce, HttpStatus.BAD_REQUEST);
        }

    }


}
