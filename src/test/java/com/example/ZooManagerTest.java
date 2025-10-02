
package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

public class ZooManagerTest {

    // Ensures the Zoo starts empty and does not have data from a previous test that
    // could influence it.
    /*
     * @BeforeEach
     * void setup() {
     * ZooManager setupZooManager = new ZooManager();
     * setupZooManager.clearAllAnimals();
     * setupZooManager.saveState();
     * setupZooManager = null;
     * }
     */
    
    // 1. Declare fields for the test instance
    private ZooManager zooManager;
    private InMemoryZooSerializer testSerializer;

 
    @BeforeEach
    void setup() {
        // Create a fresh in-memory serializer
        testSerializer = new InMemoryZooSerializer();
        zooManager = new ZooManager(testSerializer);
    }

    @Test
    void createApeTest() {
        //ZooManager zooManager = new ZooManager();
        zooManager.createApe();

        assertEquals(1, zooManager.getAnimals().size());
        assertTrue(zooManager.getAnimals().get(0) instanceof Ape);
        assertTrue(zooManager.getAnimals().get(0).getAnimalType() == "Ape");

    }

    @Test
    void createDolphinTest() {
        //ZooManager zooManager = new ZooManager();
        zooManager.createDolphin();

        assertEquals(1, zooManager.getAnimals().size());
        assertTrue(zooManager.getAnimals().get(0) instanceof Dolphin);
        assertTrue(zooManager.getAnimals().get(0).getAnimalType() == "Dolphin");
    }

    @Test
    void createEagleTest() {
        //ZooManager zooManager = new ZooManager();
        zooManager.createEagle();

        assertEquals(1, zooManager.getAnimals().size());
        assertTrue(zooManager.getAnimals().get(0) instanceof Eagle);
        assertTrue(zooManager.getAnimals().get(0).getAnimalType() == "Eagle");
    }

    @Test
    void createBearTest() {
        //ZooManager zooManager = new ZooManager();
        zooManager.createBear();

        assertEquals(1, zooManager.getAnimals().size());
        assertTrue(zooManager.getAnimals().get(0) instanceof Bear);
        assertTrue(zooManager.getAnimals().get(0).getAnimalType() == "Bear");
    }

    @Test
    void sortingTest1() {
        //ZooManager zooManager = new ZooManager();

        zooManager.createDolphin();
        zooManager.createApe();

        assertEquals("Ape", zooManager.getAnimals().get(0).getAnimalType());
        assertEquals("Dolphin", zooManager.getAnimals().get(1).getAnimalType());
    }

    @Test
    void sortingTest2() {
        //ZooManager zooManager = new ZooManager();

        zooManager.createBear();
        zooManager.createEagle();
        zooManager.createDolphin();

        assertEquals("Eagle", zooManager.getAnimals().get(2).getAnimalType());
    }

    @Test
    void eatingShouldIncreaseWeight() {
        //ZooManager zooManager = new ZooManager();
        int previousWeight;
        int currentWeight;
        zooManager.createDolphin();

        previousWeight = zooManager.getAnimals().get(0).getWeight();
        zooManager.getAnimals().get(0).eat();
        currentWeight = zooManager.getAnimals().get(0).getWeight();

        assertTrue(previousWeight < currentWeight);
    }

    @Test
    void getAnimalByIndexTest() {
        //ZooManager zooManager = new ZooManager();
        zooManager.createBear();
        zooManager.createBear();
        zooManager.createDolphin();
        zooManager.createEagle();

        assertTrue(zooManager.getAnimalByIndex(2) instanceof Dolphin);

        assertThrows(IndexOutOfBoundsException.class, () -> zooManager.getAnimalByIndex(20));
        assertThrows(IndexOutOfBoundsException.class, () -> zooManager.getAnimalByIndex(-1));
    }

    @Test
    void restrictOuterAnimalChanges() {
        //ZooManager zooManager = new ZooManager();

        zooManager.createApe();
        List<Animal> animals = zooManager.getAnimals();

        assertThrows(UnsupportedOperationException.class, () -> animals.clear());
        assertThrows(UnsupportedOperationException.class, () -> animals.remove(0));
        assertThrows(UnsupportedOperationException.class, () -> animals.sort(null));

    }

    @Test
    void sortByWeight() {
        //ZooManager zooManager = new ZooManager();
        zooManager.createApe();
        zooManager.createApe();
        zooManager.createBear();

        zooManager.sortByWeight(false);
        assertTrue(zooManager.getAnimals().get(0) instanceof Bear);

        zooManager.sortByWeight(true);
        assertTrue(zooManager.getAnimals().get(2) instanceof Bear);
    }

    @Test
    void sortByType() {
        //ZooManager zooManager = new ZooManager();
        zooManager.createApe();
        zooManager.createApe();
        zooManager.createDolphin();
        zooManager.createApe();

        zooManager.sortByType(false);
        assertTrue(zooManager.getAnimals().get(0) instanceof Dolphin);

        zooManager.sortByType(true);
        assertTrue(zooManager.getAnimals().get(3) instanceof Dolphin);

    }

    @Test
    void animalsSortTest() {
        List<Animal> animals = new LinkedList<>();
        animals.add(new Bear());
        animals.add(new Ape());

        animals.sort(null);
        assertTrue(animals.get(0).getAnimalType().equals("Ape"));
    }

    @Test
    void onlySwimmingAnimalsLoseWeightWhenSwimming() {
        //ZooManager zoomanager = new ZooManager();
        List<SwimmingAnimal> swimmingAnimals = new LinkedList<>();
        List<Animal> animals = new LinkedList<>();

        zooManager.createDolphin();
        zooManager.createEagle();
        zooManager.createFish();
        int fishCurrentWeight;
        int fishPreviousWeight;
        int dolphinCurrentWeight;
        int dolphinPreviousWeight;
        int eagleCurrentWeight;
        int eaglePreviousWeight;

        // Speichere die Gewichte (Previous)
        animals = zooManager.getAnimals();
        dolphinPreviousWeight = animals.get(0).weight;
        eaglePreviousWeight = animals.get(1).weight;
        fishPreviousWeight = animals.get(2).weight;

        // Alle Wassertiere schwimmen
        swimmingAnimals = zooManager.getSwimmingAnimals();
        swimmingAnimals.forEach(SwimmingAnimal::swim);
        swimmingAnimals.forEach(animal -> animal.swim());
        swimmingAnimals.forEach(a -> a.swim());

        /*
         * *
         * for (int i = 0; i < swimmingAnimals.size(); i++) {
         * swimmingAnimals.get(i).swim();
         * }
         */
        dolphinCurrentWeight = animals.get(0).weight;
        eagleCurrentWeight = animals.get(1).weight;
        fishCurrentWeight = animals.get(2).weight;

        assertTrue(fishPreviousWeight > fishCurrentWeight);
        assertTrue(dolphinPreviousWeight > dolphinCurrentWeight);
        assertTrue(eaglePreviousWeight == eagleCurrentWeight);

    }

    @Test
    void categorizingAnimals() {
        List<SwimmingAnimal> swimmingAnimals = new LinkedList<>();
        List<FlyingAnimal> flyingAnimals = new LinkedList<>();
        List<ClimbingAnimal> climbingAnimals = new LinkedList<>();

        //ZooManager zoomanager = new ZooManager();
        // 2x Bat/1x Ape/1 Dolphin/ 1 Fish/3 Bear/1 Eagle ==>
        // 3Flying/2Swimming/4Climbing
        zooManager.createApe();
        zooManager.createDolphin();
        zooManager.createBear();
        zooManager.createBear();
        zooManager.createBear();
        zooManager.createFish();
        zooManager.createBat();
        zooManager.createBat();
        zooManager.createEagle();

        swimmingAnimals = zooManager.getSwimmingAnimals();
        flyingAnimals = zooManager.getFlyingAnimals();
        climbingAnimals = zooManager.getClimbingAnimals();

        assertTrue(swimmingAnimals.size() == 2);
        assertTrue(swimmingAnimals.get(0) instanceof Dolphin || swimmingAnimals.get(0) instanceof Fish);
        assertTrue(swimmingAnimals.get(1) instanceof Dolphin || swimmingAnimals.get(1) instanceof Fish);

        assertTrue(flyingAnimals.size() == 3);

        assertTrue(climbingAnimals.size() == 4);

    }

    @Test
    void onlyClimbingAnimalsCanclimb() {

        Dolphin dolphin = new Dolphin();
        assertThrows(Exception.class, () -> {
            ((ClimbingAnimal) dolphin).climb();
        });
    }

    @Test
    void SumOfAllAnimalWeightsTest() {
        // Sum should be between 6000 (inclusive) and 7800 (inclusive) when 3 Bears are
        // created.
        int sum = 0;
        //ZooManager zooManager = new ZooManager();
        zooManager.createBear();
        zooManager.createBear();
        zooManager.createBear();

        sum = zooManager.getSumOfAllAnimalWeights();

        assertTrue(sum >= 6000 && sum <= 7800);

        // Sum should be exactly 60 when 3 Fish are created.
        InMemoryZooSerializer freshSerializer = new InMemoryZooSerializer();
        ZooManager zooManager2 = new ZooManager(freshSerializer);

        sum = 0;
        zooManager2.createFish();
        zooManager2.createFish();
        zooManager2.createFish();

        sum = zooManager2.getSumOfAllAnimalWeights();

        assertEquals(60, sum);
    }

    @Test
    void SumOfAllAnimalWeightsWithStreamsTest() {
        // Sum should be exactly 60 when 3 Fish are created.
        int sum = 0;
        //ZooManager zooManager = new ZooManager();
        zooManager.createFish();
        zooManager.createFish();
        zooManager.createFish();

        sum = zooManager.getSumOfAllAnimalWeightsUsingStreams();

        assertEquals(60, sum);
    }

    @Test
    void getAnimalsByTypeTest() {
        //ZooManager zooManager = new ZooManager();
        zooManager.createApe();
        zooManager.createBat();
        zooManager.createBear();

        List<ClimbingAnimal> climbingAnimals = zooManager.getAnimalsByType(ClimbingAnimal.class);
        assertFalse(climbingAnimals.get(0) instanceof Bat);
        assertFalse(climbingAnimals.get(1) instanceof Bat);
        assertTrue(climbingAnimals.get(0) instanceof Ape);
        assertTrue(climbingAnimals.get(1) instanceof Bear);
        assertEquals(2, climbingAnimals.size());
    }

    @Test
    void CreateAnimalByTypeTest() {
        List<Animal> animals = new LinkedList<>();
        //ZooManager zooManager = new ZooManager();
        zooManager.createAnimalByType(Ape.class);
        animals = zooManager.getAnimals();

        assertTrue(animals.get(0) instanceof Ape);
        assertTrue(animals.size() == 1);
        assertTrue(animals.get(0).getWeight() >= 90);

    }

    @Test
    void OnlyReturnSumOfWeightOfClimbingAnimals() {
        //ZooManager zoomanager = new ZooManager();
        zooManager.createApe();
        zooManager.createDolphin();
        zooManager.createDolphin();
        zooManager.createDolphin();
        zooManager.createDolphin();
        zooManager.createDolphin();

        int sumOfWeigthOfClimbingAnimals = 0;
        sumOfWeigthOfClimbingAnimals = zooManager.getSumOfWeightOfAnimalsByType(ClimbingAnimal.class);

        assertTrue(sumOfWeigthOfClimbingAnimals > 0);
        assertTrue(sumOfWeigthOfClimbingAnimals <= 150);

    }

    @Test
    void clearAllAnimalsTest() {
        //ZooManager zooManager = new ZooManager();
        zooManager.createApe();
        zooManager.createApe();
        zooManager.createApe();

        zooManager.clearAllAnimals();
        List<Animal> animals = zooManager.getAnimals();

        assertTrue(animals.size() == 0);
    }

    @Test
    void savingAndLoadingAnimals() {
        InMemoryZooSerializer testSerializer = new InMemoryZooSerializer();
        List<Animal> animals = new LinkedList<>();
        ZooManager zooManager = new ZooManager(testSerializer);
        zooManager.createApe();
        zooManager.createApe();
        zooManager.saveAnimals(); // Save the two apes

        zooManager.createBat(); // This Bat will not be saved.

        animals = zooManager.getAnimals();

        assertEquals(3, animals.size()); // There are 3 animals before the application closes.

        zooManager = null; // Simulates the application closing
        zooManager = new ZooManager(testSerializer); // Simulates the application restarting
        animals = zooManager.getAnimals();

        assertEquals(2, animals.size()); // There are now only the animals that got saved (2 Apes)
    }

    @Test
    void shouldSaveAndLoadStateUsingInterface() {
        // 1. Arrange: Create the in-memory serializer stub
        InMemoryZooSerializer testSerializer = new InMemoryZooSerializer();

        // 2. Arrange: Inject the stub into the first ZooManager
        ZooManager manager1 = new ZooManager(testSerializer);
        manager1.createBear();
        manager1.createDolphin();

        assertEquals(2, manager1.getAnimals().size(), "Manager 1 should have 2 animals.");

        // 3. Act: Save the state (via the injected stub)
        manager1.saveAnimals();

        // 4. Act: Create a second manager, injecting the SAME stub
        ZooManager manager2 = new ZooManager(testSerializer);

        // 5. Assert: Check that the second manager loaded the state from the stub's
        // storage
        List<Animal> loadedAnimals = manager2.getAnimals();
        assertEquals(2, loadedAnimals.size(), "Manager 2 should have loaded 2 animals.");
        assertTrue(loadedAnimals.stream().anyMatch(a -> a instanceof Bear));
        assertTrue(loadedAnimals.stream().anyMatch(a -> a instanceof Dolphin));
    }
}
