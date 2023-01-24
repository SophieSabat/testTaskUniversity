package com.example.testtask.dao;

import com.example.testtask.models.Lector;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class LectorDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void addLector(Lector lector) {
        entityManager.persist(lector);
    }

    public String searchByTemplate(String template) {
        List<String> resultList = entityManager.createNativeQuery(
                "select l.full_name from Lector l where l.full_name like '%" + template + "%'").getResultList();

        StringBuilder result = new StringBuilder();

        if (!resultList.isEmpty()) {
            resultList.forEach(lectorName -> {
                if (!result.isEmpty()) {
                    result.append(", ");
                }
                result.append(lectorName);
            });
        }
        return result.toString();
    }
}
