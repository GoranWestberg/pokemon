package com.goran.godiw_three.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Trainer {
    public Trainer() {
        this.myPokemons = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "myTrainer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pokemon> myPokemons;
}
