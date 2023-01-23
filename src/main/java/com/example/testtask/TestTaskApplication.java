package com.example.testtask;

import com.example.testtask.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = Deprecated.class)
public class TestTaskApplication implements CommandLineRunner {

    @Autowired
    private DepartmentService departmentService;

    public TestTaskApplication(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TestTaskApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Input command:");

        Scanner scanner = new Scanner(System.in);
        String inputCommand = scanner.nextLine();

        if (inputCommand.contains("Who is head of department")) {
            String inputDepartmentName = inputCommand.substring(26);
            String headOfDepartment = departmentService.headOfDepartment(inputDepartmentName);
            System.out.println(headOfDepartment);

        } else if (inputCommand.contains("statistics")) {
            String[] splitString = inputCommand.split("\\s+");
            StringBuilder result = new StringBuilder();

            for (int i = 1; i < splitString.length - 1; i++) {
                result.append(splitString[i]).append(" ");
            }

            String departmentStatistics = departmentService.showDepartmentStatistics(String.valueOf(result));
            System.out.println(departmentStatistics);

        } else if (inputCommand.contains("Show the average salary for the department")) {
            String inputDepartmentName = inputCommand.substring(43);
            System.out.println(inputDepartmentName);
            BigDecimal result = departmentService.averageSalary(inputDepartmentName);
            System.out.println(result);

        } else if (inputCommand.contains("Show count of employee for")) {
            String inputDepartmentName = inputCommand.substring(27);
            System.out.println(inputDepartmentName);

        } else if (inputCommand.contains("Global search by")) {
            String template = inputCommand.substring(17);
            System.out.println(template);

        }
    }
}
