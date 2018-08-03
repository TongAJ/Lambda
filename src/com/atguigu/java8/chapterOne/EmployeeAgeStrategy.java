package com.atguigu.java8.chapterOne;

public class EmployeeAgeStrategy implements MyStrategy<Employee> {
    @Override
    public boolean compareEmployeeAttr(Employee employee) {
        return employee.getAge()>30;
    }
}
