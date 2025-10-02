package com.example;

import java.util.LinkedList;
import java.util.List;

public class InMemoryZooSerializer implements ZooStateSerializer {

    // This list stores the state for the test run, avoiding file I/O
    private List<Animal> storage = new LinkedList<>(); 

    @Override
    public List<Animal> loadAnimals() {
        return new LinkedList<>(storage); 
    }

    @Override
    public void saveAnimals(List<Animal> animals) {
        this.storage = new LinkedList<>(animals); 
    }

    // Helper to ensure state is clean
    public void clearStorage() {
        this.storage.clear();
    }
}