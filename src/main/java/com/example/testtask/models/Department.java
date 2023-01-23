package com.example.testtask.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", unique = true)
    private String name;

    @NonNull
    @OneToOne
    @JoinColumn(name = "head_of_department")
    private Lector head_of_department;

    @Setter(AccessLevel.PRIVATE)
    @ManyToMany(mappedBy = "departments", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    public List<Lector> lectors = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }
}
