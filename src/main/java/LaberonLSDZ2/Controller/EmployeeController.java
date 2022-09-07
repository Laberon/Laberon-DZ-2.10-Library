package LaberonLSDZ2.Controller;


import LaberonLSDZ2.Domain.Employee;
import LaberonLSDZ2.Service.EmployeeServiceImpl;
import LaberonLSDZ2.Service.EmployeeServiceStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;
    private final EmployeeServiceStream employeeServiceStream;

    public EmployeeController(EmployeeServiceImpl employeeService, EmployeeServiceStream employeeServiceStream) {
        this.employeeService = employeeService;
        this.employeeServiceStream = employeeServiceStream;
    }

    @GetMapping(path = "/add")
    public String add(@RequestParam("name") String firstName,
                      @RequestParam("lastName") String lastName,
                      @RequestParam("salary") Integer salary,
                      @RequestParam("department") Integer department) {
        if (firstName.isEmpty()) {
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (lastName.isEmpty()) {
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        employeeService.addPerson(firstName,lastName,salary,department);
        return "Добавлен";
    }

    @GetMapping(path = "/find")
    public String find(@RequestParam("name") String firstName,
                       @RequestParam("lastName") String lastName) {//@RequestParam("lastName") String lastName
        return employeeService.findPersons(firstName, lastName).toString();
    }

    @GetMapping(path = "/remove")
    public String remove(@RequestParam("name") String firstname,
                         @RequestParam("lastName") String lastName,
                         int salary,
                         int department) {
        employeeService.removePerson(firstname, lastName, salary, department);
        return "Сотрудник: " + firstname + " удален";
    }

    @GetMapping(path = "/findAll")
    public Collection<Employee> find() {
        return employeeService.getAll();
    }

    @GetMapping(path = "/departments/min-salary")
    public String minSalary(@RequestParam("departments") int departments) {//@RequestParam("lastName") String lastName
        return employeeServiceStream.getSalaryMin(departments);
    }
}
