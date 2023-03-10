package com.example.testtask;

import com.example.testtask.services.DepartmentService;
import com.example.testtask.services.LectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.logging.Logger;

@SpringBootApplication
public class TestTaskApplication implements CommandLineRunner {

    private static final Logger LOG = Logger.getLogger(TestTaskApplication.class.getName());
    private final DepartmentService departmentService;
    private final LectorService lectorService;

    @Autowired
    public TestTaskApplication(DepartmentService departmentService, LectorService lectorService) {
        this.departmentService = departmentService;
        this.lectorService = lectorService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TestTaskApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        commandOperation();
        run(args);
    }

    public void commandOperation() {
        LOG.info("Input command: ");

        Scanner scanner = new Scanner(System.in);
        String inputCommand = scanner.nextLine();

        if (inputCommand.contains("Who is head of department")) {
            String inputDepartmentName = inputCommand.substring(26);
            String headOfDepartment = departmentService.headOfDepartment(inputDepartmentName);
            LOG.info(headOfDepartment);

        } else if (inputCommand.contains("statistics")) {
            String[] splitString = inputCommand.split("\\s+");
            StringBuilder result = new StringBuilder();

            for (int i = 1; i < splitString.length - 1; i++) {
                result.append(splitString[i]).append(" ");
            }
            String departmentStatistics = departmentService.showDepartmentStatistics(String.valueOf(result));
            LOG.info(departmentStatistics);

        } else if (inputCommand.contains("Show the average salary for the department")) {
            String inputDepartmentName = inputCommand.substring(43);
            BigDecimal result = departmentService.averageSalary(inputDepartmentName);
            LOG.info(result.toString());

        } else if (inputCommand.contains("Show count of employee for")) {
            String inputDepartmentName = inputCommand.substring(27);
            String countOfEmployeeForDepartment = departmentService.countOfEmployeeForDepartment(inputDepartmentName);
            LOG.info(countOfEmployeeForDepartment);

        } else if (inputCommand.contains("Global search by")) {
            String template = inputCommand.substring(17);
            String searchByTemplate = lectorService.searchByTemplate(template);
            LOG.info(searchByTemplate);
        }
    }
}
