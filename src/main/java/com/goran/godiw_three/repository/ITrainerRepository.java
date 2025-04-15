package com.goran.godiw_three.repository;

import com.goran.godiw_three.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITrainerRepository extends JpaRepository<Trainer, Integer> {
}
