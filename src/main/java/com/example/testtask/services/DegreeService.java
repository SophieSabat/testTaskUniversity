package com.example.testtask.services;

import com.example.testtask.dao.DegreeDAO;
import com.example.testtask.models.Degree;
import org.springframework.stereotype.Service;

@Service
public class DegreeService {
    private DegreeDAO degreeDAO;

    public DegreeService(DegreeDAO degreeDAO) {
        this.degreeDAO = degreeDAO;
    }

    public void add(Degree degree) {
        if (degree != null) {
            degreeDAO.addDegree(degree);
        }
    }
}
