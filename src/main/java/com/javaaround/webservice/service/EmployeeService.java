package com.javaaround.webservice.service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import com.javaaround.webservice.model.Employee;
import com.javaaround.webservice.dao.EmployeeDAO;
 
public class EmployeeService {
 
    EmployeeDAO employeeDAO;

    public EmployeeService() {
       employeeDAO = new EmployeeDAO();
       
    }
    public Employee getEmployee(Integer id) {
        return employeeDAO.getEmployee(id);
    }
    public Employee addEmployee(Employee emp) {
        employeeDAO.addEmployee(emp);
        return emp;
    }
 
    public Employee updateEmployee(Employee emp) {
        employeeDAO.updateEmployee(emp);
        return emp;
    }
 
    public void deleteEmployee(Integer id) {
         employeeDAO.deleteEmployee(id);
    }
 
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }
  
}