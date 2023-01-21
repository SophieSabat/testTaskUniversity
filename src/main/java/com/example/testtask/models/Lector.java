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
@Table(name = "lectors")
public class Lector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "full_name")
    private String fullName;
    private int salary;

    @Setter(AccessLevel.PRIVATE)
    @ManyToOne
    @JoinColumn(name = "degree_id")
    public Degree degree;

    @Setter(AccessLevel.PRIVATE)
    @ManyToMany
    @JoinTable(
            name = "lectors_departments",
            joinColumns = @JoinColumn(name = "lector_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    public List<Department> departments = new ArrayList<>();

    @OneToOne(mappedBy = "headOfDepartment")
    public Department headOfWhichDepartment;

    public Lector(String fullName) {
        this.fullName = fullName;
    }
}
