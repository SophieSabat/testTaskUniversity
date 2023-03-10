package com.example.testtask.services;

import com.example.testtask.dao.LectorDAO;
import com.example.testtask.models.Lector;
import org.springframework.stereotype.Service;

@Service
public class LectorService {
    private final LectorDAO lectorDAO;
    public LectorService(LectorDAO lectorDAO) {
        this.lectorDAO = lectorDAO;
    }

    public void addLector(Lector lector) {
        if (lector != null) {
            lectorDAO.addLector(lector);
        }
    }

    public String searchByTemplate(String template) {
        return lectorDAO.searchByTemplate(template);
    }
}
