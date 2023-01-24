package com.example.testtask.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "degree")
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String name;

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy="degree")
    private List<Lector> lectors;

    public Degree(String name) {
        this.name = name;
    }
}
