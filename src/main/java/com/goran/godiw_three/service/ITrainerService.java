package com.goran.godiw_three.service;

import com.goran.godiw_three.model.Pokemon;
import com.goran.godiw_three.model.Trainer;

import java.util.List;

public interface ITrainerService {
    public List<Trainer> getTrainers();

    public String addTrainer(String name);

    public String deleteTrainer(int id);

    public String editTrainer(int id, String newName);

    public Trainer findTrainer(int id);

    public String addPokemon(Trainer trainer, Pokemon pokemon);

    public String removePokemon(Trainer trainer, Pokemon pokemon);
}
