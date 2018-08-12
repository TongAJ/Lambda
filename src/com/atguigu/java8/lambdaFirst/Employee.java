package com.atguigu.java8.lambdaFirst;

import java.util.Objects;

public class Employee {
    private String name;
    private Integer age;
    private double salary;
    private STATUS status;

    public Employee(String name, Integer age, double salary, STATUS status) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Double.compare(employee.salary, salary) == 0 &&
                Objects.equals(name, employee.name) &&
                Objects.equals(age, employee.age) &&
                status == employee.status;
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, age, salary, status);
    }

    public void setStatus(STATUS status) {

        this.status = status;
    }

    public Employee() {
    }

    public Employee(String name, Integer age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public STATUS getStatus() {
        return status;
    }

    public enum STATUS{
        FREE,
        BUSY,
        VOCATION
    }
}
