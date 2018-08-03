package com.atguigu.java8.chapterOne;

public class EmployeeSalaryStrategy implements MyStrategy<Employee> {
    @Override
    public boolean compareEmployeeAttr(Employee employee) {
        return employee.getSalary()<5000;
    }
}
