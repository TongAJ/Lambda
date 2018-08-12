package com.atguigu.java8.lambdaFirst;

public class EmployeeAgeStrategy implements MyStrategy<Employee> {
    @Override
    public boolean compareEmployeeAttr(Employee employee) {
        return employee.getAge()>30;
    }
}
