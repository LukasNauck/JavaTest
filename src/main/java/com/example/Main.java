package com.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ZooManager zooManager = new ZooManager();   
        List<Animal> animals = zooManager.getAnimals();    
        zooManager.createBat();

        zooManager.saveAnimals();

        animals = zooManager.getAnimals();
        System.out.println(animals.size());
    }
}
