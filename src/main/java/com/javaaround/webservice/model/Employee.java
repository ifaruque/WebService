package com.javaaround.webservice.model;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {
 
    private Integer id;
    private String name;
    private Double salary;
 
    // This default constructor is required if there are other constructors.
    public Employee() {
 
    }
 
    public Employee(Integer id, String name, Double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
 
    public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public Double getSalary() {
        return salary;
    }
 
    public void setSalary(Double salary) {
        this.salary = salary;
    }
 
}