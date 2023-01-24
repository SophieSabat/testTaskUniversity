package com.example.testtask.dao;

import com.example.testtask.models.Degree;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class DegreeDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void addDegree(Degree degree) {
        entityManager.persist(degree);
    }
}
