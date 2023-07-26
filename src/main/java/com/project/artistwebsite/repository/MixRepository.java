package com.project.artistwebsite.repository;

import com.project.artistwebsite.model.Mix;
import org.springframework.data.repository.CrudRepository;


public interface MixRepository extends CrudRepository<Mix, Integer> {

    // finds mix by mix name in database
    public Mix findMixByMixName(String mixName);
}
