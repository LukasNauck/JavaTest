package com.example;
import java.util.LinkedList;
import java.util.List;

public class ZooManager {
    public List<Animal> animals = new LinkedList();

    // Neue Tiere erstellen
    public void createApe() {
        Ape newApe = new Ape();
        animals.add(sortedInsert("Ape"), newApe);
    }

    public void createDolphin() {
        Dolphin newDolphin = new Dolphin();
        animals.add(sortedInsert("Dolphin"), newDolphin);
    }

    public void createEagle() {
        Eagle newEagle = new Eagle();
        animals.add(sortedInsert("Eagle"), newEagle);
    }

    public void createBear() {
        Bear newBear = new Bear();
        animals.add(sortedInsert("Bear"), newBear);
    }

    public int sortedInsert(String TypeToAdd) {
        int posInsert = 0;
        boolean searchingForPos = true;

        if (animals.isEmpty() == true) {
            return posInsert;
        }

        while (searchingForPos == true) {
            if (animals.size() > posInsert) {
                if (animals.get(posInsert).getAnimalType().compareTo(TypeToAdd) <= 0) {
                    posInsert++;
                } else {
                    searchingForPos = false;
                }
            } else {
                searchingForPos = false;
            }
        }
        return posInsert;
    }

    // Alle Tiere ausgeben
    public void printAllAnimals() {

        if (animals.size() == 0){
            System.out.println("There are no animals yet");
        }
        for (int i = 0; i < animals.size(); i++) {
            System.out.println(animals.get(i).getAnimalType()+" weighs "+animals.get(i).getWeight()+" and is in enclosure "+(i+1));
        }
    }
}
