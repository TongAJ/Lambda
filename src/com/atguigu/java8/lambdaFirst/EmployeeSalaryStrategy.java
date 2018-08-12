package com.atguigu.java8.lambdaFirst;

public class EmployeeSalaryStrategy implements MyStrategy<Employee> {
    @Override
    public boolean compareEmployeeAttr(Employee employee) {
        return employee.getSalary()<5000;
    }
}
