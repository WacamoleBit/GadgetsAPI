package com.manu.GadgetAPI.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.manu.GadgetAPI.Models.Gadget;

public interface IGadget extends CrudRepository<Gadget, Integer> {
    List<Gadget> findByName(String name);
    List<Gadget> findByBrand(String brand);
    List<Gadget> findByModel(String model);
    List<Gadget> findByPlatform(String platform);
    List<Gadget> findByPrice(String price);
    List<Gadget> findByType(String type);
}
