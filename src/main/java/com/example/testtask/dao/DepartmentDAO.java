package com.example.testtask.dao;

import com.example.testtask.models.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
@AllArgsConstructor
public class DepartmentDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void addDepartment(Department department) {
        entityManager.persist(department);
    }

    public String whoIsHeadOfDepartment(String departmentName) {
        Query query = entityManager.createNativeQuery(
                "select lector.full_name from lector " +
                        "where lector.id = (select Department.head_of_department from Department where Department.name = ?)");
        query.setParameter(1, departmentName);

        return query.getSingleResult().toString();
    }

    public String showDepartmentStatistics(String departmentName) {

        Query countOfAssistant = entityManager.createNativeQuery(
                "select count(lector.id) from lector\n" +
                        "left join lectors_departments ld on lector.id = ld.lector_id\n" +
                        "right join department on department.id = ld.department_id and department.name = ? \n" +
                        "join degree on degree.id = lector.degree_id and degree.name = 'assistant'"
        ).setParameter(1, departmentName);

        Query countOfAssociateProfessor = entityManager.createNativeQuery(
                "select count(lector.id) from lector\n" +
                        "left join lectors_departments ld on lector.id = ld.lector_id\n" +
                        "right join department on department.id = ld.department_id and department.name = ? \n" +
                        "join degree on degree.id = lector.degree_id and degree.name = 'associate professor'"
        ).setParameter(1, departmentName);

        Query countOfProfessor = entityManager.createNativeQuery(
                "select count(lector.id) from lector\n" +
                        "left join lectors_departments ld on lector.id = ld.lector_id\n" +
                        "right join department on department.id = ld.department_id and department.name = ? \n" +
                        "join degree on degree.id = lector.degree_id and degree.name = 'professor'"
        ).setParameter(1, departmentName);

        String result = new String(
                "assistans - " + countOfAssistant.getSingleResult().toString() + "\n" +
                        "associate professors - " + countOfAssociateProfessor.getSingleResult().toString() + "\n" +
                        "professors - " + countOfProfessor.getSingleResult().toString());

        return result;
    }

    public BigDecimal averageSalary(String departmentName) {
        Query query = entityManager.createNativeQuery(
                        "select avg(lector.salary) from lector\n" +
                                "left join lectors_departments ld on lector.id = ld.lector_id\n" +
                                "right join department d on d.id = ld.department_id\n" +
                                "and d.name = ?")
                .setParameter(1, departmentName);

        return (BigDecimal) query.getSingleResult();
    }

    public int showCountOfEmployeeForDepartment(String departmentName) {
        entityManager.createNativeQuery(
                ""
        ).setParameter(1, departmentName);

        return 0;
    }
}
