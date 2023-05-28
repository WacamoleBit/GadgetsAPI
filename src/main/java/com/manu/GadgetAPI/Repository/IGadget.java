package com.manu.GadgetAPI.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.manu.GadgetAPI.Models.Gadget;

public interface IGadget extends CrudRepository<Gadget, Integer> {
    Iterable<Gadget> findByNameOrBrandOrModelOrPlatformOrPriceOrType(
            String name,
            String brand,
            String model,
            String platform,
            double price,
            String type);

    Optional<Gadget> findByName(String name);

    Optional<Gadget> findByBrand(String brand);

    Optional<Gadget> findByModel(String model);

    Optional<Gadget> findByPlatform(String platform);

    Optional<Gadget> findByPrice(double price);

    Optional<Gadget> findByType(String type);
}
