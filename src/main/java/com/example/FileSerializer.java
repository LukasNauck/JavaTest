package com.example;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class FileSerializer implements ZooStateSerializer {

    private String filePath;

    // Constructor to set the file path
    public FileSerializer(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Animal> loadAnimals() {
        try (FileInputStream fileIn = new FileInputStream("zoo_manager_state.ser");
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

    @Override
    public void saveAnimals(List<Animal> animals) {
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            
            objectOut.writeObject(animals);
            System.out.println("ZooManager state saved");
            
        } catch (IOException e) {
            System.err.println("Error saving zoo state: " + e.getMessage());
        }
    }
}