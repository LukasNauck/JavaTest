package com.example;

import java.util.Random;

public abstract class Animal implements Comparable<Animal>,java.io.Serializable {
    String animalType;
    int weight;
    int startingWeightMax;
    int startingWeightMin;
    int weightGainMin;
    int weightGainMax;

    public Animal(int startingWeightMin, int startingWeightMax, int weightGainMin, int weightGainMax,
            String animalType) {
        this.startingWeightMin = startingWeightMin;
        this.startingWeightMax = startingWeightMax;
        this.weightGainMin = weightGainMin;
        this.weightGainMax = weightGainMax;
        weight = generateStartingWeight();
        this.animalType = animalType;
    }

    public int generateStartingWeight() {
        if (startingWeightMax == startingWeightMin) {
            return startingWeightMin;
        } else {
            Random random = new Random();
            return random.nextInt(startingWeightMax - startingWeightMin) + startingWeightMin;
        }
    }

    public void eat() {
        Random random = new Random();
        weight = weight + random.nextInt(weightGainMax - weightGainMin) + weightGainMin;
    }

    public int getWeight() {
        return weight;
    }

    public abstract void makeSound();

    public String getAnimalType() {
        return this.animalType;
    }

    @Override
    public int compareTo(Animal other) {
        return this.animalType.compareTo(other.animalType);
    }

}