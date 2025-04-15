package com.goran.godiw_three.service;

import com.goran.godiw_three.model.Pokemon;
import com.goran.godiw_three.model.Trainer;

import java.util.List;

public interface IPokemonService {
    public List<Pokemon> getPokemons();

    public Pokemon addPokemon(String name, String type, Trainer myTrainer);

    public void deletePokemon(int id);

    public String editPokemon(int id, String newName, String newType, Trainer newTrainer);

    public Pokemon findPokemon(int id);
}
