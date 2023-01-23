package com.example.testtask.dao;

import com.example.testtask.models.Lector;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class LectorDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public String searchByTemplate(String template) {
        List<Lector> lectorList = entityManager.createQuery("select l from Lector l", Lector.class).getResultList();
        StringBuilder result = new StringBuilder();

        lectorList.forEach(lector -> {
            if (lector.getFullName().contains(template)) {
                result.append(lector.getFullName()).append(", ");
            }

        });
        return result.toString();
    }


}
