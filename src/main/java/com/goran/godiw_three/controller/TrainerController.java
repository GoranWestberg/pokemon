package com.goran.godiw_three.controller;

import com.goran.godiw_three.model.Pokemon;
import com.goran.godiw_three.model.Trainer;
import com.goran.godiw_three.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrainerController {
    @Autowired
    TrainerService trainerServ;

    @GetMapping("pokemon/trainers/getAll")
    public List<Trainer> getTrainers() {
        return trainerServ.getTrainers();
    }

    @GetMapping("pokemon/trainers/getTrainer/{id}")
    public Trainer getTrainer(int id) {
        return trainerServ.findTrainer(id);
    }

    @PostMapping("pokemon/trainers/create")
    public String createTrainer(@RequestParam String name) {
        return trainerServ.addTrainer(name);
    }

    @PostMapping("pokemons/trainers/addPokemon")
    public String addPokemon(@RequestParam Trainer trainer, Pokemon poke) {
        return trainerServ.addPokemon(trainer, poke);
    }

    @PutMapping("pokemon/trainers/edit/{id}")
    public String editTrainer(@RequestParam int id,
                              @RequestBody(required = false) String name) {

        return trainerServ.editTrainer(id, name);
    }

    @DeleteMapping("pokemon/trainers/delete/{id}")
    public String deleteTrainer(@RequestParam int id) {
        return trainerServ.deleteTrainer(id);
    }
}
