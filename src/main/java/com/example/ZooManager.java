package com.example;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.*;

public class ZooManager {
    private List<Animal> animals = new LinkedList();
    private ZooStateSerializer serializer;

    // Constructor for production/default use
    public ZooManager() {
        this.serializer = new FileSerializer("zoo_manager_state.ser");
        this.animals = serializer.loadAnimals();
    }
    
    // Constructor for testing
    public ZooManager(ZooStateSerializer serializer) {
        this.serializer = serializer;
        this.animals = serializer.loadAnimals();
    }
    
    public void saveAnimals() {
        // Delegate the saving responsibility to the injected serializer
        serializer.saveAnimals(this.animals);
    }

    // Create Animals
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

    // Maybe making this method private in the future would make sense to ensure the
    // User can not remove animals, but currently this does not matter much.
    public void clearAllAnimals() {
        animals = new LinkedList();
    }

    public <T extends Animal> void createAnimalByType(Class<T> animalClass) { // T extends Animal ist wichtig, da
                                                                              // sortedInsert den Typ Animal erwartet
        try {
            T newAnimal = animalClass.getDeclaredConstructor().newInstance();
            sortedInsert(newAnimal);
        } catch (Exception e) {
        }

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
                .map(ClimbingAnimal.class::cast) // Konvertiere animal zu Climbing animal
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
        for (Animal animal : animals) {
            totalWeight += animal.getWeight();
        }
        return totalWeight;
    }

    public int getSumOfAllAnimalWeightsUsingStreams() {
        return animals.stream()
                .mapToInt(animal -> animal.getWeight())
                .sum();
    }

    public <T> int getSumOfWeightOfAnimalsByType(Class<T> animalClass) {
        return animals.stream()
                .filter(animal -> animalClass.isInstance(animal))
                .mapToInt(animal -> animal.getWeight())
                .sum();
    }

/* 

    // Method is private and only gets called by constructor to ensure users can not
    // use this as a clear method by loading empty files.
    private List<Animal> loadState() {
        try (FileInputStream fileIn = new FileInputStream("animals_state.ser");
                ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            System.out.println("Loading a saved state.");
            return (List<Animal>) objectIn.readObject();

        } catch (FileNotFoundException e) {
            System.out.println("No saved state found. Starting with an empty zoo.");
            return new LinkedList<>();
        } catch (Exception e) {
            return new LinkedList<>();
        }
    }

    // Maybe making this method private in the future would make sense too to ensure
    // that the manager can check if the current state is valid to be saved. It
    // could also run this method after each time the animals list changes. This
    // does not matter much currently.
    public void saveState() {
        try (FileOutputStream fileOut = new FileOutputStream("animals_state.ser");
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject(this.animals);
            System.out.println("ZooManager state saved.");

        } catch (IOException e) {
        }
    }
*/
}
