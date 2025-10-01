package com.example;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

public class ZooManagerTest {

    @Test
    void createApeTest() {
        ZooManager zooManager = new ZooManager();
        zooManager.createApe();

        assertEquals(1, zooManager.getAnimals().size());
        assertTrue(zooManager.getAnimals().get(0) instanceof Ape);
        assertTrue(zooManager.getAnimals().get(0).getAnimalType() == "Ape");

    }

    @Test
    void createDolphinTest() {
        ZooManager zooManager = new ZooManager();
        zooManager.createDolphin();

        assertEquals(1, zooManager.getAnimals().size());
        assertTrue(zooManager.getAnimals().get(0) instanceof Dolphin);
        assertTrue(zooManager.getAnimals().get(0).getAnimalType() == "Dolphin");
    }

    @Test
    void createEagleTest() {
        ZooManager zooManager = new ZooManager();
        zooManager.createEagle();

        assertEquals(1, zooManager.getAnimals().size());
        assertTrue(zooManager.getAnimals().get(0) instanceof Eagle);
        assertTrue(zooManager.getAnimals().get(0).getAnimalType() == "Eagle");
    }

    @Test
    void createBearTest() {
        ZooManager zooManager = new ZooManager();
        zooManager.createBear();

        assertEquals(1, zooManager.getAnimals().size());
        assertTrue(zooManager.getAnimals().get(0) instanceof Bear);
        assertTrue(zooManager.getAnimals().get(0).getAnimalType() == "Bear");
    }

    @Test
    void sortingTest1() {
        ZooManager zooManager = new ZooManager();

        zooManager.createDolphin();
        zooManager.createApe();

        assertEquals("Ape", zooManager.getAnimals().get(0).getAnimalType());
        assertEquals("Dolphin", zooManager.getAnimals().get(1).getAnimalType());
    }

    @Test
    void sortingTest2() {
        ZooManager zooManager = new ZooManager();

        zooManager.createBear();
        zooManager.createEagle();
        zooManager.createDolphin();

        assertEquals("Eagle", zooManager.getAnimals().get(2).getAnimalType());
    }

    @Test
    void eatingShouldIncreaseWeight() {
        ZooManager zooManager = new ZooManager();
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
        ZooManager zooManager = new ZooManager();
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
        ZooManager zooManager = new ZooManager();

        zooManager.createApe();
        List<Animal> animals = zooManager.getAnimals();

        assertThrows(UnsupportedOperationException.class, () -> animals.clear());
        assertThrows(UnsupportedOperationException.class, () -> animals.remove(0));
        assertThrows(UnsupportedOperationException.class, () -> animals.sort(null));

    }

    @Test
    void sortByWeight() {
        ZooManager zooManager = new ZooManager();
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
        ZooManager zooManager = new ZooManager();
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
        Comparable<Animal> comparable = new Ape();
    }

    /*
     * Manager soll die Tiere, die er verwaltet in Kategorien aufteilen:
     * Bsp. Flugtiere/Wassertiere/Erdtiere/
     * Manager soll Methoden bekommen, um alle Tiere einer Kategorie zu bekommen
     * Soll durch den Typ gesteuert werden
     * 
     * Jede Art muss eine für sie spezifische Methode anbieten.
     * 
     * List<Wassertiere> wassertiere = new ArrayList();
     * Wassertier dolphin = new Dolphin();
     * wassertiere.add(dolphin);
     * dolphin.tauchen();
     * 
     */

    @Test
    void onlySwimmingAnimalsLoseWeightWhenSwimming() {
        ZooManager zoomanager = new ZooManager();
        List<SwimmingAnimal> swimmingAnimals = new LinkedList<>();
        List<Animal> animals = new LinkedList<>();

        zoomanager.createDolphin();
        zoomanager.createEagle();
        zoomanager.createFish();
        int fishCurrentWeight;
        int fishPreviousWeight;
        int dolphinCurrentWeight;
        int dolphinPreviousWeight;
        int eagleCurrentWeight;
        int eaglePreviousWeight;

        // Speichere die Gewichte (Previous)
        animals = zoomanager.getAnimals();
        dolphinPreviousWeight = animals.get(0).weight;
        eaglePreviousWeight = animals.get(1).weight;
        fishPreviousWeight = animals.get(2).weight;

        // Alle Wassertiere schwimmen
        swimmingAnimals = zoomanager.getSwimmingAnimals();
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

        ZooManager zoomanager = new ZooManager();
        // 2x Bat/1x Ape/1 Dolphin/ 1 Fish/3 Bear/1 Eagle ==>
        // 3Flying/2Swimming/4Climbing
        zoomanager.createApe();
        zoomanager.createDolphin();
        zoomanager.createBear();
        zoomanager.createBear();
        zoomanager.createBear();
        zoomanager.createFish();
        zoomanager.createBat();
        zoomanager.createBat();
        zoomanager.createEagle();

        swimmingAnimals = zoomanager.getSwimmingAnimals();
        flyingAnimals = zoomanager.getFlyingAnimals();
        climbingAnimals = zoomanager.getClimbingAnimals();

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
    void SumOfAllAnimalWeightsTest(){
    //Sum should be between 6000 (inclusive) and 7800 (inclusive) when 3 Bears are created.
        int sum = 0;
        ZooManager zooManager = new ZooManager();
        zooManager.createBear();
        zooManager.createBear();
        zooManager.createBear();

        sum = zooManager.getSumOfAllAnimalWeights();

        assertTrue(sum>=6000 && sum<=7800);

    //Sum should be exactly 60 when 3 Fish are created.
        sum = 0;
        ZooManager zooManager2 = new ZooManager();
        zooManager2.createFish();
        zooManager2.createFish();
        zooManager2.createFish();

        sum = zooManager2.getSumOfAllAnimalWeights();

        assertEquals(60,sum);
    }

    @Test
    void SumOfAllAnimalWeightsWithStreamsTest(){
        int sum = 0;
        ZooManager zooManager = new ZooManager();
        zooManager.createFish();
        zooManager.createFish();
        zooManager.createFish();

        sum = zooManager.getSumOfAllAnimalWeightsUsingStreams();

        assertEquals(60,sum);
    }
    
    @Test
    void getAnimalsByTypeTest(){
        ZooManager zooManager = new ZooManager();
        zooManager.createApe();
        zooManager.createBat();
        zooManager.createBear();

        List<ClimbingAnimal> climbingAnimals = zooManager.getAnimalsByType(ClimbingAnimal.class);
        assertFalse(climbingAnimals.get(0) instanceof Bat);
        assertFalse(climbingAnimals.get(1) instanceof Bat);
        assertTrue(climbingAnimals.get(0) instanceof Ape);
        assertTrue(climbingAnimals.get(1) instanceof Bear);
        assertEquals(2,climbingAnimals.size());
    }

    @Test
    void CreateAnimalByTypeTest(){
        List<Animal> animals = new LinkedList<>();
        ZooManager zooManager = new ZooManager();
        zooManager.createAnimal(Ape.class);
        animals = zooManager.getAnimals();

        assertTrue(animals.get(0) instanceof Ape);
        assertTrue(animals.size() == 1);
        assertTrue(true == true);




    }


    /*
     * Manager soll die Möglichkeit bieten, die Summe des Gewichts aller Tiere
     * abzufragen (Über alle Tiere und nach Kategorie)
     * Die 3 GetMethoden zu einer allgemeinen umbauen
     * Die Create Methoden zu einer allgemeinen umbauen
     * Stream filter sum collect in Verbindung mit Listen nachschlagen
     */
}