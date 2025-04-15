package com.goran.godiw_three.service;

import com.goran.godiw_three.model.Pokemon;
import com.goran.godiw_three.model.Trainer;
import com.goran.godiw_three.repository.ITrainerRepository;
import com.goran.godiw_three.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService implements ITrainerService {
    @Autowired
    ITrainerRepository trainerRepo;

    @Override
    public List<Trainer> getTrainers() {
        return trainerRepo.findAll();
    }

    @Override
    public String addTrainer(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("ERROR: Name must not be empty.");
        }

        Trainer trai = new Trainer();
        trai.setName(name);

        trainerRepo.save(trai);
        return "Trainer with name " + name + " successfully created.";
    }

    @Override
    public String deleteTrainer(int id) {
        if (id < 1) {
            throw new IllegalArgumentException("ERROR: Invalid ID (" + id + ").");
        }

        Trainer trai = this.findTrainer(id);

        if (!Utilities.IsValid(trai)) {
            throw new NullPointerException("ERROR: Trainer with ID " + id + " not found.");
        }

        trainerRepo.delete(trai);
        return "Trainer with ID " + id + " deleted.";
    }

    @Override
    public String editTrainer(int id, String newName) {
        if (id < 1) {
            throw new IllegalArgumentException("ERROR: Invalid ID (" + id + ").");
        }

        Trainer trai = this.findTrainer(id);

        if (!Utilities.IsValid(trai)) {
            throw new NullPointerException("ERROR: Trainer with ID " + id + " not found.");
        }

        if (!newName.isBlank()) {
            trai.setName(newName);
        }

        trainerRepo.save(trai);
        return "Trainer with ID " + id + " was successfully edited.";
    }

    @Override
    public Trainer findTrainer(int id) {
        return trainerRepo.findById(id).orElse(null);
    }

    private Pokemon getCollectedPokemon(Trainer trainer, int pokeId) {
        if (pokeId < 1) {
            throw new IllegalArgumentException("ERROR: Invalid ID (" + pokeId + ").");
        }

        if (!Utilities.IsValid(trainer)) {
            throw new NullPointerException("ERROR: Trainer is invalid");
        }

        Pokemon poke = null;
        int i = 0;
        List<Pokemon> trainerPokemons = trainer.getMyPokemons();
        
        while (poke == null && i < trainerPokemons.size()) {
            Pokemon actual = trainerPokemons.get(i);
            if (actual.getId() == pokeId) {
                poke = actual;
            }
            i++;
        }

        return poke;
    }

    @Override
    public String addPokemon(Trainer trainer, Pokemon poke) {
        if (this.getCollectedPokemon(trainer, poke.getId()) != null) {
            throw new IllegalArgumentException("ERROR: Trainer already has Pokemon with ID " + poke.getId() + " in his inventory.");
        }

        trainer.getMyPokemons().add(poke);

        trainerRepo.save(trainer);
        return poke.getName() + "(ID " + poke.getId() + ") successfully added to Trainer's inventory";
    }

    @Override
    public String removePokemon(Trainer trainer, Pokemon pokemon) {
        Pokemon poke = this.getCollectedPokemon(trainer, pokemon.getId());

        if (!Utilities.IsValid(poke)) {
            throw new NullPointerException("ERROR: Invalid Pokemon");
        }

        trainer.getMyPokemons().remove(poke);

        trainerRepo.save(trainer);
        return "Pokemon with ID " + pokemon.getId() + " successfully deleted.";
    }
}
