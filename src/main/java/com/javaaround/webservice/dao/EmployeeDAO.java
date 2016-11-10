package com.javaaround.webservice.dao;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import com.javaaround.webservice.model.Employee;
 
public class EmployeeDAO {
 
    private static final Map<Integer, Employee> empMap = new HashMap<Integer, Employee>();
 
    static {
        Employee emp1 = new Employee(1, "Shamim Miah", 1200.00);
        Employee emp2 = new Employee(2, "Alamin", 13000.00);
        Employee emp3 = new Employee(3, "Sohag", 25000.00);
 
        empMap.put(emp1.getId(), emp1);
        empMap.put(emp2.getId(), emp2);
        empMap.put(emp3.getId(), emp3);
    }
 
 
    public static Employee getEmployee(Integer id) {
        return empMap.get(id);
    }
 
    public static Employee addEmployee(Employee emp) {
        empMap.put(emp.getId(), emp);
        return emp;
    }
 
    public static Employee updateEmployee(Employee emp) {
        empMap.put(emp.getId(), emp);
        return emp;
    }
 
    public static void deleteEmployee(Integer id) {
        empMap.remove(id);
    }
 
    public static List<Employee> getAllEmployees() {
        Collection<Employee> c = empMap.values();
        List<Employee> list = new ArrayList<Employee>();
        list.addAll(c);
        return list;
    }
   
 
}