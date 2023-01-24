package com.example.testtask.dao;

import com.example.testtask.models.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

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

        List<Object[]> resultList = entityManager.createNativeQuery(
                "select degree.name, count(lector.id) from lector " +
                        "left join lectors_departments ld on lector.id = ld.lector_id " +
                        "right join department on department.id = ld.department_id and department.name = ? " +
                        "right join degree on lector.degree_id = degree.id " +
                        "group by degree.id"
        ).setParameter(1, departmentName).getResultList();

        StringBuilder result = new StringBuilder();

        for (Object[] objects : resultList) {
            result.append(objects[0])
                    .append(" - ")
                    .append(objects[1])
                    .append("\n");
        }
        return result.toString();
    }

    public BigDecimal averageSalary(String departmentName) {
        Query query = entityManager.createNativeQuery(
                        "select avg(lector.salary) from lector " +
                                "left join lectors_departments ld on lector.id = ld.lector_id " +
                                "right join department d on d.id = ld.department_id " +
                                "and d.name = ?")
                .setParameter(1, departmentName);

        return (BigDecimal) query.getSingleResult();
    }

    public int showCountOfEmployeeForDepartment(String departmentName) {
        Query countOfEmployee = entityManager.createNativeQuery(
                "select count(lector_id) from lectors_departments " +
                        "where department_id = (select department.id from department where department.name = ?)"
        ).setParameter(1, departmentName);

        return countOfEmployee.getFirstResult();
    }
}
