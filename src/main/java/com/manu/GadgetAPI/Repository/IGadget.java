package com.manu.GadgetAPI.Repository;

import org.springframework.data.repository.CrudRepository;

import com.manu.GadgetAPI.Models.Gadget;

public interface IGadget extends CrudRepository<Gadget, Integer> {
    
}
