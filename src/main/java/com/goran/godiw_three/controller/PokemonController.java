package com.goran.godiw_three.controller;

import com.goran.godiw_three.model.Pokemon;
import com.goran.godiw_three.model.Trainer;
import com.goran.godiw_three.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PokemonController {

    @Autowired
    PokemonService pokeService;

    @GetMapping("pokemon/pokemons/getAll")
    public List<Pokemon> getPokemons() {
        return pokeService.getPokemons();
    }

    @GetMapping("pokemon/pokemons/getPoke/{id}")
    public Pokemon getPokemon(@PathVariable int id) {
        return pokeService.findPokemon(id);
    }

    @PostMapping("pokemon/pokemons/create")
    public void createPokemon(@RequestParam String name,
                                @RequestParam String type,
                                @RequestParam Trainer trainer) {

        pokeService.addPokemon(name, type, trainer);
    }

    @PutMapping("pokemon/pokemons/edit/{id}")
    public String editPokemon(@PathVariable int id,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String type,
                              @RequestParam(required = false) Trainer trainer) {

        return pokeService.editPokemon(id, name, type, trainer);
    }

    @DeleteMapping("pokemon/pokemons/delete/{id}")
    public void deletePokemon(@PathVariable int id) {
        pokeService.deletePokemon(id);
    }
}
