package LaberonLSDZ2.Domain;

import LaberonLSDZ2.Exception.EmployeeAlreadyAddedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Employee implements Comparator<Employee> {
    private final String firstName;
    private final String lastName;

    private int salary;

    private HashSet<Integer> department;


    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public HashSet<Integer> getDepartment() {
        return department;
    }

    public void setDepartment(HashSet<Integer> department) {
        this.department = department;
    }

    public String getFullName() {
        return firstName + " " + lastName;//+" "+salary+" "+department
    }

    public Employee(String firstName, String lastName, int salary, int department) {//String middleName
        if (firstName.isBlank()) {
           throw new EmployeeAlreadyAddedException("Message");
        }if (lastName.isBlank()) {
           throw new EmployeeAlreadyAddedException("Message");
        }
        this.firstName = StringUtils.capitalize(firstName);
        this.lastName = StringUtils.capitalize(lastName);
        this.salary = salary;
//        this.department = department;
        this.department = new HashSet<>(List.of(department));
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.salary - o2.salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }


    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", department=" + department +
                '}';
    }
}