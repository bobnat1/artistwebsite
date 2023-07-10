package com.project.capstone.repository;

import com.project.capstone.model.Mix;
import org.springframework.data.repository.CrudRepository;


public interface MixRepository extends CrudRepository<Mix, Integer> {

    // finds mix by mix name in database
    public Mix findMixByMixName(String mixName);
}
