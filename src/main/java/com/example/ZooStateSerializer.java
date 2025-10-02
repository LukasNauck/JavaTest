package com.example;

import java.util.List;

public interface ZooStateSerializer {
    
    List<Animal> loadAnimals();

    void saveAnimals(List<Animal> animals);
}