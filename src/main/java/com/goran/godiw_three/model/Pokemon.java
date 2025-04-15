package com.goran.godiw_three.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name, type;

    @ManyToOne
    @JoinColumn(name = "trainer_id",
               referencedColumnName = "id")
    private Trainer myTrainer;
}
