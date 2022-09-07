package LaberonLSDZ2.Service;

import LaberonLSDZ2.Domain.Employee;
import LaberonLSDZ2.Exception.EmployeeAlreadyAddedException;
import LaberonLSDZ2.Exception.EmployeeNotFoundException;
import LaberonLSDZ2.Exception.EmployeeStorageIsFullException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    static final Map<String, Employee> employeeMap = new HashMap<>(Map.of(
//            "РАБОТИН", new Employee("РАБОТИН", "Агафон", 1_000, 1),
//           "НАБИЕВА", new Employee("НАБИЕВА", "Беатрис Бенедиктович", 2000,2),
//            "ДАНИЛЕЙКО", new Employee("ДАНИЛЕЙКО", "Вальтер Вадимович", 3000,3),
//            "ХАВИН",new Employee("ХАВИН", "Дамир Венедиктович", 3000,3),
//            "ШАБАШЕВ", new Employee("ШАБАШЕВ", "Егор Виссарионович", 4000,2),
//            "ЗАДОРИНа",new Employee("ЗАДОРИНа", "Зарина Вячеславович", 5000,1)
    ));


    @Override
    public Employee addPerson(String firstName, String lastName, int salary, int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employeeMap.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Message");
        }
        employeeMap.values().stream()
                .filter(s->StringUtils.isBlank((CharSequence) s))
                .filter(e-> Boolean.parseBoolean(StringUtils.capitalize(String.valueOf(e))));
        employeeMap.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public List<Employee> findPersons(String firstName, String lastName) {
        int salary = 0;
        int department = 0;
        Employee employees = new Employee(firstName, lastName, salary, department);
        if (employeeMap.containsKey(employees.getFullName())) {
            return Collections.singletonList(employeeMap.get(employees.getFullName()));
        }
        throw new EmployeeNotFoundException("Not found");
    }

    @Override
    public Employee removePerson(String firstName, String lastName, int salary, int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);//,middleName
        if (employeeMap.containsKey(employee.getFullName())) {
            return employeeMap.remove(employee.getFullName());
        }
        throw new EmployeeNotFoundException("Message");
    }

    @Override
    public Collection<Employee> getAll() {
        return employeeMap.values();
    }

    @Override
    public List<Employee> getEmployeeDepartment(Integer department) {
        return employeeMap.values().stream()
                .filter(e -> e.getDepartment().contains(department))
                .collect(Collectors.toList());
    }
}