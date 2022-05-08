package com.demo.employee.controller;

import com.demo.employee.dao.EmployeeDAOImpl;
import com.demo.employee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EmployeeController {

    @Autowired
    EmployeeDAOImpl employeeDAO;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        System.out.println("Getting list of all the employees");
        return employeeDAO.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId){
        Employee employee = employeeDAO.getEmployeeById(employeeId);
        if(Objects.isNull(employee)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        employeeDAO.addEmployee(employee);
        return employee;
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId, @RequestBody Employee employeeDetails){
        employeeDAO.updateEmployeeById(employeeId,employeeDetails);
        return getEmployeeById(employeeId);
    }

    @DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId){
        employeeDAO.deleteEmployeeById(employeeId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
