package com.goran.godiw_three.repository;

import com.goran.godiw_three.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPokemonRepository extends JpaRepository<Pokemon, Integer> {
}
