package com.goran.godiw_three.service;

import com.goran.godiw_three.model.Pokemon;
import com.goran.godiw_three.model.Trainer;
import com.goran.godiw_three.repository.IPokemonRepository;
import com.goran.godiw_three.util.BadRequestException;
import com.goran.godiw_three.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService implements IPokemonService {

    @Autowired
    IPokemonRepository pokeRepo;

    @Override
    public List<Pokemon> getPokemons() {
        return pokeRepo.findAll();
    }

    @Override
    public Pokemon findPokemon(int id) {
        if (id < 1) {
            throw new IllegalArgumentException("ERROR: Invalid ID (" + id + ").");
        }
        return pokeRepo.findById(id).orElseThrow(() -> new BadRequestException("Pokemon with ID " + id + " not found."));
    }

    @Override
    public Pokemon addPokemon(String name, String type, Trainer myTrainer) {
        boolean conditions = (!name.isBlank() && !type.isBlank() && Utilities.IsValid(myTrainer));

        if (!conditions) {
            throw new BadRequestException("Name, type and trainer are required.");
        }

        Pokemon poke = new Pokemon();
        poke.setName(name);
        poke.setType(type);
        poke.setMyTrainer(myTrainer);

        return pokeRepo.save(poke);
    }

    @Override
    public void deletePokemon(int id) {
        Pokemon poke = this.findPokemon(id);
        // Gotta remove the pokemon from the Trainer's list
        pokeRepo.delete(this.findPokemon(id));
    }

    @Override
    public String editPokemon(int id, String newName, String newType, Trainer newTrainer) {
        if (id < 1) {
            throw new IllegalArgumentException("ERROR: Invalid ID (" + id + ").");
        }

        Pokemon poke = this.findPokemon(id);

        if (!Utilities.IsValid(poke)) {
            throw new NullPointerException("ERROR: Pokemon with ID " + id + " not found.");
        }

        if (!newName.isBlank()) {
            poke.setName(newName);
        }

        if (!newType.isBlank()) {
            poke.setType(newType);
        }

        if (Utilities.IsValid(newTrainer)) {
            poke.setMyTrainer(newTrainer);
            //Remove pokemon from old trainer, add pokemon to new trainer
        }

        pokeRepo.save(poke);
        return "Pokemon with ID " + id + " was successfully edited.";
    }
}
