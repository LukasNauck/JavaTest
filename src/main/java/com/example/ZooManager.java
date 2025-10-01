package com.example;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class ZooManager {
    private List<Animal> animals = new LinkedList();

    // Tiere erstellen
    public void createApe() {
        Ape newApe = new Ape();
        sortedInsert(newApe);
    }

    public void createDolphin() {
        Dolphin newDolphin = new Dolphin();
        sortedInsert(newDolphin);
    }

    public void createEagle() {
        Eagle newEagle = new Eagle();
        sortedInsert(newEagle);
    }

    public void createBear() {
        Bear newBear = new Bear();
        sortedInsert(newBear);
    }

    public void createFish() {
        Fish newfish = new Fish();
        sortedInsert(newfish);
    }

    public void createBat() {
        Bat newBat = new Bat();
        sortedInsert(newBat);
    }

    public void CreateAnimalByType(){



    }

    public Animal getAnimalByIndex(int index) {
        if (animals.size() <= index) {
            throw new IndexOutOfBoundsException("Index " + index + " is greater than the size of the list");
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " is negative");
        }
        return animals.get(index);
    }

    public List<Animal> getAnimals() {
        /*
         * List<Animal> animalsCopy = new LinkedList();
         * animalsCopy.addAll(animals);
         * return animalsCopy;
         */
        return List.copyOf(animals);
    }

    private void sortedInsert(Animal newAnimal) {
        String newType = newAnimal.getAnimalType();

        for (int i = 0; i < animals.size(); i++) {
            String currentType = animals.get(i).getAnimalType();

            if (newType.compareTo(currentType) <= 0) {
                animals.add(i, newAnimal);
                return;
            }
        }
        animals.add(newAnimal);
    }

    // Eine Methode implentieren, die SortByType heißt und als Paramter kann man auf
    // oder absteigend angeben
    public void sortByType(boolean isAscending) {
        if (isAscending == true) {
            animals.sort((animal1, animal2) -> animal1.getAnimalType().compareTo(animal2.getAnimalType()));
        } else {
            animals.sort((animal2, animal1) -> animal1.getAnimalType().compareTo(animal2.getAnimalType()));

        }
    }

    // SortByWeight (als Paramter aufsteigend oder absteigend)
    public void sortByWeight(boolean isAscending) {
        if (isAscending == true) {
            animals.sort((animal1, animal2) -> Integer.compare(animal1.getWeight(), animal2.getWeight()));
        } else {
            animals.sort((animal2, animal1) -> Integer.compare(animal1.getWeight(), animal2.getWeight()));
        }
    }

    // Kategorien
    public List<SwimmingAnimal> getSwimmingAnimals() { 
        List<SwimmingAnimal> swimmingAnimals = new LinkedList<>();

        for (int i = 0; i < animals.size(); i++) {
            if (getAnimalByIndex(i) instanceof SwimmingAnimal) {
                swimmingAnimals.add((SwimmingAnimal) getAnimalByIndex(i));
            }
        }
        return List.copyOf(swimmingAnimals);
    }

    public List<FlyingAnimal> getFlyingAnimals() {
        return animals.stream()
        .filter(animal -> animal instanceof FlyingAnimal) 
        .map(FlyingAnimal.class::cast)        
        .collect(Collectors.toUnmodifiableList());  
    }

    public List<ClimbingAnimal> getClimbingAnimals() {
    return animals.stream()
        .filter(animal -> animal instanceof ClimbingAnimal) 
        .map(ClimbingAnimal.class::cast)      //Konvertiere animal zu Climbing animal                
        .collect(Collectors.toUnmodifiableList());           
}

    public <T> List<T> getAnimalsByType(Class<T> targetClass) {
    return animals.stream()
        .filter(targetClass::isInstance) 
        .map(targetClass::cast)         
        .collect(Collectors.toUnmodifiableList());
}

    public int getSumOfAllAnimalWeights() {
        int totalWeight = 0;
        for (Animal animal : animals) { // Kann man das auch mit -> lösen?
            totalWeight += animal.getWeight();
        }
        return totalWeight;
    }

    public int getSumOfAllAnimalWeightsUsingStreams() {
        return animals.stream()
                .mapToInt(animal -> animal.getWeight())
                .sum();
    }

}
